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
        String minute = twoDigits(date.get(Calendar.MINUTE));
        String second = twoDigits(date.get(Calendar.SECOND));
        return hour + separator + minute + separator + second;
    }

    public static String getDate() {
        String separator = "-";
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = twoDigits(date.get(Calendar.MONTH) + 1);
        String day = twoDigits(date.get(Calendar.DAY_OF_MONTH));
        return year + separator + month + separator + day;
    }

    public static String getCompleteDate() {
        return getCompleteDate(0);
    }

    public static String getCompleteDate(int hourDelta) {
        return getDate() + "T" + getTime(hourDelta) + "Z";
    }

    public static String extractTime(String completeDate) {
        return completeDate.split("T")[1].split("Z")[0];
    }

    public static String extractDate(String completeDate) {
        return completeDate.split("T")[0];
    }

    private static String twoDigits(int n){
        return ("0" + n).substring(String.valueOf(n).length() - 1);
    }
}
