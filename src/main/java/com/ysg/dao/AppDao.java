package com.ysg.dao;

import com.ysg.data.App;
import com.ysg.data.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Thaslim on 31/05/17.
 */
public interface AppDao extends CrudRepository<App, String> {

    @Query("select a from App a where a.id not in (select ur.id.appId from UserRole ur where ur.id.userId = :userId) ")
    List<App> getAppsNotLinkedToUser(@Param("userId") String userId);

    @Query("select r from Role r where r.id in (select ar.id.roleId from AppRole ar where ar.id.appId=:appId) ")
    List<Role> findRoles(@Param("appId") String appId);

    @Query("select r from Role r where r.id not in (select ar.id.roleId from AppRole ar where ar.id.appId=:appId) ")
    List<Role> findNotLinkedRoles(@Param("appId") String appId);
}
