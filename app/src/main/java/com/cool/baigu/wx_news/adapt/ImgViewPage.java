package com.cool.baigu.wx_news.adapt;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.cool.baigu.wx_news.bean.DetailNewItemImg;

import java.util.ArrayList;

/**
 * Created by baiguangan on 2017/12/4.
 */

public class ImgViewPage extends PagerAdapter {
    ArrayList<View> views;
    ArrayList<DetailNewItemImg> imgs;

    public ImgViewPage(ArrayList<View> views, ArrayList<DetailNewItemImg> imgs) {
        this.views = views;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(object != null) {
            container.removeView((View) object);
        }
    }
}
