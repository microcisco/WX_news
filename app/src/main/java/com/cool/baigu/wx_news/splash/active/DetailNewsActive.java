package com.cool.baigu.wx_news.splash.active;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cool.baigu.wx_news.IResponse.HttpResponse;
import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.bean.DetailNewItem;
import com.cool.baigu.wx_news.bean.DetailNewItemImg;
import com.cool.baigu.wx_news.constData.Const;
import com.cool.baigu.wx_news.utils.HttpUtil;
import com.cool.baigu.wx_news.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by baigu on 2017/11/9.
 */

public class DetailNewsActive extends SwipeBackActivity {
    public static final String newsId = "newsId";

    private SwipeBackLayout mSwipeBackLayout;

    WebView f2;
    TextView tt1;
    EditText edittext;
    boolean editHasFocus = false;    //编辑框是否获取焦点
    RelativeLayout f3;
    public DetailNewItem newItem;
    TextView tj;
    Handler handler;

    @Override
    public void onBackPressed() {
        if(editHasFocus) {
            f3.requestFocus();
            return;
        }
        finish();
    }

//    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})

    @JavascriptInterface
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.details_news);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        f3 = (RelativeLayout)findViewById(R.id.f3);
        edittext = (EditText)findViewById(R.id.edittext);
        tt1 = (TextView)findViewById(R.id.tt1);
        f2 = (WebView)findViewById(R.id.f2);
        f2.getSettings().setJavaScriptEnabled(true);
        tj = (TextView)findViewById(R.id.tj);

        f2.addJavascriptInterface(this, "demo");    //添加接口

        handler = new DeatilNewsHandler(this);

        //获取文章id
        final String docId = getIntent().getStringExtra(newsId);
        //监听获取焦点
        edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editHasFocus = hasFocus;
                if (!hasFocus) {
                    //未获取焦点
                    tt1.setVisibility(View.VISIBLE);
                    tj.setVisibility(View.GONE);

                } else {
                    //已获取焦点
                    tt1.setVisibility(View.GONE);
                    tj.setVisibility(View.VISIBLE);

                }
            }
        });
        //跟帖点击按钮
        final DetailNewsActive detailNewsActive = this;
        tt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(detailNewsActive, FeedBackActive.class);
                intent.putExtra(FeedBackActive.newsId, docId);
                startActivity(intent);
            }
        });
        Log.d("kaka", "onCreate: " + docId);
        String s = Const.NEWS_DETAIL_INFO.replace("%D", docId);
        HttpUtil<String> httpUtil = new HttpUtil<>();
        httpUtil.getData(s, String.class, new HttpResponse<String>() {
            @Override
            public void onErr(String s) {
                Log.d("kaka", "onErr: shibai");

            }

            @Override
            public void onSucess(String s) {
                Log.d("kaka", "onSucess: " + s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String s1 = jsonObject.optJSONObject(docId).toString();
                    newItem = Util.jsonParse(s1, DetailNewItem.class);
                    handler.sendEmptyMessage(0);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @JavascriptInterface
    public void javaShow() {
        Intent intent = new Intent();
        intent.setClass(this,DeatilNewImg.class);
        intent.putExtra(DeatilNewImg.imgs,newItem.getImg());
        startActivity(intent);
    }

    //
    static class DeatilNewsHandler extends Handler {
        WeakReference<DetailNewsActive> fr;

        public DeatilNewsHandler(DetailNewsActive f) {
            fr = new WeakReference<>(f);
        }

        @Override
        public void handleMessage(Message msg) {
            DetailNewsActive active = fr.get();
            if (active == null) {
                return;
            }

            //替换图片地址
                    String body = active.newItem.getBody();
            for (DetailNewItemImg img : active.newItem.getImg()
                    ) {
                body = body.replace(img.getRef(),"<img src='"+ img.getSrc()+"'onclick='show()'/>");
            }
            //加上标题加上时间
            String titleHTML = "<p><span style='font-size:18px;'><strong>" + active.newItem.getTitle() + "</strong></span></p>";// 标题
            titleHTML = titleHTML+ "<p><span style='color:#666666;'>"+active.newItem.getSource()+"&nbsp&nbsp"+active.newItem.getPtime()+"</span></p>";//来源与时间
            body = titleHTML + body;

            //加上头尾
            body = "<html><head><style>img{width:100%}</style><script type='text/javascript'>function show(){alert(123); window.demo.javaShow()} </script></head><body>"
                    + body
                    + "</body></html>";
            //显示

            Log.d("kaka", "handleMessage: 草泥马");

            active.f2.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);

            active.tt1.setText(String.valueOf(active.newItem.getReplyCount()));

        }
    }

}
