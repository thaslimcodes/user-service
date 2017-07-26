package com.ysg.model;

import com.ysg.data.App;
import com.ysg.data.City;
import com.ysg.data.Role;
import com.ysg.data.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserInfoObj {

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private String applicationName;

    @Getter
    @Setter
    private String roleName;

    @Getter
    @Setter
    private List<City> cityList;
}
