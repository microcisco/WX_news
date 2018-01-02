package com.cool.baigu.wx_news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cool.baigu.wx_news.R;

/**
 * Created by baigu on 2017/11/9.
 */

public class MineFragment extends Fragment {
    static int a = 10;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        TextView viewById = (TextView)view.findViewById(R.id.textView3);
        viewById.setText("kaka" + a);
        ++a;
        return view;
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