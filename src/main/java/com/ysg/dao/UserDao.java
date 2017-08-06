package com.ysg.dao;

import com.ysg.data.User;
import com.ysg.data.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Thaslim on 27/03/17.
 */
@Transactional
public interface UserDao extends CrudRepository<User, String> {

    /**
     * This method will find a User instance in the database by email.
     * Note that this method is not implemented and its working code will be
     * automatically generated from its signature by Spring Data JPA.
     */
    User findByEmail(String email);

    @Query(" SELECT r.name FROM User u, Role r, UserRole ur, App a WHERE u.id = ur.id.userId AND r.id = ur.id.roleId AND u.email = :email AND a.id = ur.id.appId AND a.name = :app ")
    List<String> getRoles(@Param("app") String app, @Param("email") String email);

    @Query(" SELECT u.email FROM User u, Role r, UserRole ur, App a WHERE u.id = ur.id.userId AND r.id = ur.id.roleId AND r.name = :role AND a.id = ur.id.appId AND a.name = :app ")
    List<String> getEmailForRole(@Param("app") String app, @Param("role") String role);

    @Query(" SELECT u.email FROM User u, City c, UserCity uc, Role r, UserRole ur, App a WHERE u.id = ur.id.userId AND r.id = ur.id.roleId AND r.name = :role AND u.id = uc.id.userId AND c.id = uc.id.cityId AND c.name = :city AND a.id = ur.id.appId AND a.name = :app ")
    List<String> getEmailIdsForRoleAndCity(@Param("app") String app, @Param("role") String role, @Param("city") String city);

    @Query(" SELECT u.email FROM User u WHERE u.id = :id ")
    String getEmail(@Param("id") String id);

    @Query(" SELECT c.name FROM User u, UserCity uc, City c WHERE u.id = uc.id.userId AND c.id = uc.id.cityId AND u.id = :id ")
    List<String> getCitiesForUser(@Param("id") String id);

    @Query(" SELECT u FROM User u WHERE u.id not in (select ur.id.userId from  UserRole ur where ur.id.appId = :appId) ")
    List<User> getUsersNotLinkedToApp(@Param("appId") String appId);

    @Query(" SELECT ur FROM  UserRole ur WHERE ur.id.appId =  :appId ")
    List<UserRole> getUsersLinkedToApp(@Param("appId") String appId);
}
