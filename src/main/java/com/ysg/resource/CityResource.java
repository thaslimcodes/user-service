package com.ysg.resource;


import com.ysg.data.City;
import com.ysg.service.CityService;
import com.ysg.service.UserCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thaslim on 22/04/17.
 */

@RestController
@RequestMapping("/city")
public class CityResource {

    @Autowired
    public CityService cityService;

    @Autowired
    public UserCityService userCityService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(cityService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity find(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(cityService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody City city) {
        return ResponseEntity.ok().body(cityService.save(city));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody City city) {
        return ResponseEntity.ok().body(cityService.save(city));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(cityService.delete(id));
    }


    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getLinkedCities(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(userCityService.getLinkedCities(userId));
    }

    @RequestMapping(value = "/users/{userId}/notLinked", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getNotLinkedCities(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(userCityService.getNotLinkedCities(userId));
    }

}
