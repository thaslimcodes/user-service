package com.ysg.service;


import com.ysg.dao.RoleDao;
import com.ysg.data.Role;
import com.ysg.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    public long count() {
        return roleDao.count();
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
        if (role.getId() != null && !role.getId().isEmpty()) {
            return new Result(0, "Role Name cannot be updated only added. Provide blank Id");
        }
        role.setId(UUID.randomUUID().toString());
        roleDao.save(role);
        return new Result(1, role.getId());
    }

}
