package com.ysg.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ysg.data.User;
import com.ysg.model.*;
import com.ysg.security.TokenService;
import com.ysg.service.GoogleTokenService;
import com.ysg.service.UserService;
import com.ysg.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thaslim on 27/03/17.
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    public UserService userService;
    @Value("${jwt.audience}")
    private String audience;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private GoogleTokenService googleTokenService;

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


    @RequestMapping(value = "/getAppInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity getAppInfo(@RequestBody EmailApp emailApp) {
        return ResponseEntity.ok().body(userService.getAppInfo(emailApp));
    }

    @HystrixCommand(fallbackMethod = "unauthorized")
    @RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity signIn(@RequestBody Token token) {
        GoogleTokenResponse res = googleTokenService.verify(token.getToken());
        if (res.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (token.getAppName() == null || token.getAppName().isEmpty()) {
            token.setAppName(Constants.APP_NAME);
        }
        UserInfoObj userInfoObj = userService.getAppInfo(new EmailApp(res.getEmail(), token.getAppName()));
        if (userInfoObj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        String session = tokenService.generate(userInfoObj, new String[]{audience});

        Profile profile = new Profile();
        profile.setName(res.getName());
        profile.setId(userInfoObj.getUser().getId());
        profile.setPhoto(res.getPicture());
        profile.setToken(session);
        profile.setRoles(userInfoObj.getRoles());
        profile.setCityList(userInfoObj.getCityList());

        return ResponseEntity.ok(profile);
    }

    private ResponseEntity unauthorized(@RequestBody Token token) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
