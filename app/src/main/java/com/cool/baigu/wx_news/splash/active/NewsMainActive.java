package com.cool.baigu.wx_news.splash.active;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.fragment.MineFragment;
import com.cool.baigu.wx_news.fragment.NewsFragment;
import com.cool.baigu.wx_news.fragment.ReadingFragment;
import com.cool.baigu.wx_news.fragment.TopicFragment;
import com.cool.baigu.wx_news.fragment.VedioFragment;

/**
 * Created by baigu on 2017/11/9.
 */

public class NewsMainActive extends FragmentActivity {
    private FragmentTabHost tab_host;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_news_main_layout);

        tab_host = (FragmentTabHost)findViewById(R.id.tab_host);

        //1 绑定->fragment显示容器
        tab_host.setup(this, getSupportFragmentManager(), R.id.fragment_context);  //标签栏在下面这里会变

        //2生成标签  tag
        TabHost.TabSpec tabSpec = tab_host.newTabSpec("1");
        tabSpec.setIndicator(getSelectItem(this, R.drawable.item_1, "新闻"));

        TabHost.TabSpec tabSpec1 = tab_host.newTabSpec("2");
        tabSpec1.setIndicator(getSelectItem(this, R.drawable.item_2, ""));
//
        TabHost.TabSpec tabSpec2 = tab_host.newTabSpec("3");
        tabSpec2.setIndicator(getSelectItem(this, R.drawable.item_3, ""));

        TabHost.TabSpec tabSpec3 = tab_host.newTabSpec("4");
        tabSpec3.setIndicator(getSelectItem(this, R.drawable.item_4, ""));
//
        TabHost.TabSpec tabSpec4 = tab_host.newTabSpec("5");
        tabSpec4.setIndicator(getSelectItem(this, R.drawable.item_5, ""));


        tab_host.addTab(tabSpec, NewsFragment.class, null);
        tab_host.addTab(tabSpec1, MineFragment.class, null);
        tab_host.addTab(tabSpec2, ReadingFragment.class, null);
//
        tab_host.addTab(tabSpec3, TopicFragment.class, null);
        tab_host.addTab(tabSpec4, VedioFragment.class, null);


        fragmentManager = getFragmentManager();

        //标签切换事键
        tab_host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("ddd", "onTabChanged: " + tabId);

                fragmentManager.findFragmentByTag(tabId);


            }
        });

    }

    /**
     * 获取选项卡
     */
    private View getSelectItem(Context c, int resId, String name) {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.item_title, null);

        ImageView tt = (ImageView)view.findViewById(R.id.tt);
        TextView ttt = (TextView)view.findViewById(R.id.ttt);
        tt.setImageResource(resId);
        ttt.setText(name);
        return view;
    }

}
