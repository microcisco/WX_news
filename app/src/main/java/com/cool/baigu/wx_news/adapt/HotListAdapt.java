package com.cool.baigu.wx_news.adapt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cool.baigu.wx_news.R;
import com.cool.baigu.wx_news.bean.HotPageItemData;

import java.util.ArrayList;

/**
 * Created by baigu on 2017/11/22.
 */

public class HotListAdapt extends BaseAdapter {
    private ArrayList<HotPageItemData> datas;
    private Context ctx;

    public HotListAdapt(ArrayList<HotPageItemData> datas, Context c) {
        this.datas = datas;
        ctx = c;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotPageItemData thatData = datas.get(position);
        ViewHold vh;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.hot_new_item, null);
            vh = new ViewHold();
            vh.smailImage = (ImageView) convertView.findViewById(R.id.img);
            vh.title = (TextView) convertView.findViewById(R.id.title);
            vh.source = (TextView) convertView.findViewById(R.id.src);
            vh.comment = (TextView) convertView.findViewById(R.id.weiba);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHold) convertView.getTag();
        }
        //改变显示
        vh.title.setText(thatData.getTitle());
        vh.source.setText(thatData.getSource());
        vh.comment.setText(thatData.getReplyCount() + "跟帖");

        Glide.with(ctx)
                .load(thatData.getImg())
                .placeholder(R.drawable.base_refresh_mars_hole)
                .crossFade()
                .into(vh.smailImage);

        return convertView;
    }


    class ViewHold {
        ImageView smailImage;
        TextView title;
        TextView source;
        TextView comment;
    }

}
