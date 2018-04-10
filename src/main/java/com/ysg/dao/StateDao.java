package com.ysg.dao;

import com.ysg.data.City;
import com.ysg.data.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vrathakumar on 3/30/2017.
 */
public interface StateDao extends CrudRepository<State, String> {

}
