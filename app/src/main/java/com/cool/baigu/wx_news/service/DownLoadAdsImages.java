package com.cool.baigu.wx_news.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.cool.baigu.wx_news.bean.AdsContent;
import com.cool.baigu.wx_news.bean.AdsResult;
import com.cool.baigu.wx_news.utils.MD5Util;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static com.cool.baigu.wx_news.constData.Const.WX_NRES_CACHE;

/**
 * Created by baigu on 2017/10/28.
 */

public class DownLoadAdsImages extends IntentService {
    public static final String ADS_OBJ = "ads";

    //必须写默认构造函数
    public DownLoadAdsImages() {
        super("DownLoadAdsImages");
    }

    //启动时调用
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        AdsResult adsObj = (AdsResult) intent.getSerializableExtra(ADS_OBJ);
        ArrayList<AdsContent> ads = adsObj.getAds();
        for (AdsContent item:ads
             ) {
            ArrayList<String> res_url = item.getRes_url();
            String urlStr = res_url.get(0);
            if (!checkImageIsDownLoad(urlStr)) {
                downLoadImageByHttp(urlStr);
            }
        }
    }

    /**
     * 下载图片
     *
     * @param urlStr
     */
    private void downLoadImageByHttp(String urlStr) {
        try {
            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            if (bitmap != null) {
                saveToSDCache(bitmap, urlStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param bitmap
     * @param urlStr
     */
    private void saveToSDCache(Bitmap bitmap, String urlStr) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(externalStorageDirectory, WX_NRES_CACHE);
            if (!file.exists()) {
                file.mkdirs();
            }
            String fileName = getFileNameByUrl(urlStr);
            File targetFile = new File(file, fileName);
            if (!targetFile.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);  //压缩百分之四十
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件名根据url
     * @param urlStr
     */
    private String getFileNameByUrl(String urlStr) {
        return MD5Util.encrypt(urlStr);
    }

    /**
     * 获取文件名根据url
     *
     * @param urlStr
     */
    private boolean checkImageIsDownLoad(String urlStr) {
        File file = new File(new File(Environment.getExternalStorageDirectory(), WX_NRES_CACHE), getFileNameByUrl(urlStr));
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bitmap != null) {
                return true;
            }
        }
        return false;
    }
}
