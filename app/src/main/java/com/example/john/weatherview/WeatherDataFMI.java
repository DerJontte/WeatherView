package com.example.john.weatherview;

public class WeatherDataFMI implements WeatherData {
    private String baseURL = "http://opendata.fmi.fi/wfs/fin";

    private void buildQuery() {

    }

    @Override
    public float getTemp() {
        return 0;
    }

    @Override
    public String getUpdateTime() {
        return null;
    }
}
