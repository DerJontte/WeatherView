package com.example.john.weatherview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;
import android.webkit.WebView;
import android.widget.TextView;
import org.xml.sax.SAXException;
import parser.Parser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView asema = findViewById(R.id.stationName);
        asema.setText("...");

        MakeWebView webView = new MakeWebView((WebView) findViewById(R.id.webView), this);
        webView.create();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    asema.setText("Fetching...");
                    Pair<String, String> values = Parser.parse();
                    asema.setText("Fetched...");
                    asema.setText(values.second);
                } catch (Exception e) {
                    e.printStackTrace();
                    asema.setText("Error");
                }
            }
        }).start();
    }

}
