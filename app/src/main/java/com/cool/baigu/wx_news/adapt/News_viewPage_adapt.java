package com.cool.baigu.wx_news.adapt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by baigu on 2017/11/14.
 */

public class News_viewPage_adapt extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fs;
    public News_viewPage_adapt(FragmentManager fm, ArrayList<Fragment> fs) {
        super(fm);
        this.fs = fs;
    }

    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "标题11" + position;
    }






}
