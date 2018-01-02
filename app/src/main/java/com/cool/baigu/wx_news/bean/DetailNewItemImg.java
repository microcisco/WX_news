package com.cool.baigu.wx_news.bean;

import java.io.Serializable;

/**
 * Created by baiguangan on 2017/12/4.
 */

public class DetailNewItemImg implements Serializable {
    String ref;
    String src;
    String pixel;

    @Override
    public String toString() {
        return "DetailNewItemImg{" +
                "ref='" + ref + '\'' +
                ", src='" + src + '\'' +
                ", pixel='" + pixel + '\'' +
                '}';
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }
}
