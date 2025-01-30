package mdlds.inc.mailman.serviceIMPL;

import java.util.List;
import mdlds.inc.mailman.Repository.CityRepository;
import mdlds.inc.mailman.model.City;
import mdlds.inc.mailman.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Baljeet
 */
@Service
public class CityServiceIMPL implements CityService{

    @Autowired
    private final CityRepository repository;
    
    /**
     * Constructor for the class where we instantiate the repository.
     * 
     * @param repository - The repository is passed automatically to the function and we instantiate it.
     */
    public CityServiceIMPL(CityRepository repository){
        this.repository = repository;
    }
    
    /**
     * This function returns the city that coincides with the id passed.
     * @param id - The id that we search for.
     * @return City - The city that we pulled from the database.
     */
    @Override
    public City getCity(Long id) {
        return repository.findById(id).get();
    }

    /**
     * This method pulls all the cities from the database.
     * @return Cities - A list of all the cities from the database.
     */
    @Override
    public List<City> getAll() {
        return repository.findAll();
    }
    
}