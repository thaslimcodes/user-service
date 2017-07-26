package com.ysg.model;

/**
 * Created by thaslim on 28/05/17.
 */
public enum YNEnum {
    Y, N, None;

    public static YNEnum get(String str) {
        switch (str.toUpperCase()) {
            case "Y":
                return YNEnum.Y;
            case "N":
                return YNEnum.N;
            default:
                return YNEnum.None;
        }
    }
}