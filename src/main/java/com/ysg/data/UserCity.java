package com.ysg.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Thaslim on 27/04/17.
 */
@Entity
@Table(name = "user_city")
public class UserCity {

    @EmbeddedId
    @Getter
    @Setter
    private UserCityId id;
}
