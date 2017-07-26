package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 04/03/17.
 */
public class SetPinRequest {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String password;
}
