package com.ysg.service;


import com.ysg.dao.AppRoleDao;
import com.ysg.data.AppRole;
import com.ysg.data.AppRoleId;
import com.ysg.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Thaslim on 22/04/17.
 */

@Component
@Transactional
public class AppRoleService {

    @Autowired
    public AppRoleDao appRoleDao;


    public Result delete(AppRoleId appRoleId) {
        try {
            appRoleDao.delete(appRoleId);
            return new Result(1, "Record deleted successfully");
        } catch (Exception ex) {
            return new Result(0, ex.getMessage());
        }
    }

    public Result save(AppRoleId appRoleId) {
        if (appRoleId != null) {
            if (appRoleDao.findOne(appRoleId) == null) {
                AppRole appRole = new AppRole();
                appRole.setId(appRoleId);
                appRoleDao.save(appRole);
                return new Result(1, "Record Inserted Successfully");
            } else {
                return new Result(1, "Record Already Exists");
            }
        }
        return null;
    }


}
