package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 25/02/17.
 */
public class Result {

    public static final Result OK = new Result(1, "success");
    public static final Result FAIL = new Result(0, "failure");
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;

    public Result() {
        // Required
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
