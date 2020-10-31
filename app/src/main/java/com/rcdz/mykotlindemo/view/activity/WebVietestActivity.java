package com.rcdz.mykotlindemo.view.activity;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rcdz.mykotlindemo.R;
import com.wjt.mylibrary.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/30 13:13
 */
public class WebVietestActivity extends BaseActivity {
    WebView wb;
    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        wb=findViewById(R.id.testweb);
        WebSettings mWebSettings = wb.getSettings();
        mWebSettings.setSupportZoom(true);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("GBK");
        mWebSettings.setLoadsImagesAutomatically(true);
       mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //localStorage  允许存储
       mWebSettings.setDomStorageEnabled(true);
       mWebSettings.setAppCacheMaxSize(1024*1024*8);//存储的最大容量
        String appCachePath = this.getCacheDir().getAbsolutePath();
       mWebSettings.setAppCachePath(appCachePath);
       mWebSettings.setAllowFileAccess(true);
       mWebSettings.setAppCacheEnabled(true);
       mWebSettings.setJavaScriptEnabled(true);  //设置参数
       mWebSettings.setBuiltInZoomControls(true);
        wb.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
       mWebSettings.setBuiltInZoomControls(false);
       mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);//默认缩放模式
       mWebSettings.setSupportZoom(true);
       mWebSettings.setLoadWithOverviewMode(true);
       mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        String mylink = "http://192.168.1.170:9999/page/tmp/oauth2-client.html";
        wb.loadUrl(mylink);
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 加载页面
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                //安卓调用js方法。注意需要在 onPageFinished 回调里调用
                webView.post(new Runnable() {
                    String s="appUser1";
                    String s2="12安抚";
                    @Override
                    public void run() {
                        wb.evaluateJavascript("javascript:connect('" + s+"','"+s2 + "')", new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                Log.i("test",s);
                            }
                        });
                    }
                });
            }
        });

    }

    @NotNull
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void onClick(View view) {

    }
}
