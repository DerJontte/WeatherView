package com.example.john.weatherview;

import java.util.Calendar;

public class DateAndTime {

    public static String getTime() {
        return getTime(0);
    }

    public static String getTime(int hourDelta) {
        String separator = ":";
        Calendar date = Calendar.getInstance();
        String hour = twoDigits(date.get(Calendar.HOUR_OF_DAY) + hourDelta);
        if (Integer.valueOf(hour) < 0) {
            hour = String.valueOf(-(Integer.valueOf(hour) + 24));
        }
        String minute = twoDigits(date.get(Calendar.MINUTE));
        String second = twoDigits(date.get(Calendar.SECOND));
        return hour + separator + minute + separator + second;
    }

    public static String getDate(int dateDelta) {
        String separator = "-";
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = twoDigits(date.get(Calendar.MONTH) + 1);
        String day = twoDigits(date.get(Calendar.DAY_OF_MONTH) + dateDelta);
        return year + separator + month + separator + day;
    }

    public static String getCompleteDate() {
        return getCompleteDate(0);
    }

    public static String getCompleteDate(int hourDelta) {
        String queryTime = getTime(hourDelta);
        int dateDelta = 0;
        if (queryTime.substring(0, 1).equals("-")) {
            queryTime = queryTime.substring(1);
            dateDelta = -1;
        }
        return getDate(dateDelta) + "T" + queryTime + "Z";
    }

    public static String extractTime(String completeDate) {
        return completeDate.split("T")[1].split("Z")[0];
    }

    public static String extractDate(String completeDate) {
        return completeDate.split("T")[0];
    }

    public static String twoDigits(int n) {
        return ("0" + n).substring(String.valueOf(n).length() - 1);
    }
}
