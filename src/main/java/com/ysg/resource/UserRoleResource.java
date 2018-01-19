package com.ysg.resource;


import com.ysg.data.UserRoleId;
import com.ysg.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thaslim on 22/04/17.
 */

@RestController
@RequestMapping("/userRoles")
public class UserRoleResource {

    @Autowired
    public UserRoleService userRoleService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(userRoleService.findAll());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(userRoleService.findByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody UserRoleId ur) {
        return ResponseEntity.ok().body(userRoleService.save(ur));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity delete(@RequestBody UserRoleId ur) {
        return ResponseEntity.ok().body(userRoleService.delete(ur));
    }

}
