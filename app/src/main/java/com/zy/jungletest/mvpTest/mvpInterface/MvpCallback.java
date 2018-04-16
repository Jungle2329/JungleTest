package com.zy.jungletest.mvpTest.mvpInterface;

import com.zy.jungletest.model.TranslationBean;

import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Jungle on 2018/4/16 0016.
 */

public interface MvpCallback {
    void onSuccess(Result<TranslationBean> data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
