package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thaslim on 11/03/17.
 */
public class UserScope {

    @Getter
    @Setter
    private String user;

    @Getter
    @Setter
    private List<String> roles;

    public UserScope() {
        roles = new ArrayList<>();
    }

    public UserScope(String user, List<String> roles) {
        this.user = user;
        this.roles = roles;
    }
}
