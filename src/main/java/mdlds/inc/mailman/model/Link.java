package mdlds.inc.mailman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Baljeet
 */

@Entity
@Table(name = "link")
public class Link {

//  We define the id attribute which will be automatically generated, not null, and used for the identity of every element.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//  We define the congestion of the route.
    @Column(nullable = false)
    private Long airTraficCongestion;

//  We define the weather of the route.    
    @Column(nullable = false)
    private Long weather;

//  We define the variable that tells us if the route is transitable for animals.    
    @Column(nullable = false)
    private boolean  wildlifePassableRoute;

//  We define the foreign key of the route named "idStrart" and ignore the properties 
//    named "linksStart" and "linksEnd" to avoid an infinite loop.    
    @JsonIgnoreProperties(value = {"linksStart", "linksEnd"})
    @ManyToOne
    @JoinColumn(name = "idStart")
    private City idStart;
    
//  We define the foreign key of the route named "idEnd" and ignore the properties 
//    named "linksStart" and "linksEnd" to avoid an infinite loop.
    @JsonIgnoreProperties(value = {"linksStart", "linksEnd"})
    @ManyToOne
    @JoinColumn(name = "idEnd")
    private City idEnd;
    
// Constructor for the class.
    public Link(){
        
    }

//    Getters and setters.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAirTraficSaturation() {
        return airTraficCongestion;
    }

    public void setAirTraficSaturation(Long airTraficCongestion) {
        this.airTraficCongestion = airTraficCongestion;
    }

    public Long getWeather() {
        return weather;
    }

    public void setWeather(Long weather) {
        this.weather = weather;
    }

    public boolean isWildlifePassableRoute() {
        return wildlifePassableRoute;
    }

    public void setWildlifePassableRoute(boolean wildlifePassableRoute) {
        this.wildlifePassableRoute = wildlifePassableRoute;
    }

    public City getIdStart() {
        return idStart;
    }

    public void setIdStart(City idStart) {
        this.idStart = idStart;
    }

    public City getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(City idEnd) {
        this.idEnd = idEnd;
    }
    
    @Override
    public String toString(){
        return "Link with " + id + " starts in " + idStart.getName() + " and ends in " + idEnd.getName();
    }
}
