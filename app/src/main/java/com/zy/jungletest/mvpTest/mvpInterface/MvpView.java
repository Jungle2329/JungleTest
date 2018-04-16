package com.zy.jungletest.mvpTest.mvpInterface;

import com.zy.jungletest.model.TranslationBean;

import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Jungle on 2018/4/16 0016.
 */

public interface MvpView {
    /**
     * 显示正在加载进度框
     */
    void showLoading();
    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();
    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void showData(Result<TranslationBean> data);
    /**
     * 当数据请求失败后，调用此接口提示
     * @param msg 失败原因
     */
    void showFailureMessage(String msg);
    /**
     * 当数据请求异常，调用此接口提示
     */
    void showErrorMessage();
}