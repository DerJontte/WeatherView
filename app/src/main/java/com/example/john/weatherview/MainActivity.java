package com.example.john.weatherview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MakeWebView webView = new MakeWebView((WebView) findViewById(R.id.webView), this);
        webView.create();
    }

}
