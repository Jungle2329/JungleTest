package com.zy.jungletest.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;

import com.zy.jungletest.utils.AppUtils;

/**
 * Created by Jungle on 2018/8/31 0031.
 *
 * @desc TODO
 */

public class DragView extends FrameLayout {

    private GestureDetector mGestureDetector;
    private VelocityTracker mVelocityTracker;
    private Context context;
    private float lastX = 0f;
    private float lastY = 0f;

    public DragView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public DragView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public DragView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(context, new MyGestureDetectorListener());
        mVelocityTracker = VelocityTracker.obtain();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    class MyGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {

        public MyGestureDetectorListener() {
            super();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("zhangyi", "onSingleTapUp");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("zhangyi", "onLongPress");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (lastX == 0 && lastY == 0) {
                lastX = e1.getRawX();
                lastY = e1.getRawY();
            }

            float mDistenceX = e2.getRawX() - lastX;
            float mDistenceY = e2.getRawY() - lastY;
            Log.i("onScroll", "getTop()" + getTop());
            Log.i("onScroll", "getBottom()" + getBottom());
            if(getTop() <= 0) {
//            setX(getX() + mDistenceX);
                if(mDistenceY > 0) {
                    setY(getY() + mDistenceY);
                }
            } else if(getBottom() >= AppUtils.getWindowWidth(context)) {
                if(mDistenceY < 0) {
                    setY(getY() + mDistenceY);
                }
            } else {
                setY(getY() + mDistenceY);
            }

            lastX = e2.getRawX();
            lastY = e2.getRawY();
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("onFling", "velocityX" + velocityX);
            Log.i("onFling", "velocityY" + velocityY);
            float mDistenceX = e2.getRawX() - lastX;
            float mDistenceY = e2.getRawY() - lastY;
//            setX(getX() + mDistenceX);
            setY(getY() + mDistenceY);

            lastX = e2.getRawX();
            lastY = e2.getRawY();
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("zhangyi", "onShowPress");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("zhangyi", "onDown");
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("zhangyi", "onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("zhangyi", "onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("zhangyi", "onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Log.i("zhangyi", "onContextClick");
            return super.onContextClick(e);
        }
    }
}
