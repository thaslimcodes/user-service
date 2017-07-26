package com.ysg.data;

import com.ysg.model.YNEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Thaslim on 27/03/17.
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;
}
