package com.ysg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AppRoleDetail {

    @Getter
    @Setter
    private String appId;

    @Getter
    @Setter
    private String roleId;

    @Getter
    @Setter
    private String roleName;
}
