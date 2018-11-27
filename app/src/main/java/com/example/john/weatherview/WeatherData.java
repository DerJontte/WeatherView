package com.example.john.weatherview;

import java.io.IOException;

public interface WeatherData {
    public String getTemp() throws IOException;
    public String getUpdateTime();
    public String getStationName();
}
