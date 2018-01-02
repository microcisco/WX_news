package com.cool.baigu.wx_news.bean;

import java.io.Serializable;

/**
 * Created by baigu on 2017/10/25.
 */

public class ActionParam implements Serializable {
    String link_url;
    int fullscreen;

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public int getFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(int fullscreen) {
        this.fullscreen = fullscreen;
    }

    @Override
    public String toString() {
        return "ActionParam{" +
                "link_url='" + link_url + '\'' +
                ", fullscreen=" + fullscreen +
                '}';
    }
}
