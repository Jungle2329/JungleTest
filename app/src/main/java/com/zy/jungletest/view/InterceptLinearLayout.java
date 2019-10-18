package com.zy.jungletest.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Jungle on 2018/4/19 0019.
 *
 * @desc TODO
 */

public class InterceptLinearLayout extends LinearLayout {
    public InterceptLinearLayout(Context context) {
        this(context, null);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InterceptLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InterceptLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("zhangyi", "ll_parent->dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("zhangyi", "ll_parent->onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("zhangyi", "ll_parent->onTouchEvent");
        return super.onTouchEvent(event);
    }
}
