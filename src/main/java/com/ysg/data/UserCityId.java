package com.ysg.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Thaslim on 27/04/17.
 */
@Embeddable
public class UserCityId implements Serializable {

    @Column(name = "user_id")
    @Getter
    @Setter
    private String userId;

    @Column(name = "city_id")
    @Getter
    @Setter
    private String cityId;
}
