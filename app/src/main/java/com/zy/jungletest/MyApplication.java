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

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor(""))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                //其他配置
//                .build();
//
//        OkHttpUtils.initClient(okHttpClient);
        Log.i("zhangyi", "MyApplication");
    }
}
