package com.ysg.service;


import com.ysg.dao.AppDao;
import com.ysg.dao.RoleDao;
import com.ysg.dao.UserDao;
import com.ysg.data.City;
import com.ysg.data.Role;
import com.ysg.data.User;
import com.ysg.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Thaslim on 27/03/17.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String APP = "EPSILON";

    @Autowired
    private AppDao appDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserCityService userCityService;

    public List<User> findAll() {
        return (List) userDao.findAll();
    }

    public User findById(String id) {
        return userDao.findOne(id);
    }

    public long count() {
        return userDao.count();
    }

    public Result delete(String id) {
        try {
            userDao.delete(id);
            return new Result(1, "Record deleted successfully");
        } catch (Exception ex) {
            return new Result(0, ex.getMessage());
        }
    }

    public Result save(User user) {
        if (user.getId() != null) {
            if (findById(user.getId()) == null) {
                return new Result(0, "Invalid ID");
            }
        } else {
            user.setId(UUID.randomUUID().toString());
        }
        userDao.save(user);
        return new Result(1, user.getId());
    }

    public UserInfoObj getAppInfo(EmailApp emailApp) {
        if (emailApp.getAppName() != null && emailApp.getEmailId() != null) {
            User user = userDao.findByEmail(emailApp.getEmailId());
            if (user != null) {
                List<UserRoleObj> collect = userRoleService.findByUserId(user.getId())
                        .stream()
                        .filter(e -> e.getApp().getId().equalsIgnoreCase(emailApp.getAppName()))
                        .collect(Collectors.toList());
                String appName = collect.stream().map(e -> e.getApp().getId()).findFirst().orElse("");
                List<String> roleIds = new ArrayList();
                collect.stream().forEach(e -> roleIds.add(e.getRole().getId()));

                List<Role> roles = new ArrayList();
                collect.stream().forEach(e -> roles.add(e.getRole()));

                List<City> linkedCities = userCityService.getLinkedCities(user.getId());
                return new UserInfoObj(user, appName, roleIds, roles, linkedCities);
            } else {
                return null;
            }
        }
        return null;
    }

    public List<User> getUsersNotLinkedToApp(String appId) {
        return userDao.getUsersNotLinkedToApp(appId);
    }

    public List<UserRoleInfo> getUsersLinkedToApp(String appId) {
        List<UserRoleInfo> list = new ArrayList<UserRoleInfo>();
        userDao.getUsersLinkedToApp(appId).stream()
                .forEach(e -> list.add(new UserRoleInfo(e.getId(), userDao.findOne(e.getId().getUserId()), roleDao.findOne(e.getId().getRoleId()))));

        return list;

    }
}
