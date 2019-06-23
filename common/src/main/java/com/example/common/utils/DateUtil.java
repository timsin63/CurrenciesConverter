package com.example.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private DateUtil() {}

    private static final String DATE_REGEX = "yyyy-MM-dd";

    public static Date dateFromStr(String dateSrt) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_REGEX, Locale.UK);

        return format.parse(dateSrt);
    }

    public static String stringFromDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_REGEX, Locale.UK);

        return format.format(date);
    }
}
