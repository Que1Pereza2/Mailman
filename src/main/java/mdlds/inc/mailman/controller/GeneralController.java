/*
 * This class is the main controller where the logic of the API happens. It recieves the client's petition, processes it, cleans and
 * formats the message appropriately, then returns to the client the route and the cost of this route.
 * 
 */
package mdlds.inc.mailman.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Imports for the Spring Boot side of the API
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

// Imports for the graph part of the API
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

// Imports for the models of the API
import mdlds.inc.mailman.model.City;
import mdlds.inc.mailman.model.Link;
import mdlds.inc.mailman.service.CityService;

/**
 *
 * @author Baljeet
 */


@RestController
public class GeneralController {

//  We import City service only since using SpringBoot's way of handling databases we can pull from all the information we need
//   using only the city's repository.
    @Autowired
    private CityService repository;
    
/**
 *   The only end point of the API and is the one that receives the client's message and calls the necessary functions in order to
 * clean the message so that the API can work with the data.
 * 
 * @param destinations - this functions expects a JSON that contains 2 elements (start and finish).
 * 
 * @return path - This function returns a JSON that contains the path generated and the weight of the path.
 */
    @PostMapping("/")
    public Map<String,String> getRoute( @RequestBody Map<String,String> destinations){

//  We pull all the data that we need from the database and store it in citiesAndLinks.
        List<City> citiesAndLinks = repository.getAll();
        
//  We then process the database data down to the elements we need in the format that we need them to be so thei can be 
// inserted in the graph.
        Map translatedData = translateDataToGraph(citiesAndLinks);
        
//  We then generate the graph and the path from the processed data.
        Map path = generateGraph(translatedData, destinations.get("start"), destinations.get("finish"));
    
        return path;
    }
    
    /**
     *  This function handles the data received from the database and transforms it into data that the graph can understand it.
     * 
     * @param dataToPrepare - The data pulled from the database.
     * 
     * @return preparedData - The cleaned data, ready to be passed to the graph.
     */
    private Map<String,Map<String,Long>> translateDataToGraph(List<City> dataToPrepare){
//  This is the map where the cleaned data will be stored.
        Map<String,Map<String,Long>> preparedData = new HashMap<>();
        
//  We iterate trough the data recieved from the database and save the into prepared data the city name and it's links names 
//with their weights.
        for(City city : dataToPrepare){
//  This map stores the links asotiated with each individual city.
            Map<String,Long> linkMap  = new HashMap<>();
            
//  Here we Iterate trough the links of the city and calculate the weight, after that we store the link's destination city's name
// and the weight, which is calculated using the calculateWeight function.
            for(Link link : city.getLinksStart()){
                Long weight = calculateWeight(link);
                linkMap.put(link.getIdEnd().getName(), weight);
            }
//  After all the links have been iterated trough the city's name and the list of links are stored in the preparedData map. 
            preparedData.put(city.getName(), linkMap);
        }
        
        return preparedData;
    }
    
    /**
     * This function calculated the weight of the received link.
     * @param link - The link whose weight needs to be calculate.
     * @return weight - The calculated weight of the link.
     */
    private Long calculateWeight(Link link){
        Long weight; 
        
        if(link.isWildlifePassableRoute()){
            weight = (link.getWeather() + link.getAirTraficSaturation())*2;
        }
        
         weight = link.getWeather() + link.getAirTraficSaturation();
         
         return weight;
    }
    
    /**
     * This function generated the graph and passes and gets the path based on the starting city and the destination.
     * 
     * @param preparedData - The data necessary to generate the graph.
     * @param startingCity - The city from which the the path is calculated.
     * @param destination - The city to which the path needs to end.
     * 
     * @return path - Map containing the path and the weight.
     */
    private Map generateGraph(Map<String,Map<String,Long>> preparedData,String startingCity, String destination){
        
//  We create the graph that DijkstraShortestPath will use to generate the graph.
        Graph<String, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
//  We instantiate the algorithm that calculates the shortest path.
        DijkstraShortestPath<String,DefaultWeightedEdge> dijsktra = new DijkstraShortestPath<>(graph);
            
//  Here we add the vertex to the graph.
        preparedData.forEach((startCity, links) -> {
                graph.addVertex(startCity);
        });
        
//  Here we add the edges to the links and then assign the calculated weight to said edge.
        preparedData.forEach((startCity, links) -> {
            links.forEach((endCity, weight) ->{
                DefaultWeightedEdge edge = graph.addEdge( startCity,  endCity);
                graph.setEdgeWeight(edge, weight);
            });
        });
        
//  This map will store the calculated path.
        Map<String,String> path= new HashMap<>();
//  Here we call the method getPath to calculate the shortest path between "atarting city" and 'destination'.
        GraphPath generatedPath = dijsktra.getPath(startingCity, destination);
        
//  Here we convert the path to a String so it's easyer to pass the parameters.
        String routeString = "";
        for(int i = 0 ; i< generatedPath.getVertexList().size();i++)
            routeString += generatedPath.getVertexList().get(i) + "-";
        
//  Here we insert the path and the cost to the path map and return it.
        path.put("path", routeString);
        path.put("cost", Double.toString(generatedPath.getWeight()));
        
        return path;
    }
}