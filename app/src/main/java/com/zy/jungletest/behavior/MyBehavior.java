package com.zy.jungletest.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.zy.jungletest.R;

/**
 * Created by Jungle on 2017/8/4.
 */

public class MyBehavior extends CoordinatorLayout.Behavior {

    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return dependency.getId() == R.id.tv;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int parentX = parent.getWidth();
        int parentY = parent.getHeight();


        child.setX(parentX - dependency.getX());
        child.setY(parentY - dependency.getY());
        return true;
    }
}
