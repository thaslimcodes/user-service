package com.ysg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Thaslim on 15/05/17.
 */
public class RoleStage {

    @Getter
    @Setter
    private String role;

    @Getter
    @Setter
    private List<String> stages;
}
