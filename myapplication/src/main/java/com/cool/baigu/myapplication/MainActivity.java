package com.cool.baigu.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cool.baigu.view.CountDown;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private CountDown down;
    int arc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        down = (CountDown) findViewById(R.id.k);


        handler = new Handler();


        Runnable task = new Runnable(){
            @Override
            public void run() {
                if(arc < 360) {
                    arc += 6;
                    down.SetArc(arc);
                    handler.postDelayed(this, 80);
                }
            }
        };

        handler.postDelayed(task, 0);

    }






}
