package com.Prince.forensicmart_admin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.Prince.forensicmart_admin.R;

public class OpenWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String string = getIntent().getStringExtra("link");
        WebView c= findViewById(R.id.webview);
        c.loadUrl(string);

/*
        WebView myWebView = new WebView(this);
        setContentView(myWebView);
        myWebView.loadUrl(string);
*/
    }
}