package com.ysg.dao;

import com.ysg.data.App;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Thaslim on 31/05/17.
 */
public interface AppDao extends CrudRepository<App, String> {

    App findByName(String name);

    @Query("select a from App a where a.id not in (select ur.id.appId from UserRole ur where ur.id.userId = :userId) ")
    List<App> getAppsNotLinkedToUser(@Param("userId") String userId);
}
