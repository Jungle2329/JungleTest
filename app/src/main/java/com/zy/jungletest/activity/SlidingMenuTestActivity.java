package com.zy.jungletest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zy.jungletest.R;
import com.zy.commonlibrary.utils.PxUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jungle on 2017/3/13.
 */

public class SlidingMenuTestActivity extends AppCompatActivity {

    @BindView(R.id.fl_parent)
    FrameLayout fl_parent;
    @BindView(R.id.fl_slide)
    FrameLayout fl_slide;

    private int currentMargin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);

        ButterKnife.bind(this);

        currentMargin = PxUtils.dip2px(SlidingMenuTestActivity.this, -250);

        final GestureDetector mGestureDetector = new GestureDetector(this, new MyOnGestureListener());

        fl_parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        private float lastX;
        private float lastY;

        public MyOnGestureListener() {
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
            Log.i("onScroll", "e1.getRawX(): " + e1.getRawX());
            Log.i("onScroll", "e1.getRawY(): " + e1.getRawY());
            Log.i("onScroll", "e2.getRawX(): " + e2.getRawX());
            Log.i("onScroll", "e2.getRawY(): " + e2.getRawY());
            Log.i("onScroll", "distanceX: " + distanceX);
            Log.i("onScroll", "distanceY: " + distanceY);

            if (lastX == 0 && lastY == 0) {
                lastX = e1.getRawX();
                lastY = e1.getRawY();
            }

            float mDistenceX = e2.getRawX() - lastX;
            float mDistenceY = e2.getRawY() - lastY;

            currentMargin += (int) mDistenceX;
            Log.i("currentMargin", "currentMargin: " + currentMargin);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(currentMargin, 0, 0, 0);
            fl_slide.setLayoutParams(params);
            fl_slide.postInvalidate();

            lastX = e2.getRawX();
            lastY = e2.getRawY();

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("zhangyi", "onFling");
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
            lastX = 0;
            lastY = 0;
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

    }
}
