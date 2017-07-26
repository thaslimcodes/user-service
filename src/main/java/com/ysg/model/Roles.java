package com.ysg.model;

/**
 * Created by Thaslim on 07/05/17.
 */
public enum Roles {

    HEAD_OFFICE("head_office"),
    FULFILMENT("fulfilment"),
    COMPLIANCE("compliance"),
    CITY_TEAM("city"),
    FINANCE("finance");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }
}
