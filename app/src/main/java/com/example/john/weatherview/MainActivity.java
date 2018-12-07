package com.example.john.weatherview;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView currTemp = null;
    private TextView stationName = null;
    private TextView lastUpdated = null;
    private TextView lastCheck = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.currTemp = findViewById(R.id.temperatureText);
        this.stationName = findViewById(R.id.stationName);
        this.lastUpdated = findViewById(R.id.lastUpdate);
        this.lastCheck = findViewById(R.id.lastCheck);

        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                stationName.setText("Fetching temperatures...");
                try {
                    Pair<String, String> values = Parser.parse();
                    lastUpdated.setText(values.first.replace("T", " ").replace("Z", ""));
                    currTemp.setText(values.second + " \u00b0C");
                    stationName.setText("Turku Artukainen");
                    lastCheck.setText("Data fetched: " + DateAndTime.getTime());
                } catch (Exception e) {
                    currTemp.setText("Error:\n" + e);
                }
            }
        }).start();
    }
}
