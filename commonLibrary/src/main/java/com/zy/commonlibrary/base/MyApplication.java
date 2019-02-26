package com.zy.commonlibrary.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.BuildConfig;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Jungle on 2017/4/6.
 */

public class MyApplication extends Application {

    private static MyApplication appContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initARouter();
        Fresco.initialize(this);
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(appContext); // 尽可能早，推荐在Application中初始化
    }

    public static MyApplication getAppContext() {
        return appContext;
    }
}
