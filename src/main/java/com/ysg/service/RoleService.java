package com.ysg.service;


import com.ysg.dao.RoleDao;
import com.ysg.data.Role;
import com.ysg.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Thaslim on 22/04/17.
 */

@Component
@Transactional
public class RoleService {

    @Autowired
    public RoleDao roleDao;

    public List<Role> findAll() {
        return (List) roleDao.findAll();
    }

    public Role findById(String id) {
        return roleDao.findOne(id);
    }

    public Result delete(String id) {
        try {
            roleDao.delete(id);
            return new Result(1, "Record deleted successfully");
        } catch (Exception ex) {
            return new Result(0, ex.getMessage());
        }
    }

    public Result save(Role role) {
        if (role.getId() == null || role.getId().isEmpty()) {
            return new Result(0, "Role Id Cannnot be Emplty");
        } else {
            Role role1 = roleDao.findOne(role.getId());
            if(role1!=null) {
                role1.setName(role.getName());
                roleDao.save(role1);
            } else {
                roleDao.save(role);
            }
        }
        return new Result(1, role.getId());
    }

    public List<Role> getRolesNotLinkedToAppUser(String appId, String userId) {
        return roleDao.getRolesNotLinkedToAppUser(appId, userId);
    }

    public List<Role> getRolesLinkedToAppUser(String appId, String userId) {
        return roleDao.getRolesLinkedToAppUser(appId, userId);
    }
}
