package com.ysg.model;

import com.ysg.data.App;
import com.ysg.data.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class UserRoleObj {

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private App app;

    @Getter
    @Setter
    private Role role;
}
