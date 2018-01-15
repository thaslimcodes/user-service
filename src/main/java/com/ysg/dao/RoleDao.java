package com.ysg.dao;

import com.ysg.data.Role;
import com.ysg.model.AppRoleDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Thaslim on 27/03/17.
 */
@Transactional
public interface RoleDao extends CrudRepository<Role, String> {

    /**
     * This method will find a User instance in the database by email.
     * Note that this method is not implemented and its working code will be
     * automatically generated from its signature by Spring Data JPA.
     */
    Role findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.id NOT IN (SELECT ur.id.roleId FROM UserRole ur WHERE ur.id.appId=:appId AND ur.id.userId=:userId)")
    List<Role> getRolesNotLinkedToAppUser(@Param("appId") String appId, @Param("userId") String userId);

    @Query("SELECT r FROM Role r WHERE r.id IN (SELECT ur.id.roleId FROM UserRole ur WHERE ur.id.appId=:appId AND ur.id.userId=:userId)")
    List<Role> getRolesLinkedToUser(@Param("userId") String userId, @Param("appId") String appId);

    @Query("SELECT new com.ysg.model.AppRoleDetail(a.id, r.id, r.name)  FROM Role r, UserRole ur, App a WHERE  r.id=ur.id.roleId and ur.id.appId=a.id and ur.id.appId=:appId AND ur.id.userId=:userId")
    List<AppRoleDetail> getRolesLinkedToUser(@Param("userId") String userId);


}
