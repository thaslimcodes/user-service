package com.ysg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Thaslim on 06/05/17.
 */
@AllArgsConstructor
@NoArgsConstructor
public class EmailApp {

    @Getter
    @Setter
    private String emailId;

    @Getter
    @Setter
    private String appName;

}
