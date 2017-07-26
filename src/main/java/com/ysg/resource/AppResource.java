package com.ysg.resource;


import com.ysg.data.App;
import com.ysg.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thaslim on 22/04/17.
 */

@RestController
@RequestMapping("/apps")
public class AppResource {

    @Autowired
    public AppService appService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(appService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity find(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(appService.findById(id));
    }

    @RequestMapping(value = "/getAppsNotLinkedToUser", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getAppsNotLinkedToUsers(@RequestParam("userId") String userId) {
        return ResponseEntity.ok().body(appService.getAppsNotLinkedToUser(userId));
    }


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody App app) {
        return ResponseEntity.ok().body(appService.save(app));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody App app) {
        return ResponseEntity.ok().body(appService.save(app));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(appService.delete(id));
    }
}
