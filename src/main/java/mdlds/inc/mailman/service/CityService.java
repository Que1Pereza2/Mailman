package mdlds.inc.mailman.service;

import java.util.List;
import mdlds.inc.mailman.model.City;

/**
 *
 * @author Baljeet
 */
public interface CityService {
    
// We define the functions that the API will use.
    public City getCity(Long id);
    public List<City> getAll();
}
