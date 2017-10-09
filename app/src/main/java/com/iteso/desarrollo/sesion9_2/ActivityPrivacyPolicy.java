package com.iteso.desarrollo.sesion9_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityPrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        WebView webView = (WebView) findViewById(R.id.activity_privacy_policy_webview);
        webView.loadUrl("file:///android_asset/PrivacyPolicy.html");
    }
}
