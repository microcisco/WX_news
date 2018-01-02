package com.cool.baigu.wx_news.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by baigu on 2017/10/25.
 */

public class AdsContent implements Serializable {
    int id;
    float ratio;
    int show_num;
    String sub_title;
    ArrayList<String> res_url;
    ActionParam action_params;
    long expired_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public int getShow_num() {
        return show_num;
    }

    public void setShow_num(int show_num) {
        this.show_num = show_num;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public ArrayList<String> getRes_url() {
        return res_url;
    }

    public void setRes_url(ArrayList<String> res_url) {
        this.res_url = res_url;
    }

    public ActionParam getAction_params() {
        return action_params;
    }

    public void setAction_params(ActionParam action_params) {
        this.action_params = action_params;
    }

    public long getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(long expired_time) {
        this.expired_time = expired_time;
    }

    @Override
    public String toString() {
        return "AdsContent{" +
                "id=" + id +
                ", ratio=" + ratio +
                ", show_num=" + show_num +
                ", sub_title='" + sub_title + '\'' +
                ", res_url=" + res_url +
                ", action_params=" + action_params +
                ", expired_time=" + expired_time +
                '}';
    }
}
