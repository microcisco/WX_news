package com.cool.baigu.wx_news.bean;

/**
 * Created by baigu on 2017/11/22.
 */

public class HotPageItemAdsData {
    String imgsrc;
    String title;
    String docid;

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HotPageItemAdsData{" +
                "imgsrc='" + imgsrc + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
