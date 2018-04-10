package com.zy.jungletest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zy.jungletest.R;
import com.zy.commonlibrary.utils.PxUtils;

/**
 * 雷达五边形
 * Created by Jungle on 2017/11/17.
 */

public class RadarView extends View {

    //数据个数
    private int dataCount = 5;
    /**
     * 雷达半径
     */
    private float radius;
    /**
     * 中心点
     */
    private int centerX;
    /**
     * 中心点
     */
    private int centerY;

    private Paint mainPaint;
    private Paint fillPaint;
    private Paint titlePaint;
    private String[] title = {"邀请好友", "分享率", "活跃率", "购物能力", "好友消费"};
    private String[] score = {"95", "5", "95", "70", "100"};
    private float maxScore = 100f;
    //雷达图与标题的间距
    private int radarMargin = PxUtils.dip2px(getContext(), 8);


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setStrokeWidth(2f);
        mainPaint.setColor(Color.WHITE);
        mainPaint.setStyle(Paint.Style.STROKE);

        fillPaint = new Paint();
        fillPaint.setAntiAlias(true);
        fillPaint.setStrokeWidth(1f);
        fillPaint.setColor(getResources().getColor(R.color.half_transparent));
        fillPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        titlePaint = new Paint();
        titlePaint.setAntiAlias(true);
        titlePaint.setTextSize(PxUtils.dip2px(getContext(), 12));
        titlePaint.setColor(Color.WHITE);
        titlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //雷达图半径
//        radius = Math.min(h, w) / 2 * 0.5f;
        radius = PxUtils.dip2px(getContext(), 60);
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawTieLine(canvas);
        drawRegion(canvas);
        drawText(canvas);
    }

    /**
     * 画多边形外框
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path mPath = new Path();
        for (int i = 0; i < dataCount; i++) {
            if (i == 0) {
                mPath.moveTo(getPoint(i).x, getPoint(i).y);
            } else {
                mPath.lineTo(getPoint(i).x, getPoint(i).y);
            }

        }
        mPath.close();
        canvas.drawPath(mPath, mainPaint);
    }

    /**
     * 画多边形中点到各个定点的连线
     *
     * @param canvas
     */
    private void drawTieLine(Canvas canvas) {
        Path mPath = new Path();
        for (int i = 0; i < dataCount; i++) {
            mPath.moveTo(centerX, centerY);
            mPath.lineTo(getPoint(i).x, getPoint(i).y);
        }
        canvas.drawPath(mPath, mainPaint);
    }

    /**
     * 确定各个数据并画出边框填充出能力图
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path mPath = new Path();
        for (int i = 0; i < dataCount; i++) {
            //计算出当前数值达成百分比
            if (i == 0) {
                mPath.moveTo(getPoint(i, Float.valueOf(score[i]) / maxScore, 0).x, getPoint(i, Float.valueOf(score[i]) / maxScore, 0).y);
            } else {
                mPath.lineTo(getPoint(i, Float.valueOf(score[i]) / maxScore, 0).x, getPoint(i, Float.valueOf(score[i]) / maxScore, 0).y);
            }
        }
        mPath.close();
        canvas.drawPath(mPath, fillPaint);
    }

    private void drawText(Canvas canvas) {
        for (int i = 0; i < dataCount; i++) {
            int x1, x2;
            int y1, y2;
            x1 = x2 = getPoint(i, 1f, radarMargin).x;
            y1 = y2 = getPoint(i, 1f, radarMargin).y;
//            float textWidth = titlePaint.measureText(title[i]);
            Rect mRect1 = new Rect();
            Rect mRect2 = new Rect();
            titlePaint.getTextBounds(title[i], 0, title[i].length(), mRect1);
            float textHeight1 = mRect1.height();
            float textWidth1 = mRect1.width();
            titlePaint.getTextBounds(score[i], 0, score[i].length(), mRect2);
            float textHeight2 = mRect2.height();
            float textWidth2 = mRect2.width();
            if (i == 0) {
                //第一排
                y1 -= textHeight1 / 2;
                //第二排
                x2 += Math.abs(textWidth1 - textWidth2) / 2;
                y2 += textHeight1;
            } else if (i == 1) {
                //第一排
                //第二排
                x2 += Math.abs(textWidth1 - textWidth2) / 2;
                y2 += textHeight1 * 1.5;
            } else if (i == 2) {
                //第一排
                x1 -= textWidth1;
                //第二排
                x2 -= (Math.abs(textWidth1 - textWidth2) / 2) + textWidth2;
                y2 += textHeight1 * 1.5;
            } else if (i == 3) {
                //第一排
                x1 -= textWidth1;
                y1 -= textHeight1 / 2;
                //第二排
                x2 -= (Math.abs(textWidth1 - textWidth2) / 2) + textWidth2;
                y2 += textHeight1;
            } else if (i == 4) {
                //第一排
                x1 -= textWidth1 / 2;
                y1 -= textHeight1 * 1.5;
                //第二排
                x2 -= textWidth2 / 2;
            }
            canvas.drawText(title[i], x1, y1, titlePaint);
            canvas.drawText(score[i], x2, y2, titlePaint);
        }

    }

    private Point getPoint(int position) {
        return getPoint(position, 1f, 0);
    }

    /**
     * 五边形
     *
     * @param position
     * @return
     */
    private Point getPoint(int position, float percent, int radarMargin) {
        int x = 0;
        int y = 0;
        if (position == 0) {//第一象限
            x = (int) (centerX + ((radius + radarMargin) * Math.cos(getRadian(18))) * percent);
            y = (int) (centerY - ((radius + radarMargin) * Math.sin(getRadian(18))) * percent);

        } else if (position == 1) {//第二象限
            x = (int) (centerX + ((radius + radarMargin) * Math.sin(getRadian(36))) * percent);
            y = (int) (centerY + ((radius + radarMargin) * Math.cos(getRadian(36))) * percent);

        } else if (position == 2) {//第三象限
            x = (int) (centerX - ((radius + radarMargin) * Math.sin(getRadian(36))) * percent);
            y = (int) (centerY + ((radius + radarMargin) * Math.cos(getRadian(36))) * percent);

        } else if (position == 3) {//第四象限
            x = (int) (centerX - ((radius + radarMargin) * Math.cos(getRadian(18))) * percent);
            y = (int) (centerY - ((radius + radarMargin) * Math.sin(getRadian(18))) * percent);

        } else if (position == 4) {
            x = centerX;
            y = (int) (centerY - (radius + radarMargin) * percent);
        }

        return new Point(x, y);
    }

    /**
     * 把角度转换成弧度
     *
     * @param angle
     * @return
     */
    private double getRadian(int angle) {
        return 2 * Math.PI / 360 * angle;
    }

}
