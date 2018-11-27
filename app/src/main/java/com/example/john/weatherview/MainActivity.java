package com.example.john.weatherview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MakeWebView webView = new MakeWebView((WebView) findViewById(R.id.webView));
        webView.create();

        WeatherData weatherData = new WeatherDataFMI();

        TextView stationName = findViewById(R.id.stationName);
        TextView lastUpdate = findViewById(R.id.lastUpdate);
        TextView temperatureText = findViewById(R.id.temperatureText);

        stationName.setText(weatherData.getStationName());
        lastUpdate.setText(weatherData.getUpdateTime());
        temperatureText.setText(String.valueOf(weatherData.getTemp()));
    }

}
