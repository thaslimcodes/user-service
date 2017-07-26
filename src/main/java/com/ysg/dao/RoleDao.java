package com.ysg.dao;

import com.ysg.data.Role;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

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

}
