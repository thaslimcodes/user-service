package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 04/03/17.
 */
public class SaltHash {

    @Getter
    @Setter
    private String hash;

    @Getter
    @Setter
    private String salt;

    public SaltHash() {
    }

    public SaltHash(String salt, String hash) {
        this.hash = hash;
        this.salt = salt;
    }
}
