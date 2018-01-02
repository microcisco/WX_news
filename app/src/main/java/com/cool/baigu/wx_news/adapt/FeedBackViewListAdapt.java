package com.cool.baigu.wx_news.adapt;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.bean.FeedBackItem;

import java.util.ArrayList;

/**
 * Created by baigu on 2017/11/22.
 */

public class FeedBackViewListAdapt extends BaseAdapter {
    private ArrayList<FeedBackItem> feedbackitems;
    private Context ctx;

    public FeedBackViewListAdapt(ArrayList<FeedBackItem> datas, Context c) {
        feedbackitems = datas;
        ctx = c;
    }

    @Override
    public int getCount() {
        return feedbackitems.size();
    }

    @Override
    public Object getItem(int position) {
        return feedbackitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            View feedback_details = View.inflate(ctx, R.layout.feedback_details, null);
            holder.user_avater = (ImageView) feedback_details.findViewById(R.id.user_avater);
            holder.name = (TextView) feedback_details.findViewById(R.id.tv_name);
            holder.src = (TextView) feedback_details.findViewById(R.id.src);
            holder.content = (TextView) feedback_details.findViewById(R.id.content);
            v = feedback_details;
            v.setTag(holder);
        } else {
            v = convertView;
        }
        holder = (Holder) v.getTag();
        //更新评论UI
        FeedBackItem item = feedbackitems.get(position);
        //设置头像
        if (TextUtils.isEmpty(item.getTimg())) {
            Glide.with(ctx)
                    .load(item.getTimg())
                    .placeholder(R.drawable.base_refresh_mars_hole)
                    .crossFade()
                    .into(holder.user_avater);
        }
        //设置名字
        holder.name.setText(item.getN());
        //设置来源
        holder.src.setText(item.getF());
        //设置内容
        holder.content.setText(item.getB());
        return v;
    }
    //内部类
    class Holder {
        ImageView user_avater;  //头像
        TextView name;          //名字
        TextView src;           //来自哪里
        TextView content;       //评论内容
    }
}