package com.bawie.duqilin20191126;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawie.duqilin20191126.base.BaseActivity;

public class Main2Activity extends BaseActivity {

    private WebView webView;
    private String key;

    @Override
    protected void inittData() {
        //接受传过来的数据
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        webView.loadUrl(key);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

        });
        webView.setWebChromeClient(new WebChromeClient(){

        });
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.weba);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }
}
