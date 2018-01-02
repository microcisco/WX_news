package com.cool.baigu.wx_news.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cool.baigu.wx_news.splash.active.SplashActive;

import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

/**
 * Created by baigu on 2017/11/8.
 */

public class MyHandler extends Handler {
    WeakReference<SplashActive> act;

    public MyHandler(SplashActive act) {
        super();
        this.act = new WeakReference<>(act);
    }

    @Override
    public void handleMessage(Message msg) {
        SplashActive splashActive = this.act.get();  //获取active

        Log.d(TAG, "handleMessage: 草泥马");
        
        super.handleMessage(msg);
    }
}
