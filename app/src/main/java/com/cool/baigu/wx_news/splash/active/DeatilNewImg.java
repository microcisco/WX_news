package com.cool.baigu.wx_news.splash.active;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.adapt.ImgViewPage;
import com.cool.baigu.wx_news.bean.DetailNewItemImg;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by baiguangan on 2017/12/4.
 */

public class DeatilNewImg extends FragmentActivity {
    static final String imgs = "imgs";
    ViewPager vp_img;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.deatilnewimg_p);
        vp_img = (ViewPager)findViewById(R.id.vp_img);


        //
        ArrayList<View> views = new ArrayList<>();
        //获取数据
        ArrayList<DetailNewItemImg> imgsData = (ArrayList<DetailNewItemImg>) getIntent().getSerializableExtra(imgs);

        for (DetailNewItemImg img:imgsData
             ) {
            View view = View.inflate(this, R.layout.deatilnewimg, null);
            PhotoView hj = (PhotoView) view.findViewById(R.id.hj);
            Glide.with(this)
                    .load(img.getSrc())
                    .placeholder(R.drawable.base_refresh_mars_hole)
                    .crossFade()
                    .into(hj);
            views.add(view);
        }
        ImgViewPage ivg = new ImgViewPage(views, imgsData);
        vp_img.setAdapter(ivg);

    }


}
