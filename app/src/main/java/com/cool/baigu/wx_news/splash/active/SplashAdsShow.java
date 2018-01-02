package com.cool.baigu.wx_news.splash.active;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cool.baigu.wx_news.R;

/**
 * Created by baigu on 2017/11/1.
 */

public class SplashAdsShow extends Activity {
    public static final String ADS_ADDRESS = "ads_address";

    private WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_ads_webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra(ADS_ADDRESS);
        webview = (WebView)findViewById(R.id.webview);
        //开启javascript
        webview.getSettings().setJavaScriptEnabled(true);
        //加载url
        webview.loadUrl(url);
        //自己处理url重定向
        webview.setWebViewClient(new WebViewClient(){
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }
        });
    }

    //覆盖返回键
    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
            return;
        }
        super.onBackPressed();
    }
}
