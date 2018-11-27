package com.example.john.weatherview;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class WeatherDataFMI implements WeatherData {
    private String baseURL = "http://opendata.fmi.fi/wfs/fin";

    private void buildQuery() {

    }

    @Override
    public double getTemp() throws IOException {
        URL url = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return 0.0;
    }

    @Override
    public String getUpdateTime() {
        Calendar time = Calendar.getInstance();
        String timeStamp = time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE) + ":" + time.get(Calendar.SECOND);
        return String.valueOf(timeStamp);
    }

    @Override
    public String getStationName() {
        return null;
    }
}
