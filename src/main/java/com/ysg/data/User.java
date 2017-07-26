package com.ysg.data;

import com.ysg.model.YNEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Thaslim on 27/03/17.
 */
@Entity
@Table(name = "registered_user")
public class User {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String mobile;

    @Getter
    @Setter
    private String email;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private YNEnum actvInd;

    public User() {
        actvInd=YNEnum.Y;
    }
}
