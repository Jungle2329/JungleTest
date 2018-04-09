package com.zy.jungletest.designpattern.FactoryPattern;

import android.util.Log;

import com.zy.jungletest.designpattern.SimpleFactoryPattern.Phone;

/**
 * Created by Jungle on 2018/3/19.
 */

public class Nokia extends Phone{
    @Override
    public void call() {
        Log.i("zhangyi", "耐操");
    }
}
