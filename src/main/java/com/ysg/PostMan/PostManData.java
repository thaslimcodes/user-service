package com.ysg.PostMan;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by thaslim on 22/05/17.
 */
public class PostManData {

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String jsonString;

    @Getter
    @Setter
    private String httpMethod;

    @Getter
    @Setter
    private String requestId;

    @Getter
    @Setter
    private String componentId;

    @Getter
    @Setter
    private String requestString;

    @Getter
    @Setter
    private String name;

}
