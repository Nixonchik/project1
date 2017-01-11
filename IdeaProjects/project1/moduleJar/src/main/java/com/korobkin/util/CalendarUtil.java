package com.korobkin.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Util class to convert Calendars, Strings, Dates etc.
 */
public class CalendarUtil {


    /**
     * Convert String like YYYY-MM-DD in Calendar Object
     * @param date YYYY-MM-DD String.
     * @return Calendar object of the date
     */
    public static Calendar getCalendar(String date) {
        String[] dateSpl = date.split("-");
        int year = Integer.parseInt(dateSpl[0]);
        int month = Integer.parseInt(dateSpl[1]);
        int day = Integer.parseInt(dateSpl[2]);

        Calendar result = new GregorianCalendar(year, month-1, day);
        return result;
    }

    /**
     * Convert String like YYYY-MM-DD and HH:MM in Calendar Object
     * @param date YYYY-MM-DD String.
     * @param time HH:MM String.
     * @return Calendar object of the date
     */
    public static Calendar getCalendar(String date, String time) {
        String[] dateSpl = date.split("-");
        int year = Integer.parseInt(dateSpl[0]);
        int month = Integer.parseInt(dateSpl[1]);
        int day = Integer.parseInt(dateSpl[2]);

        String[] timeSpl = time.split(":");
        int hour = Integer.parseInt(timeSpl[0]);
        int minute = Integer.parseInt(timeSpl[1]);

        return new GregorianCalendar(year, month-1, day, hour, minute);
    }


    /**
     * Returns String in format YYYY-MM-DD.
     * @param date Calendar object
     * @return String
     */
    public static String getDateString(Calendar date) {
        StringBuilder result = new StringBuilder();
        result
                .append(date.get(Calendar.YEAR))
                .append("-")
                .append(date.get(Calendar.MONTH) + 1)
                .append("-")
                .append(date.get(Calendar.DATE));
        return result.toString();
    }
}
