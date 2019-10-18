package com.zy.commonlibrary.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.zy.commonlibrary.utils.GeometryUtil;

/**
 * 粘性控件
 * Created by Jungle on 2017/12/6.
 */

public class GooView extends View {

    private float maxDistance = 600f;
    private Paint mPaint;
    //固定圆圆心
    private PointF stableCenter = new PointF(200f, 200f);
    //固定圆的半径
    private float stableRadius = 20f;
    //拖拽圆圆心
    private PointF dragCenter = new PointF(200f, 200f);
    //拖拽圆的半径
    private float dragRadius = 20f;
    //控制点
    private PointF controlPoint = new PointF(150f, 325f);
    //固定圆的两个附着点
    private PointF[] stablePoints = new PointF[]{
            new PointF(200f, 300f),
            new PointF(200f, 350f),
    };
    //拖拽圆的两个附着点
    private PointF[] dragPoints = new PointF[]{
            new PointF(100f, 300f),
            new PointF(100f, 350f),
    };
    private Path path;
    private int statusBarHeight;
    private boolean isOutOfRange = false;
    private boolean isDisappear = false;


    public GooView(Context context) {
        this(context, null);
    }

    public GooView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GooView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化画笔
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(25);

        path = new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        statusBarHeight = getStatusBarHeight(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //保存状态
        canvas.save();
        //向上移动画布
        canvas.translate(0, -statusBarHeight);

        calculatePoint();
        if (!isDisappear) {
            canvas.drawCircle(dragCenter.x, dragCenter.y, dragRadius, mPaint);
        }
        if (!isOutOfRange) {
            canvas.drawCircle(stableCenter.x, stableCenter.y, stableRadius, mPaint);
            //画出四个附着点
            //绘制不规则图形
            //移动到某个点，选取一个起点
            path.moveTo(stablePoints[0].x, stablePoints[0].y);
            //绘制点1到点2之间的曲线
            path.quadTo(controlPoint.x, controlPoint.y, dragPoints[0].x, dragPoints[0].y);
            //绘制点2到点3之间的直线
            path.lineTo(dragPoints[1].x, dragPoints[1].y);
            //绘制点3到点4之间的曲线
            path.quadTo(controlPoint.x, controlPoint.y, stablePoints[1].x, stablePoints[1].y);
            //封闭
            path.close();
            //绘制这个路径
            canvas.drawPath(path, mPaint);
            path.reset();
        }

        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDisappear = false;
                isOutOfRange = false;
                float rawX = event.getRawX();
                float rawY = event.getRawY();
                stableCenter.set(rawX, rawY);
                dragCenter.set(rawX, rawY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float distance = GeometryUtil.getDistanceBetween2Points(dragCenter, stableCenter);
                if (distance > maxDistance) {
                    isOutOfRange = true;
                }
                rawX = event.getRawX();
                rawY = event.getRawY();
                dragCenter.set(rawX, rawY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                distance = GeometryUtil.getDistanceBetween2Points(dragCenter, stableCenter);
                if (distance > maxDistance) {
                    isDisappear = true;
                } else {
                    //抬起手前没有超出最大范围,抬起手后也没有超出最大范围
                    //执行平移动画
                    //记录松开手的瞬间拖拽圆的圆心
                    final PointF oldDragCenterPoint = new PointF(dragCenter.x, dragCenter.y);
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(distance, 0);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            //获得值变化的百分比
                            float percent = animation.getAnimatedFraction();
                            //根据百分比获取新的圆心点
                            PointF newPointF = GeometryUtil.getPointByPercent(oldDragCenterPoint, stableCenter, percent);
                            //将新的圆心作为拖拽圆的圆心
                            dragCenter.set(newPointF.x, newPointF.y);
                            invalidate();
                        }
                    });
                    //设置动画插入器,使得动画执行到目标点后有一个超出的效果
                    valueAnimator.setInterpolator(new OvershootInterpolator(2));
                    valueAnimator.setDuration(300);
                    valueAnimator.start();
                }

                rawX = event.getRawX();
                rawY = event.getRawY();
                dragCenter.set(rawX, rawY);
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 计算会变化的点
     */
    private void calculatePoint() {
        //计算斜率
        double k = (stableCenter.y - dragCenter.y) / (stableCenter.x - dragCenter.x);
        //计算固定圆的两个连接点
        stablePoints = GeometryUtil.getIntersectionPoints(stableCenter, stableRadius, k);
        //计算拖拽圆的两个连接点
        dragPoints = GeometryUtil.getIntersectionPoints(dragCenter, dragRadius, k);
        //计算控制点
        controlPoint = GeometryUtil.getMiddlePoint(stableCenter, dragCenter);

    }

    //获取状态栏高度
    private int getStatusBarHeight(View view) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

}
