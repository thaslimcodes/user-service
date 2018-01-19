package com.ysg.resource;


import com.ysg.data.AppRoleId;
import com.ysg.data.UserRoleId;
import com.ysg.service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thaslim on 22/04/17.
 */

@RestController
@RequestMapping("/appRoles")
public class AppRoleResource {

    @Autowired
    public AppRoleService appRoleService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody AppRoleId ur) {
        return ResponseEntity.ok().body(appRoleService.save(ur));
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity delete(@RequestBody AppRoleId ur) {
        return ResponseEntity.ok().body(appRoleService.delete(ur));
    }

}
