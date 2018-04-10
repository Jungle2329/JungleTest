package com.zy.jungletest;

import android.app.Application;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/6.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("zhangyi", "MyApplication");
    }
}
