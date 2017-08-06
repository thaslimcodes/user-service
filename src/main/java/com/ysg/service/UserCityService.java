package com.ysg.service;


import com.ysg.dao.UserCityDao;
import com.ysg.data.City;
import com.ysg.data.UserCity;
import com.ysg.data.UserCityId;
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
public class UserCityService {

    @Autowired
    public UserCityDao userCityDao;

    public List<UserCity> findAll() {
        return (List) userCityDao.findAll();
    }

    public UserCity findById(UserCityId userCityId) {
        return userCityDao.findOne(userCityId);
    }

    public long count() {
        return userCityDao.count();
    }

    public Result delete(UserCityId userCityId) {
        try {
            userCityDao.delete(userCityId);
            return new Result(1, "Record deleted successfully");
        } catch (Exception ex) {
            return new Result(0, ex.getMessage());
        }
    }

    public Result insert(UserCityId userCityId) {
        if (userCityId != null) {
            if (findById(userCityId) == null) {
                UserCity userCity = new UserCity();
                userCity.setId(userCityId);
                userCityDao.save(userCity);
                return new Result(1, "Record Inserted Successfully");
            } else {
                return new Result(1, "Record Already Exists");
            }
        }
        return null;
    }

    public List<City> getLinkedCities(String userId) {
        return userCityDao.getLinkedCities(userId);
    }

    public List<City> getNotLinkedCities(String userId) {
        return userCityDao.getNotLinkedCities(userId);
    }

}
