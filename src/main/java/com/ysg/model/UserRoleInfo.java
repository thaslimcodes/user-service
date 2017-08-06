package com.ysg.model;

import com.ysg.data.Role;
import com.ysg.data.User;
import com.ysg.data.UserRoleId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Thaslim on 27/03/17.
 */
@AllArgsConstructor
public class UserRoleInfo {

    @Getter
    @Setter
    private UserRoleId id;

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private Role role;


}
