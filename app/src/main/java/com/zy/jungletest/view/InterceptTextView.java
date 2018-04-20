package com.zy.jungletest.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Jungle on 2018/4/19 0019.
 *
 * @desc TODO
 */

public class InterceptTextView extends android.support.v7.widget.AppCompatTextView {
    public InterceptTextView(Context context) {
        super(context);
    }

    public InterceptTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

}
