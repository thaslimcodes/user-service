package com.ysg.dao;

import com.ysg.data.City;
import com.ysg.data.UserCity;
import com.ysg.data.UserCityId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Thaslim on 23/05/17.
 */
@Transactional
public interface UserCityDao extends CrudRepository<UserCity, UserCityId> {

    @Query("select c from City c where c.id in (select uc.id.cityId from UserCity uc where uc.id.userId=:userId)")
    List<City> getLinkedCities(@Param("userId") String userId);

    @Query("select c from City c where c.id not in (select uc.id.cityId from UserCity uc where uc.id.userId=:userId)")
    List<City> getNotLinkedCities(@Param("userId") String userId);

}
