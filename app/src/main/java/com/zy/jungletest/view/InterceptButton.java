package com.zy.jungletest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Jungle on 2018/4/19 0019.
 *
 * @desc TODO
 */

public class InterceptButton extends android.support.v7.widget.AppCompatButton {


    public InterceptButton(Context context) {
        super(context);
    }

    public InterceptButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
//      return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
