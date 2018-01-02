package com.cool.baigu.wx_news.utils;

import android.support.annotation.NonNull;

import com.cool.baigu.wx_news.IResponse.HttpResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

//import android.support.annotation.RequiresApi;

/**
 * Created by baigu on 2017/11/21.
 */

public class HttpUtil<T> {
    private static final OkHttpClient client = new OkHttpClient();

    public void getData(String url, final Class<T> tt, final HttpResponse<T> httpResponse) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                httpResponse.onErr(e.getMessage());
            }

//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        httpResponse.onErr("请求返回失败");
                        return;
                    }

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    if (responseBody == null) {
                        httpResponse.onErr("请求返回失败");
                        return;
                    }

                    String jsonStr = responseBody.string();

                    if (tt == String.class) {
                        httpResponse.onSucess((T)jsonStr);
                        return;
                    }

                    T adsResult = Util.jsonParse(jsonStr, tt);
                    httpResponse.onSucess(adsResult);


                }
            }
        });
    }
}
