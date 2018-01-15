package com.ysg.dao;

import com.ysg.data.AppRole;
import com.ysg.data.AppRoleId;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Thaslim on 23/05/17.
 */
@Transactional
public interface AppRoleDao extends CrudRepository<AppRole, AppRoleId> {

}
