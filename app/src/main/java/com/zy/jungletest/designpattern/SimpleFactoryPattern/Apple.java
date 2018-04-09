package com.zy.jungletest.designpattern.SimpleFactoryPattern;

import android.util.Log;

/**
 * Created by Jungle on 2018/3/19.
 */

public class Apple extends Phone{
    @Override
    public void call() {
        Log.i("zhangyi", "贵");
    }
}
