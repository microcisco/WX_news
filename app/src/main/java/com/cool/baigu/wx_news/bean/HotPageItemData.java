package com.cool.baigu.wx_news.bean;

import java.util.ArrayList;

/**
 * Created by baigu on 2017/11/22.
 */

public class HotPageItemData {
    ArrayList<HotPageItemAdsData> ads;
    String img;
    String title;
    String digest;
    String source;
    int replyCount;
    String docid;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public ArrayList<HotPageItemAdsData> getAds() {
        return ads;
    }

    public void setAds(ArrayList<HotPageItemAdsData> ads) {
        this.ads = ads;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
