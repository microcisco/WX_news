package com.cool.baigu.wx_news.bean;

import java.io.Serializable;

/**
 * Created by baigu on 2017/10/25.
 */

public class FeedBackItem implements Serializable {
    //头像
    private String timg;
    //来源
    private String f;
    //点赞数量
    private String v;
    //时间
    private String t;
    //内容
    private String b;
    //名称
    private String n;
    //是否是vip
    private String vip;

    public String getTimg() {
        return timg;
    }

    public void setTimg(String timg) {
        this.timg = timg;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "FeedBackItem{" +
                "timg='" + timg + '\'' +
                ", f='" + f + '\'' +
                ", v='" + v + '\'' +
                ", t='" + t + '\'' +
                ", b='" + b + '\'' +
                ", n='" + n + '\'' +
                ", vip='" + vip + '\'' +
                '}';
    }
}