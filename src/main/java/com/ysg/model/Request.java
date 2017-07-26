package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 27/02/17.
 */
public class Request {


    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String code;

    public Request() {
        // Required
    }

    public Request(String id, String code) {
        this.id = id;
        this.code = code;
    }
}
