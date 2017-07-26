package com.ysg.dao;

import com.ysg.data.UserRole;
import com.ysg.data.UserRoleId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Thaslim on 23/05/17.
 */
@Transactional
public interface UserRoleDao extends CrudRepository<UserRole, UserRoleId> {

    @Query("select ur from UserRole ur where ur.id.userId=:userId")
    List<UserRole> findByUserId(@Param("userId") String userId);
}
