package com.ysg.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ysg.data.User;
import com.ysg.model.*;
import com.ysg.security.TokenService;
import com.ysg.service.GoogleTokenService;
import com.ysg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ysg.util.ServiceUtil.error;
import static com.ysg.util.ServiceUtil.isNullOrEmpty;

/**
 * Created by Thaslim on 27/03/17.
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Value("${jwt.audience}")
    private String audience;

    @Autowired
    private TokenService tokenService;

//    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public ResponseEntity register(@RequestBody UserRegistration userRegistration) {
//
//        if (isNullOrEmpty(userRegistration.getEmail())) {
//            return error("Email is required");
//        }
//
//        if (isNullOrEmpty(userRegistration.getFirstname())) {
//            return error("Firstname is required");
//        }
//
//        if (isNullOrEmpty(userRegistration.getLastname())) {
//            return error("Lastname is required");
//        }
//
//        if (isNullOrEmpty(userRegistration.getApp())) {
//            return error("User is required");
//        }
//
//        if (isNullOrEmpty(userRegistration.getRole())) {
//            return error("Role is required");
//        }
//
//        Result result = userService.register(userRegistration);
//        if (result.getCode() == 0) {
//            return error("Failed to register");
//        }
//
//        return ResponseEntity.ok(Result.OK);
//    }
//
    
    
//    @HystrixCommand(fallbackMethod = "unauthorized")
//    @RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public ResponseEntity signIn(@RequestBody Token token) {
//
//        GoogleTokenResponse res = googleTokenService.verify(token.getToken());
//        if (res.getEmail() == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        UserScope userScope = userService.authenticate(res.getEmail());
//        if (userScope == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String[] scopes = new String[userScope.getRoles().size()];
//        scopes = userScope.getRoles().toArray(scopes);
//
//        String session = tokenService.generate(userScope.getUser(), new String[]{audience}, scopes);
//
//        Profile profile = new Profile();
//        profile.setName(res.getName());
//        profile.setPhoto(res.getPicture());
//        profile.setToken(session);
//        profile.setRoles(userScope.getRoles());
//
//        return ResponseEntity.ok(profile);
//    }

//    private ResponseEntity unauthorized(Token token) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }

    @Autowired
    public UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity find(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @RequestMapping(value = "/getUsersLinkedToApp", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getUsersLinkedToApp(@RequestParam("appId") String appId) {
        return ResponseEntity.ok().body(userService.getUsersLinkedToApp(appId));
    }
    @RequestMapping(value = "/getUsersNotLinkedToApp", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getUsersNotLinkedToApp(@RequestParam("appId") String appId) {
        return ResponseEntity.ok().body(userService.getUsersNotLinkedToApp(appId));
    }


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity insert(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity update(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }


    @RequestMapping(value="/getAppInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity getAppInfo(@RequestBody EmailApp emailApp)  {
        return ResponseEntity.ok().body(userService.getAppInfo(emailApp));
    }


}
