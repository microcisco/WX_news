package com.cool.baigu.wx_news.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by baiguangan on 2017/12/4.
 */

public class DetailNewItem implements Serializable{
    ArrayList<DetailNewItemImg> img;
    String shareLink;
    String source;
    int threadVote;
    int threadAgainst;
    int replyCount;
    String title;
    String body;
    String category;
    String ptime;

    @Override
    public String toString() {
        return "DetailNewItem{" +
                "img=" + img +
                ", shareLink='" + shareLink + '\'' +
                ", source='" + source + '\'' +
                ", threadVote=" + threadVote +
                ", threadAgainst=" + threadAgainst +
                ", replyCount=" + replyCount +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", category='" + category + '\'' +
                ", ptime='" + ptime + '\'' +
                '}';
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public ArrayList<DetailNewItemImg> getImg() {
        return img;
    }

    public void setImg(ArrayList<DetailNewItemImg> img) {
        this.img = img;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getThreadVote() {
        return threadVote;
    }

    public void setThreadVote(int threadVote) {
        this.threadVote = threadVote;
    }

    public int getThreadAgainst() {
        return threadAgainst;
    }

    public void setThreadAgainst(int threadAgainst) {
        this.threadAgainst = threadAgainst;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
