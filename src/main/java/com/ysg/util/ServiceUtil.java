package com.ysg.util;

import com.ysg.model.Result;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Thaslim on 01/03/17.
 */
public class ServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ServiceUtil.class);

    private static final FastDateFormat DATE = FastDateFormat.getInstance("dd/MM/yyyy");
    private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");

    private static SecureRandom random = new SecureRandom();

    private ServiceUtil() {
        // To avoid initialization
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().equals("");
    }

    public static int intOf(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static String random() {
        return String.valueOf(random.nextLong());
    }

    public static Timestamp dateOf(String value) {
        try {
            Date date = DATE.parse(value);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Result errorOf(String text) {
        Result error = new Result(0);
        error.setMessage(text);
        return error;
    }

    public static ResponseEntity error(String text) {
        return ResponseEntity.ok(errorOf(text));
    }

    public static Timestamp timeOf(String date) {

        try {
            return new Timestamp(fdf.parse(date).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateOf(Date date) {
        return fdf.format(date);
    }
}
