package com.ysg.dao;

import com.ysg.data.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vrathakumar on 3/30/2017.
 */
public interface CityDao extends CrudRepository<City, String> {

    @Query("select c.name from City c")
    List<String> fetchAllCityNames();

    City findByName(String name);

    @Query("SELECT c FROM City c, User u, UserCity uc WHERE u.id = :id AND uc.id.userId = u.id AND c.id = uc.id.cityId ")
    List<City> citiesForUser(@Param("id") String id);
}
