package com.ysg.service;


import com.ysg.dao.CityDao;
import com.ysg.data.City;
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
public class CityService {

    @Autowired
    public CityDao cityDao;

    public List<City> findAll() {
        return (List) cityDao.findAll();
    }

    public City findById(String id) {
        return cityDao.findOne(id);
    }

    public long count() {
        return cityDao.count();
    }

    public Result delete(String id) {
        try {
            cityDao.delete(id);
            return new Result(1, "Record deleted successfully");
        } catch (Exception ex) {
            return new Result(0, ex.getMessage());
        }
    }

    public Result save(City city) {
        if (city.getId() != null && !city.getId().isEmpty()) {
            if (findById(city.getId()) == null) {
                return new Result(0, "Invalid ID");
            }
        } else {
            city.setId(UUID.randomUUID().toString());
        }
        cityDao.save(city);
        return new Result(1, city.getId());
    }

}
