package com.zy.jungletest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zy.jungletest.R;

/**
 * Created by Jungle on 2017/1/24.
 */

public class MyView extends View {

    private int defalutSize = 100;
    private Paint mPaint;
    private Bitmap image;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        defalutSize = a.getDimensionPixelSize(R.styleable.MyView_default_size, 100);
        //最后记得将TypedArray对象回收
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(widthMeasureSpec);
        int height = getMySize(heightMeasureSpec);
        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int r = getMeasuredHeight() / 2;
        int centerX = getLeft() + r;
        int centerY = getTop() + r;


        mPaint.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, r, mPaint);
//        canvas.drawBitmap(image, 0, 0, mPaint);
//        canvas.drawCircle();
    }

    private int getMySize(int measureSpec) {
        int mySize = defalutSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            mySize = size;
        }

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: //如果没有指定大小，就设置为默认大小
                mySize = defalutSize;
                break;

            case MeasureSpec.AT_MOST: //如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;

            case MeasureSpec.EXACTLY: //如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
        }
        return mySize;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("zhangyi", "RawX:" + event.getRawX() + "------------RawY:" + event.getRawY());
        return super.onTouchEvent(event);
    }
}
