package com.ysg.util;

import java.util.regex.Pattern;

/**
 * Created by Thaslim on 10/03/17.
 */
public class ValidationUtil {

    private static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static boolean email(String email) {

        return EMAIL_ADDRESS.matcher(email).matches();
    }
}
