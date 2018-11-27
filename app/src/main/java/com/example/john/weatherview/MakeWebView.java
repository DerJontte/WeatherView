package com.example.john.weatherview;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MakeWebView {
    private WebView webView;

    public MakeWebView(WebView webView, MainActivity mainActivity) {
        this.webView = webView;
        this.webView.addJavascriptInterface(new JSInterface(mainActivity), "android");
    }

    public void create() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyBrowser());
        webView.loadUrl("file:///android_asset/parser.html");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
