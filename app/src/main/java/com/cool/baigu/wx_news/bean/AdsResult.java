package com.cool.baigu.wx_news.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by baigu on 2017/10/25.
 */

public class AdsResult implements Serializable {
    ArrayList<AdsContent> ads;
    int next_req;
    int result;

    @Override
    public String toString() {
        return "AdsResult{" +
                "ads=" + ads +
                ", next_req=" + next_req +
                ", result=" + result +
                '}';
    }

    public ArrayList<AdsContent> getAds() {
        return ads;
    }

    public void setAds(ArrayList<AdsContent> ads) {
        this.ads = ads;
    }

    public int getNext_req() {
        return next_req;
    }

    public void setNext_req(int next_req) {
        this.next_req = next_req;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
