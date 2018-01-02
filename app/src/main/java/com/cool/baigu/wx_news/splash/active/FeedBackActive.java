package com.cool.baigu.wx_news.splash.active;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.cool.baigu.wx_news.IResponse.HttpResponse;
import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.adapt.FeedBackViewListAdapt;
import com.cool.baigu.wx_news.bean.FeedBackItem;
import com.cool.baigu.wx_news.constData.Const;
import com.cool.baigu.wx_news.utils.HttpUtil;
import com.cool.baigu.wx_news.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by baiguangan on 2017/12/5.
 */

public class FeedBackActive extends SwipeBackActivity {
    //回退界面
    private SwipeBackLayout mSwipeBackLayout;
    ListView listview;
    public static final String newsId = "newsId";
    private ArrayList<FeedBackItem> feedbackitems;

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedbackactive);
        //设置handler
        handler = new FeedBackHandler(this);
        //设置回退界面
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //其他设置
        listview = (ListView)findViewById(R.id.listview);
        //获取新闻Id
        String newsId = getIntent().getStringExtra(FeedBackActive.newsId);
        //获取数据
        HttpUtil<String> httpUtil = new HttpUtil<>();
        String s = Const.FeedBackUrl.replace("%D", newsId);
        //申请容器
        feedbackitems = new ArrayList<>();
        //获取数据
        httpUtil.getData(s, String.class, new HttpResponse<String>() {
            @Override
            public void onErr(String s) {

            }

            @Override
            public void onSucess(String jsonStr) {
                Log.d("kaka", "onSucess: " + jsonStr);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonStr);
                    JSONArray hotPosts = jsonObject.optJSONArray("hotPosts");
                    for (int i = 0; i < hotPosts.length(); ++i) {
                        JSONObject object = hotPosts.getJSONObject(i);
                        Iterator<String> keys = object.keys();
                        if (keys.hasNext()){
                            String key = keys.next();
                            Object o = object.get(key);
                            feedbackitems.add(Util.jsonParse(o.toString(), FeedBackItem.class));
                        }
                    }
                    handler.sendEmptyMessage(0);
                    //更新UI显示
                    Log.d("kaka", "onSucess: ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }
    //
    class FeedBackHandler extends Handler {
        WeakReference<FeedBackActive> feedbackactive;

        public FeedBackHandler(FeedBackActive f) {
            this.feedbackactive = new WeakReference<>(f);
        }

        @Override
        public void handleMessage(Message msg) {
            FeedBackActive active = feedbackactive.get();
            if (active == null) {
                return;
            }
            //处理事件
            active.listview.setAdapter(new FeedBackViewListAdapt(active.feedbackitems, active));

        }
    }

}