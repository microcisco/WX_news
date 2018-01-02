package com.cool.baigu.wx_news.utils;

import com.google.gson.Gson;

/**
 * Created by baigu on 2017/10/26.
 */

public class Util {

    private static Gson gson;

    public static <T> T jsonParse(String jsonStr, Class<T> clzz) {
        if(null == gson) {
            gson = new Gson();
        }
        return gson.fromJson(jsonStr, clzz);
    }
}
