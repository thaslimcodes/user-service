package com.ysg.service;


import com.ysg.dao.AppDao;
import com.ysg.data.App;
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
public class AppService {

    @Autowired
    public AppDao appDao;

    public List<App> findAll() {
        return (List) appDao.findAll();
    }

    public App findById(String id) {
        return appDao.findOne(id);
    }

    public long count() {
        return appDao.count();
    }

    public Result delete(String id) {
        try {
            appDao.delete(id);
            return new Result(1, "Record deleted successfully");
        } catch (Exception ex) {
            return new Result(0, ex.getMessage());
        }
    }

    public Result save(App app) {
        if (app.getId() != null && !app.getId().isEmpty()) {
            return new Result(0, "App Name cannot be updated only added. Provide blank Id");
        }
        app.setId(UUID.randomUUID().toString());
        appDao.save(app);
        return new Result(1, app.getId());
    }

    public List<App> getAppsNotLinkedToUser(String userId) {
        return appDao.getAppsNotLinkedToUser(userId);
    }
}
