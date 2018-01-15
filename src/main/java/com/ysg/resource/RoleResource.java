package com.ysg.resource;


import com.ysg.data.Role;
import com.ysg.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thaslim on 22/04/17.
 */

@RestController
@RequestMapping("/roles")
public class RoleResource {

    @Autowired
    public RoleService roleService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(roleService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity find(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(roleService.findById(id));
    }

    @RequestMapping(value = "/apps/{appId}/users/{userId}/notLinked", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getRolesNotLinkedToAppUser(@PathVariable("appId") String appId, @PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(roleService.getRolesNotLinkedToAppUser(appId, userId));
    }

    @RequestMapping(value="/linkedUsers",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getRolesLinkedToAppUser(@RequestParam("userId") String userId, @RequestParam(value = "appId",defaultValue = "") String appId) {
        appId=appId.trim();
        if(appId.isEmpty()) {
            return ResponseEntity.ok().body(roleService.getRolesLinkedToUser(userId));
        }
        return ResponseEntity.ok().body(roleService.getRolesLinkedToUser(userId, appId));
    }


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody Role role) {
        return ResponseEntity.ok().body(roleService.save(role));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody Role role) {
        return ResponseEntity.ok().body(roleService.save(role));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(roleService.delete(id));
    }
}
