package com.zy.jungletest.mvpTest.model;

import android.content.Context;

import com.zy.jungletest.api.ApiRetrofit;
import com.zy.jungletest.model.TranslationBean;
import com.zy.jungletest.mvpTest.mvpInterface.MvpCallback;
import com.zy.quickretrofit.RetrofitHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;


/**
 * Created by Jungle on 2018/4/16 0016.
 */

public class MVPModel {
    public static void getNetData(Context context, final String params, final MvpCallback callback) {

        new RetrofitHelper()
                .with(context)
                .setBaseUrl("http://fanyi.youdao.com/")
                .build()
                .create(ApiRetrofit.class)
                .getDate(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<TranslationBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TranslationBean> translationBeanResult) {
                        if (translationBeanResult.response().code() == 200) {
                            callback.onSuccess(translationBeanResult);
                        } else {
                            callback.onFailure(translationBeanResult.response().code() + "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }
}
