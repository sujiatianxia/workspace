package com.vgaw.canvaspaintdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by caojin on 2016/6/21.
 */

// 本文来自：
// http://blog.csdn.net/leejizhou/article/details/51524948
public class CanvasPaintView extends View {
    public CanvasPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 为了方便将Paint初始化放在onDraw()方法内，实际使用时应该事先初始化
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        // 抗锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);




        /*// 绘制实心矩形
        canvas.drawRect(50, 0, 150, 100, paint);
        // 绘制空心矩形
        paint.setStyle(Paint.Style.STROKE);
        // 起点实际上是边框的中心
        paint.setStrokeWidth(20);
        canvas.drawRect(50, 150, 150, 250, paint);
        // 下面两种等价
        //canvas.drawRect(new RectF(50, 150, 150, 250), paint);
        //canvas.drawRect(new Rect(50, 150, 150, 250), paint);*/




        /*// 绘制实心圆
        canvas.drawCircle(200, 50, 50, paint);
        // 绘制空心圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawCircle(200, 150, 50, paint);*/




        /*// 绘制实心圆角矩形
        canvas.drawRoundRect(new RectF(50, 50, 150, 150), 5, 5, paint);
        // 绘制空心圆角矩形，外边框圆角，内边框还是直角
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawRoundRect(new RectF(50, 200, 150, 300), 5, 5, paint);*/




        /*// 绘制实心椭圆
        canvas.drawOval(new RectF(50, 50, 250, 150), paint);
        // 绘制空心椭圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawOval(new RectF(50, 50, 250, 150), paint);*/





        /*// 实心圆弧，true表示将圆心包含在内
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawArc(new RectF(50, 50, 250, 150), 0, 30, true, paint);
        // 不将圆心包含在内
        //canvas.drawArc(new RectF(50, 50, 250, 150), 0, 270, false, paint);*/





        /*// 绘制文本
        paint.setTextSize(100);
        canvas.drawText("good luck", 50, 150, paint);
        canvas.drawLine(50, 150, 1000, 150, paint);*/
    }
}
