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
@RequestMapping("/states")
public class StateResource {

    @Autowired
    public CityService stateService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(stateService.getAllStates());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity find(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(stateService.findByStateId(id));
    }


}
