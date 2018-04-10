package com.ysg.resource;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/apps/{appId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getUsersLinkedToApp(@PathVariable("appId") String appId) {
        return ResponseEntity.ok().body(userService.getUsersLinkedToApp(appId));
    }

    @RequestMapping(value = "/apps/{appId}/notLinked", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getUsersNotLinkedToApp(@PathVariable("appId") String appId) {
        return ResponseEntity.ok().body(userService.getUsersNotLinkedToApp(appId));
    }

    @RequestMapping(value = "/emails/apps/{appId}/roles/{roleId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getEmailsForAppRole(@PathVariable("appId") String appId,
                                              @PathVariable("roleId") String roleId) {
        return ResponseEntity.ok().body(userService.getEmailIdsForAppRole(appId, roleId));
    }

    @RequestMapping(value = "/emails/apps/{appId}/roles/{roleId}/city/{cityId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getEmailsForAppRoleCity(@PathVariable("appId") String appId,
                                                  @PathVariable("roleId") String roleId,
                                                  @PathVariable("cityId") String cityId) {
        return ResponseEntity.ok().body(userService.getEmailIdsForAppRoleCity(appId, roleId, cityId));
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

    @RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity signIn(@RequestBody Token token) {
        GoogleTokenResponse res = googleTokenService.verify(token.getToken());
        if(res.getEmail() == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        token.setAppName(Constants.APP_NAME);


        UserScope userScope = userService.authenticate(res.getEmail(), token.getAppName());
        if(userScope == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String[] scopes = new String[userScope.getRoles().size()];
        scopes = userScope.getRoles().toArray(scopes);

        String session = tokenService.generate(userScope.getUser(), new String[]{ audience }, scopes);


        Profile profile = new Profile();
        profile.setName(res.getName());
        profile.setPhoto(res.getPicture());
        profile.setToken(session);
        profile.setRoles(userScope.getRoles());

        return ResponseEntity.ok(profile);

    }

    @RequestMapping(value = "/signInApp", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity signInApp(@RequestBody Token token) {
        try {
            GoogleTokenResponse res = googleTokenService.verify(token.getToken());
            logger.info(res.getEmail());
            if (res.getEmail() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            UserScope userScope = userService.authenticate(res.getEmail(), token.getAppName());
            logger.info(res.getEmail());
            if (userScope == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Profile profile = new Profile();
            profile.setName(res.getName());
            profile.setPhoto(res.getPicture());
            profile.setUserScope(userScope);
            profile.setRoles(userScope.getRoles());
            return ResponseEntity.ok(profile);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }





}
