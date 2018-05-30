package com.zy.jungletest.proxyTest;

import android.util.Log;

/**
 * Created by Jungle on 2018/5/16 0016.
 *
 * @desc TODO
 */

public class OperationProxy implements ProxyInterface {

    @Override
    public void eat() {
        Log.i("zhangyi", "吃");
    }

    @Override
    public void drink() {
        Log.i("zhangyi", "喝");
    }

    @Override
    public void play() {
        Log.i("zhangyi", "玩");
    }

    @Override
    public void happy() {
        Log.i("zhangyi", "乐");
    }
}
