package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 06/05/17.
 */
public class IdName {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    public IdName() {
    }

    public IdName(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
