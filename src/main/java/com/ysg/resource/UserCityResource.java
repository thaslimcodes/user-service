package com.ysg.resource;


import com.ysg.data.UserCityId;
import com.ysg.service.UserCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thaslim on 22/04/17.
 */

@RestController
@RequestMapping("/userCity")
public class UserCityResource {

    @Autowired
    public UserCityService userCityService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(userCityService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody UserCityId userCityId) {
        return ResponseEntity.ok().body(userCityService.insert(userCityId));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity delete(@RequestBody UserCityId userCityId) {
        return ResponseEntity.ok().body(userCityService.delete(userCityId));
    }

}
