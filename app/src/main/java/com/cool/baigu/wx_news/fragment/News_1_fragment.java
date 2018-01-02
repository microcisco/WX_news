package com.cool.baigu.wx_news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.baigu.wx_news.IResponse.HttpResponse;
import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.adapt.HotListAdapt;
import com.cool.baigu.wx_news.adapt.HotViewPageAdapt;
import com.cool.baigu.wx_news.bean.HotPageItemAdsData;
import com.cool.baigu.wx_news.bean.HotPageItemData;
import com.cool.baigu.wx_news.bean.HotPageMainData;
import com.cool.baigu.wx_news.constData.Const;
import com.cool.baigu.wx_news.splash.active.DetailNewsActive;
import com.cool.baigu.wx_news.utils.HttpUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by baigu on 2017/11/14.
 */

public class News_1_fragment extends Fragment {
    private static HotListAdapt hotListAdapt;
    ListView listView;
    ViewPager viewPager;
    private HotPageItemData carouselData;
    private ArrayList<HotPageItemData> itemsData;

    private New1Handler handler;
    private TextView pageviewtitle;

    private ArrayList<ImageView> xiaodians;
    private boolean isGetMoreData;
    private int getNewsDataNum = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_1, container, false);
        listView = (ListView) view.findViewById(R.id.listView);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int newState) {

                switch (newState){
                    case SCROLL_STATE_IDLE:
                        //滑动停止
                        try {
                            if(getContext() != null) Glide.with(getContext()).resumeRequests();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SCROLL_STATE_FLING:
                        //正在滚动
                        try {
                            if(getContext() != null) Glide.with(getContext()).pauseRequests();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        }); // 设置滚动时不加载图片。

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.hot_pageview, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewPage);
        pageviewtitle = (TextView) view.findViewById(R.id.pageviewtitle);
        listView.addHeaderView(view);
        xiaodians = new ArrayList<>();
        xiaodians.add((ImageView) view.findViewById(R.id.im0));
        xiaodians.add((ImageView) view.findViewById(R.id.im1));
        xiaodians.add((ImageView) view.findViewById(R.id.im2));
        xiaodians.add((ImageView) view.findViewById(R.id.im3));
        xiaodians.add((ImageView) view.findViewById(R.id.im4));


        //
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //判断顶部底部
                if (totalItemCount > 0 && ((firstVisibleItem + visibleItemCount) == totalItemCount)) {
                    if (!isGetMoreData && (getNewsDataNum * 20 + 10) >= totalItemCount - 1) {

                        getMoreNewsData();
                    }
                }
            }
        });

        handler = new New1Handler(this);

        HttpUtil<HotPageMainData> httpUtil = new HttpUtil<>();
        httpUtil.getData(Const.HOT_PAGE_URL, HotPageMainData.class, new HttpResponse<HotPageMainData>(){
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSucess(HotPageMainData s) {
                ArrayList<HotPageItemData> t1348647909107 = s.getT1348647909107();
                carouselData = t1348647909107.get(0);

                t1348647909107.remove(0);
                itemsData = t1348647909107;
                //通知更新UI
                handler.sendEmptyMessage(0);
            }
        });





        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HotPageItemData item = (HotPageItemData) hotListAdapt.getItem(position - listView.getHeaderViewsCount());

                Intent intent = new Intent();
                intent.setClass(getContext(), DetailNewsActive.class);
                intent.putExtra(DetailNewsActive.newsId, item.getDocid());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.active_in, R.anim.active_out);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("销毁", "onDestroy: kaka");
    }

    void getMoreNewsData() {
        if (isGetMoreData) {
            return;
        }
        isGetMoreData = true;
        ++getNewsDataNum;
        HttpUtil<HotPageMainData> httpUtil = new HttpUtil<>();

        String s = Const.HOT_PAGE__REPLACE_URL.replace("%S", String.valueOf(getNewsDataNum * 20));
        s = s.replace("%E", String.valueOf((getNewsDataNum + 1)* 20));

        Log.d("kaka", s);
        httpUtil.getData(s, HotPageMainData.class, new HttpResponse<HotPageMainData>(){
            @Override
            public void onErr(String s) {
                isGetMoreData = false;
            }

            @Override
            public void onSucess(HotPageMainData s) {
                ArrayList<HotPageItemData> t1348647909107 = s.getT1348647909107();
//                carouselData = t1348647909107.get(0);

                t1348647909107.remove(0);
                if(itemsData != null) {
                    itemsData.addAll(t1348647909107);
                    //通知更新UI
                    handler.sendEmptyMessage(0);
                    isGetMoreData = false;
                }
            }
        });
    }

    static class New1Handler extends Handler {
        WeakReference<News_1_fragment> fr;
        private HotViewPageAdapt adapter;

        New1Handler(News_1_fragment fr) {
            this.fr = new WeakReference<>(fr);
        }

        @Override
        public void handleMessage(Message msg) {
            News_1_fragment news_1_fragment = fr.get();
            if (news_1_fragment == null) {
                return;
            }

            if(adapter != null && news_1_fragment.getNewsDataNum > 0) {
                hotListAdapt.notifyDataSetChanged();
                return;
            }

            //更新显示
            final News_1_fragment news_1_fragment1 = this.fr.get();


            hotListAdapt = new HotListAdapt(news_1_fragment1.itemsData, news_1_fragment1.getActivity());
            news_1_fragment1.listView.setAdapter(hotListAdapt);

            ArrayList<ImageView> views = new ArrayList<>();


            if(news_1_fragment1.carouselData.getAds() != null) {

                for (HotPageItemAdsData ads:news_1_fragment1.carouselData.getAds()
                        ) {
                    View view1 = View.inflate(news_1_fragment1.getContext(), R.layout.hot_pageview_item, null);
                    ImageView view = (ImageView) view1.findViewById(R.id.tp);
                    views.add(view);
                    Glide.with(news_1_fragment1.getContext())
                            .load(ads.getImgsrc())
                            .placeholder(R.drawable.base_refresh_mars_hole)
                            .crossFade()
                            .into(view);
                }
                adapter = new HotViewPageAdapt(views);
                news_1_fragment1.viewPager.setAdapter(adapter);
                news_1_fragment.pageviewtitle.setText(news_1_fragment1.carouselData.getAds().get(0).getTitle());

                for (ImageView img:news_1_fragment1.xiaodians
                        ) {
                    img.setImageResource(R.drawable.pageviewdian);

                }
                news_1_fragment1.xiaodians.get(0).setImageResource(R.drawable.pageviewdian1);



            }

            //原代码
//            for (HotPageItemAdsData ads:news_1_fragment1.carouselData.getAds()
//                    ) {
//                View view1 = View.inflate(news_1_fragment1.getContext(), R.layout.hot_pageview_item, null);
//                ImageView view = (ImageView) view1.findViewById(R.id.tp);
//                views.add(view);
//                Glide.with(news_1_fragment1.getContext())
//                        .load(ads.getImgsrc())
//                        .placeholder(R.drawable.base_refresh_mars_hole)
//                        .crossFade()
//                        .into(view);
//            }
//            adapter = new HotViewPageAdapt(views);
//            news_1_fragment1.viewPager.setAdapter(adapter);
//            news_1_fragment.pageviewtitle.setText(news_1_fragment1.carouselData.getAds().get(0).getTitle());
//
//            for (ImageView img:news_1_fragment1.xiaodians
//                    ) {
//                img.setImageResource(R.drawable.pageviewdian);
//
//            }
//            news_1_fragment1.xiaodians.get(0).setImageResource(R.drawable.pageviewdian1);


            news_1_fragment1.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    news_1_fragment1.pageviewtitle.setText( news_1_fragment1.carouselData.getAds().get(position).getTitle());

                    for (ImageView img:news_1_fragment1.xiaodians
                            ) {
                        img.setImageResource(R.drawable.pageviewdian);

                    }
                    news_1_fragment1.xiaodians.get(position).setImageResource(R.drawable.pageviewdian1);

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                    Log.d("kaka", "onPageScrollStateChanged: " + state);

                }
            });


//            news_1_fragment1.itemsData


        }
    }

}