package mdlds.inc.mailman.Repository;

import mdlds.inc.mailman.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Baljeet
 */
// The repository for the city.
@Repository
public interface CityRepository extends JpaRepository<City,Long>{
    
}
