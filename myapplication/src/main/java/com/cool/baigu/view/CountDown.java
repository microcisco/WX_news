package com.cool.baigu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by baigu on 2017/11/6.
 */

public class CountDown extends View {

    private TextPaint textPaint;
    private String str; //显示的字
    private float ly;   //内圆半径
    private float wy;   //外圆半径
    private int yhc = 10;   //圆弧的宽度
    private Paint paint;
    private int text_w;
    private int text_h;
    private int m_arc = 0;

    public CountDown(Context context) {
        super(context);
    }

    public CountDown(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(50);
        str = "跳过";
        Rect rect = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), rect);
        text_w = rect.width();
        text_h = rect.height();
        ly = (float) Math.sqrt(text_w * text_w + text_h * text_h);
        wy = ly + yhc * 2;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(yhc);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF oval=new RectF(yhc / 2, yhc / 2, wy, wy);
        //画圆弧
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(yhc);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, -90, m_arc, false, paint);
        //画圆
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(0f);
        float v = (float) (yhc * 1.5);
        oval=new RectF(yhc, yhc, ly + v, ly + v);
        canvas.drawArc(oval, 0, 360, false, paint);
        //画文字

        float y = canvas.getHeight() / 2;
        float de = textPaint.descent();
        float a = textPaint.ascent();

        float v1 = ((ly + v - yhc) / 2) + yhc + text_h / 2 ;
//        canvas.drawText(str, ((ly + v - yhc) / 2) + yhc - text_w / 2, (wy - yhc / 2) + ((de + a)) , textPaint);
        canvas.drawText(str, ((ly + v - yhc) / 2) + yhc - text_w / 2, (wy - yhc / 2) + ((de + a)) - 4 , textPaint);

    }

    public void SetArc(int arc) {
        m_arc = arc;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.5f);
                break;
            case MotionEvent.ACTION_UP:
                setAlpha(1f);
                break;
        }
        return true;
    }
}