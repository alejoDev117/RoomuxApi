package com.uco.RoomuxApi.RommuxApi.crossCutting.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilDateTime {

    // Default Values as Strings
    private static final String DEFAULT_VALUE_DATE_AS_STRING = "0001-01-01"; // Formato yyyy-MM-dd
    private static final String DEFAULT_VALUE_TIME_AS_STRING = "00:00:00"; // Formato HH:mm:ss
    private static final String DEFAULT_VALUE_DATETIME_AS_STRING = "0001-01-01T00:00:00"; // Formato yyyy-MM-ddTHH:mm:ss

    // Formatters
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // Default Values as Objects
    private static final Date DEFAULT_VALUE_DATE;
    private static final LocalTime DEFAULT_VALUE_TIME;
    private static final LocalDateTime DEFAULT_VALUE_DATETIME;

    static {
        try {
            DEFAULT_VALUE_DATE = DATE_FORMATTER.parse(DEFAULT_VALUE_DATE_AS_STRING);
            DEFAULT_VALUE_TIME = LocalTime.parse(DEFAULT_VALUE_TIME_AS_STRING, TIME_FORMATTER);
            DEFAULT_VALUE_DATETIME = LocalDateTime.parse(DEFAULT_VALUE_DATETIME_AS_STRING, DATETIME_FORMATTER);
        } catch (ParseException e) {
            throw new RuntimeException("Error al inicializar las fechas y tiempos predeterminados", e);
        }
    }

    // Date Methods (yyyy-MM-dd)
    public static Date fromStringToDate(final String dateValue) throws ParseException {
        return DATE_FORMATTER.parse(dateValue);
    }

    public static String fromDateToString(final Date date) {
        return DATE_FORMATTER.format(date);
    }

    public static Date getDefaultValueDate() {
        return DEFAULT_VALUE_DATE;
    }

    public static String getDefaultValueDateAsString() {
        return DEFAULT_VALUE_DATE_AS_STRING;
    }

    public static boolean isValidDate(String dateValue) {
        try {
            DATE_FORMATTER.parse(dateValue);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // LocalTime Methods (HH:mm:ss)
    public static LocalTime fromStringToLocalTime(final String timeValue) {
        return LocalTime.parse(timeValue, TIME_FORMATTER);
    }

    public static String fromLocalTimeToString(final LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    public static LocalTime getDefaultValueTime() {
        return DEFAULT_VALUE_TIME;
    }

    public static String getDefaultValueTimeAsString() {
        return DEFAULT_VALUE_TIME_AS_STRING;
    }

    public static boolean isValidTime(String timeValue) {
        try {
            LocalTime.parse(timeValue, TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // LocalDateTime Methods (yyyy-MM-ddTHH:mm:ss)
    public static LocalDateTime fromStringToLocalDateTime(final String dateTimeValue) {
        return LocalDateTime.parse(dateTimeValue, DATETIME_FORMATTER);
    }

    public static String fromLocalDateTimeToString(final LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMATTER);
    }

    public static LocalDateTime getDefaultValueDateTime() {
        return DEFAULT_VALUE_DATETIME;
    }

    public static String getDefaultValueDateTimeAsString() {
        return DEFAULT_VALUE_DATETIME_AS_STRING;
    }

    public static boolean isValidDateTime(String dateTimeValue) {
        try {
            LocalDateTime.parse(dateTimeValue, DATETIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
