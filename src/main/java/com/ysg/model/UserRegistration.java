package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 27/03/17.
 */
public class UserRegistration {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String app;

    @Getter
    @Setter
    private String role;
}
