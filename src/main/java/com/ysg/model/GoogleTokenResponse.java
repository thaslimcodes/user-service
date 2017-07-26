package com.ysg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 04/05/17.
 */
public class GoogleTokenResponse {

    @Getter
    @Setter
    private String aud;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private long exp;

    @Getter
    @Setter
    @JsonProperty("given_name")
    private String name;

    @Getter
    @Setter
    private String picture;
}
