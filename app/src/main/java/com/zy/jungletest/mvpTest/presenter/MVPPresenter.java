package com.zy.jungletest.mvpTest.presenter;

import android.content.Context;

import com.zy.jungletest.model.TranslationBean;
import com.zy.jungletest.mvpTest.model.MVPModel;
import com.zy.jungletest.mvpTest.mvpInterface.MvpCallback;
import com.zy.jungletest.mvpTest.mvpInterface.MvpView;

import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Jungle on 2018/4/16 0016.
 */

public class MVPPresenter {

    private MvpView mMvpView;
    private Context context;

    public MVPPresenter(Context context,MvpView mMvpView) {
        this.mMvpView = mMvpView;
        this.context = context;
    }

    public void getData(String params) {
        mMvpView.showLoading();
        MVPModel.getNetData(context, params, new MvpCallback() {
            @Override
            public void onSuccess(Result<TranslationBean> data) {
                mMvpView.showData(data);
            }

            @Override
            public void onFailure(String msg) {
                mMvpView.showFailureMessage(msg);
            }

            @Override
            public void onError() {
                mMvpView.showErrorMessage();
            }

            @Override
            public void onComplete() {
                mMvpView.hideLoading();
            }
        });

    }
}
