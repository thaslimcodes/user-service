package com.ysg.service;


import com.ysg.dao.AppDao;
import com.ysg.dao.RoleDao;
import com.ysg.dao.UserRoleDao;
import com.ysg.data.UserRole;
import com.ysg.data.UserRoleId;
import com.ysg.model.Result;
import com.ysg.model.UserRoleObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thaslim on 22/04/17.
 */

@Component
@Transactional
public class UserRoleService {

    @Autowired
    public UserRoleDao userRoleDao;

    @Autowired
    public AppDao applicationDao;

    @Autowired
    public RoleDao roleDao;

    public List<UserRole> findAll() {
        return (List) userRoleDao.findAll();
    }

    public UserRole findById(UserRoleId userRoleId) {
        return userRoleDao.findOne(userRoleId);
    }

    public long count() {
        return userRoleDao.count();
    }

    public Result delete(UserRoleId userRoleId) {
        try {
            userRoleDao.delete(userRoleId);
            return new Result(1, "Record deleted successfully");
        } catch (Exception ex) {
            return new Result(0, ex.getMessage());
        }
    }

    public Result save(UserRoleId userRoleId) {
        if (userRoleId != null) {
            if (findById(userRoleId) == null) {
                UserRole userRole = new UserRole();
                userRole.setId(userRoleId);
                userRoleDao.save(userRole);
                return new Result(1, "Record Inserted Successfully");
            } else {
                return new Result(1, "Record Already Exists");
            }
        }
        return null;
    }


    public List<UserRoleObj> findByUserId(String userId) {
        List<UserRoleObj> objList = new ArrayList<>();
        for (UserRole e : userRoleDao.findByUserId(userId)) {
            objList.add(new UserRoleObj(e.getId().getUserId(), applicationDao.findOne(e.getId().getAppId()), roleDao.findOne(e.getId().getRoleId())));
        }
        return objList;

    }

    public void findByEmailId(String emailId) {
    }
}
