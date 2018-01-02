package com.cool.baigu.wx_news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.adapt.News_viewPage_adapt;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

/**
 * Created by baigu on 2017/11/9.
 */

public class NewsFragment extends Fragment {

    private ViewPager vp_news;
    private FrameLayout layout;
    private News_viewPage_adapt adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        Log.d("kaka", "onCreateView: ");

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("kaka", "onActivityCreated: ");

        FragmentActivity activity = getActivity();

        //初始化viewPage
        vp_news = (ViewPager)activity.findViewById(R.id.vp_news);
        ArrayList<Fragment> fs = new ArrayList<>();
        fs.add(new News_1_fragment());

        fs.add(new News_2_fragment());

        adapter = new News_viewPage_adapt(getChildFragmentManager(), fs);
        vp_news.setAdapter(adapter);


        //
        layout = (FrameLayout)activity.findViewById(R.id.tabs);
        layout.addView(View.inflate(activity, R.layout.include_tab, null));
        SmartTabLayout sl = (SmartTabLayout) activity.findViewById(R.id.smart_tab);
        sl.setViewPager(vp_news);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {


        super.onStop();

    }
}
