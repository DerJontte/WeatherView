package com.example.john.weatherview;

import android.webkit.JavascriptInterface;
import android.widget.TextView;

public class JSInterface {
    MainActivity mainActivity;

    public JSInterface(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @JavascriptInterface
    public void setTimestamp(String text) {
        TextView lastUpdate = mainActivity.findViewById(R.id.lastUpdate);
        lastUpdate.setText(text);
    }

    @JavascriptInterface
    public void setTemp(String text) {
        TextView temperatureText = mainActivity.findViewById(R.id.temperatureText);
        temperatureText.setText(text);
    }

    @JavascriptInterface
    public void setStation(String text) {
        TextView stationName = mainActivity.findViewById(R.id.stationName);
        stationName.setText(text);
    }
}
