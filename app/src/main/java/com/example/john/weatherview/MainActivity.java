package com.example.john.weatherview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView currTemp = findViewById(R.id.temperatureText);
        final TextView stationName = findViewById(R.id.stationName);
        final TextView lastUpdated = findViewById(R.id.lastUpdate);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    currTemp.setText("Fetching temperatures...");
                    Pair<String, String> values = Parser.parse();
                    stationName.setText("Turku Artukainen");
                    lastUpdated.setText(values.first.replace("T", " ").replace("Z", ""));
                    currTemp.setText(values.second + " \u00b0C");
                } catch (Exception e) {
                    e.printStackTrace();
                    currTemp.setText("Error");
                }
            }
        }).start();
    }

}
