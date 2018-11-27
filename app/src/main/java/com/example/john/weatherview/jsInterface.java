package com.example.john.weatherview;

import android.webkit.JavascriptInterface;

public class jsInterface {
    @JavascriptInterface
    public String sendText(String text) {
        return text;
    }
}
