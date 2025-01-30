/*
 * 
 * 
 * 
 */
package mdlds.inc.mailman.controller;

import mdlds.inc.mailman.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Baljeet
 */
@RestController
public class CityController {
    
    @Autowired
    private CityService repository;
    
    
}
