package com.zy.jungletest.ui.activity.testActivity;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.commonlibrary.utils.AppUtils;
import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;
import com.zy.jungletest.view.DragView;

import butterknife.BindView;

/**
 * 手势检测测试类
 * Created by Jungle on 2017/3/13.
 */

public class GestureDetectorTestActivity extends BaseActivity{

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.ll_top)
    LinearLayout ll_top;
    @BindView(R.id.dragView)
    DragView dragView;
    private GestureDetector mGestureDetector;


    @Override
    protected int getViewId() {
        return R.layout.activity_gesture_detector;
    }

    @Override
    protected void initView() {
        mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    private void playAnimatorIn() {
        AnimationSet as = new AnimationSet(true);
        TranslateAnimation ta = new TranslateAnimation(0, 0, -AppUtils.dip2px(this, 400), 0);
        ta.setDuration(300);
        as.addAnimation(ta);
        ll_top.startAnimation(as);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void playAnimatorOut() {
        AnimationSet as = new AnimationSet(true);
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, -AppUtils.dip2px(this, 400));
        ta.setDuration(300);
        as.addAnimation(ta);
        ll_top.startAnimation(as);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ll_top.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        private float lastX;
        private float lastY;

        public MyOnGestureListener () {
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
            playAnimatorOut();
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

            if(lastX == 0 && lastY == 0) {
                lastX = e1.getRawX();
                lastY = e1.getRawY();
            }

            float mDistenceX = e2.getRawX() - lastX;
            float mDistenceY = e2.getRawY() - lastY;

            tv.setX(tv.getX() + mDistenceX);
            tv.setY(tv.getY() + mDistenceY);
//            tv.layout((int) (tv.getLeft() + mDistenceX), (int) (tv.getTop() + mDistenceY)
//                    , (int) (tv.getRight() + mDistenceX), (int) (tv.getBottom() + mDistenceY));
//            tv.postInvalidate();

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
            if(ll_top.getVisibility() == View.VISIBLE) {
                ll_top.setVisibility(View.GONE);
            } else {
                ll_top.setVisibility(View.VISIBLE);
            }

            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("zhangyi", "onSingleTapConfirmed");
//            ll_top.setVisibility(View.VISIBLE);
            return super.onSingleTapConfirmed(e);
        }

    }
}
