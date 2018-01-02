package com.cool.baigu.wx_news.IResponse;

/**
 * Created by baigu on 2017/11/21.
 */

public interface HttpResponse<T> {
    public void onErr(String s);
    public void onSucess(T s);
}
