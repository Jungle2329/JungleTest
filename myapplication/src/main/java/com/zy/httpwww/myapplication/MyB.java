package com.zy.httpwww.myapplication;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by Jungle on 2017/8/4.
 */

public class MyB extends CoordinatorLayout.Behavior{
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return dependency.getId() == R.id.action_settings;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
