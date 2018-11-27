package com.example.john.weatherview;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class WeatherDataFMI implements WeatherData {
    private String baseURL = "http://opendata.fmi.fi/wfs/fin";

    private String buildQuery() {
        Calendar time = Calendar.getInstance();
        String utcTime = time.get(Calendar.YEAR) + "-" + time.get(Calendar.MONTH) + "-" + time.get(Calendar.DAY_OF_MONTH) + "T" +
                         time.get(Calendar.HOUR_OF_DAY) + ":" + time.get(Calendar.MINUTE) + ":" + time.get(Calendar.SECOND) + "Z";
        String query = "?service=WFS&version=2.0.0&request=getFeature&storedquery_id=fmi::observations::weather::timevaluepair&fmisid=100949&parameters=t2m&starttime=" + utcTime;
        return query;
    }

    @Override
    public double getTemp() throws IOException {
        NetAccess server = new NetAccess("http://opendata.fmi.fi/wfs/fin");
        buildQuery();
//        String serverResponse = server.getServerData(buildQuery());
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
