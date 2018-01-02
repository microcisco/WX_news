package com.cool.baigu.wx_news.splash.active;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.bean.AdsContent;
import com.cool.baigu.wx_news.bean.AdsResult;
import com.cool.baigu.wx_news.constData.Const;
import com.cool.baigu.wx_news.handler.MyHandler;
import com.cool.baigu.wx_news.service.DownLoadAdsImages;
import com.cool.baigu.wx_news.utils.MD5Util;
import com.cool.baigu.wx_news.utils.SharedPreferencesUtil;
import com.cool.baigu.wx_news.utils.Util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.cool.baigu.wx_news.constData.Const.WX_NRES_CACHE;
import static com.cool.baigu.wx_news.splash.active.SplashAdsShow.ADS_ADDRESS;

/**
 * Created by baigu on 2017/10/23.
 */

public class SplashActive extends Activity{

    MyHandler handler;

    private static final String SP_ADS_CACHE_NAME = "ads_config";
    private static final String SP_ADS_CACHE_TIME_NAME = "ads_config_time";
    private static final String SP_ADS_CACHE_INDEX = "ads_config_index";
    private final OkHttpClient client = new OkHttpClient();

    private ImageView im_ads_show;
    private String ads_click_link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置消息处理者
        handler = new MyHandler(this);

        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //布局必须在设置全屏之后
        setContentView(R.layout.active_splash);

        im_ads_show = (ImageView)findViewById(R.id.im_ads_show);


        String adsCache = (String) SharedPreferencesUtil.getParam(getApplicationContext(), SP_ADS_CACHE_NAME, "");
        if(TextUtils.isEmpty(adsCache)) {
            try {
                getAdsData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //查看是否过期
            long lastTime = (long) SharedPreferencesUtil.getParam(getApplicationContext(), SP_ADS_CACHE_TIME_NAME, 0L);
            AdsResult adsResult = Util.jsonParse(adsCache, AdsResult.class);
            int nextReq = adsResult.getNext_req();
            if(new Date().getTime() / 1000 > lastTime + nextReq) {
                try {
                    getAdsData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //显示广告
            im_ads_show.setImageBitmap(getAdsBitmap());
            //设置点击事件
            im_ads_show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SplashActive.this, SplashAdsShow.class);
                    intent.putExtra(ADS_ADDRESS, ads_click_link);
                    startActivity(intent);
                }
            });

        }

        //到时间自动跳转
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActive.this.goToMainActive();
            }
        }, 1000);

    }

    /**
     * 去主界面
     */
    private void goToMainActive() {
        Intent intent = new Intent(this, NewsMainActive.class);
        startActivity(intent);
        finish();
    }

    /**
     * @return
     */
    private Bitmap getAdsBitmap() {
        String adsCache = (String) SharedPreferencesUtil.getParam(getApplicationContext(), SP_ADS_CACHE_NAME, "");
        int index = (int)SharedPreferencesUtil.getParam(getApplicationContext(), SP_ADS_CACHE_INDEX, 0);
        AdsResult adsResult = Util.jsonParse(adsCache, AdsResult.class);
        if(index >= adsResult.getAds().size()) {
            index = 0;
        }
        AdsContent adsContent = adsResult.getAds().get(index);
        ads_click_link = adsContent.getAction_params().getLink_url();
        File file = new File(new File(Environment.getExternalStorageDirectory(), WX_NRES_CACHE), MD5Util.encrypt(adsContent.getRes_url().get(0)));
        SharedPreferencesUtil.setParam(getApplicationContext(), SP_ADS_CACHE_INDEX, ++index);
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public void getAdsData() throws Exception {
        Request request = new Request.Builder()
                .url(Const.ADS_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    assert responseBody != null;


                    String jsonStr = responseBody.string();
                    AdsResult adsResult = Util.jsonParse(jsonStr, AdsResult.class);
                    //数据有效就写入缓存
                    if (adsResult != null) {
                        SharedPreferencesUtil.setParam(getApplicationContext(), SP_ADS_CACHE_NAME, jsonStr);
                        SharedPreferencesUtil.setParam(getApplicationContext(), SP_ADS_CACHE_TIME_NAME, new Date().getTime() / 1000);
                    }
                    Intent intent = new Intent();
                    intent.setClass(SplashActive.this, DownLoadAdsImages.class);
                    intent.putExtra(DownLoadAdsImages.ADS_OBJ, adsResult);
                    startService(intent);




                }
            }
        });
    }
}
