package com.teamseven.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateHelper {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "";
        }

        return date.format(dateFormatter);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        return dateTime.format(dateTimeFormatter);
    }

    public static String localDateToFriendlyString(LocalDate localDate) {
        int dom = localDate.getDayOfMonth();
        String domStr = String.valueOf(dom);
        String dayOfMonth = domStr;
        if (domStr.endsWith("1") && !domStr.equals("11")) {
            dayOfMonth += "st";
        } else if (domStr.endsWith("2") && !domStr.equals("12")) {
            dayOfMonth += "nd";
        } else if (domStr.endsWith("3") && !domStr.equals("13")) {
            dayOfMonth += "rd";
        } else {
            dayOfMonth += "th";
        }

        Month mon = localDate.getMonth();
        String month = StringUtils.capitalize(mon.toString());
        int year = localDate.getYear();
        return String.format("%s %s %d", dayOfMonth, month, year);
    }

    public static String dateToFriendlyString(Date date) {
        return localDateToFriendlyString(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault()).toLocalDate()
        );
    }

    public static String dateTimeToFriendlyString(Date date) {
        return localDateTimeToFriendlyString(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault())
        );
    }

    public static String localDateTimeToFriendlyString(LocalDateTime localDateTime) {
        return localDateTimeToFriendlyString(localDateTime, false);
    }

    public static String localDateTimeToFriendlyString(LocalDateTime localDateTime, boolean showSecond) {
        if (localDateTime == null) {
            return "";
        }

        DayOfWeek dow = localDateTime.getDayOfWeek();
        String dayOfWeek = StringUtils.capitalize(dow.toString());

        int dom = localDateTime.getDayOfMonth();
        String domStr = String.valueOf(dom);
        String dayOfMonth = domStr;
        if (domStr.endsWith("1") && !domStr.equals("11")) {
            dayOfMonth += "st";
        } else if (domStr.endsWith("2") && !domStr.equals("12")) {
            dayOfMonth += "nd";
        } else if (domStr.endsWith("3") && !domStr.equals("13")) {
            dayOfMonth += "rd";
        } else {
            dayOfMonth += "th";
        }

        Month mon = localDateTime.getMonth();
        String month = StringUtils.capitalize(mon.toString());

        int year = localDateTime.getYear();
        int hr = localDateTime.get(ChronoField.CLOCK_HOUR_OF_AMPM);
        int min = localDateTime.getMinute();
        int sec = localDateTime.getSecond();
        int ampm = localDateTime.get(ChronoField.AMPM_OF_DAY);
        String ampmStr = ampm == 0 ? "AM" : "PM";
        if (showSecond)
            return String.format("%s %s %s, %d %02d:%02d:%02d %s",
                    dayOfWeek, dayOfMonth, month, year, hr, min, sec, ampmStr);
        else
            return String.format("%s %s %s, %d %02d:%02d %s",
                    dayOfWeek, dayOfMonth, month, year, hr, min, ampmStr);
    }


    /**
     * @return Date in the format M - d,yr e.g. Jan - 31, 07
     */
    public static String localDateTimeToString(LocalDateTime date) {
        int dayOfMonth = date.getDayOfMonth();
        String monthOfYear = date.getMonth().toString();
        int year = date.getYear();
        String dateStr = String.format("%s - %d, %d", monthOfYear, dayOfMonth, year);
        return dateStr;
    }


    public static boolean isLeapYear(LocalDateTime date) {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        int year = date.getYear();
        return cal.isLeapYear(year);
    }

    public static int getQuarter(LocalDateTime timeStamp) {
        return timeStamp == null ? -1 : ((timeStamp.getMonth().getValue() - 1) / 3) + 1;
    }

    public static int getWeekOfYear(LocalDateTime timeStamp) {
        LocalDate date = timeStamp.toLocalDate();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

        return date.get(woy);
    }

}
