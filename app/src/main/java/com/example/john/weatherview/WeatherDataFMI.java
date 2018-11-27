package com.example.john.weatherview;

import java.io.IOException;
import java.util.Calendar;

public class WeatherDataFMI implements WeatherData {
    private String baseURL = "http://opendata.fmi.fi/wfs/fin";

    private String buildQuery() {
        Calendar time = Calendar.getInstance();
        String utcTime = time.get(Calendar.YEAR) + "-" + (time.get(Calendar.MONTH) + 1) + "-" + time.get(Calendar.DAY_OF_MONTH) + "T" +
                (time.get(Calendar.HOUR_OF_DAY) - 5) + ":" + doubleDigit(time.get(Calendar.MINUTE)) + ":" + doubleDigit(time.get(Calendar.SECOND)) + "Z";
        String query = "?service=WFS&version=2.0.0&request=getFeature&storedquery_id=fmi::observations::weather::timevaluepair&fmisid=100949&parameters=t2m&starttime=" + utcTime;
        return query;
    }

    @Override
    public String getTemp() throws IOException {
        NetAccess server = new NetAccess("http://opendata.fmi.fi/wfs/fin", buildQuery());

        String serverResponse = server.getServerData();
        return serverResponse;
    }

    @Override
    public String getUpdateTime() {
        Calendar time = Calendar.getInstance();
        String timeStamp = doubleDigit(time.get(Calendar.HOUR_OF_DAY)) + ":" + doubleDigit(time.get(Calendar.MINUTE)) + ":" + doubleDigit(time.get(Calendar.SECOND));
        return String.valueOf(timeStamp);
    }

    private String doubleDigit(int i) {
        String toReturn = "0" + i;
        toReturn = toReturn.substring(toReturn.length() - 2);
        return toReturn;
    }

    @Override
    public String getStationName() {
        return null;
    }
}
