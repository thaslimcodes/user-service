package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Thaslim on 04/05/17.
 */
public class Profile {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String photo;

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private List<String> roles;
}
