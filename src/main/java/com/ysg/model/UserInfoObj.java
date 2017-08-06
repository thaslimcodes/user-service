package com.ysg.model;

import com.ysg.data.City;
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
    private String appName;

    @Getter
    @Setter
    private List<String> roles;

    @Getter
    @Setter
    private List<City> cityList;
}
