package com.zy.commonlibrary;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jungle on 2018/4/3.
 */

public class BaseRetrofit {

    private OkHttpClient mOkHttpClient;
    private String baseUrl;
    private Retrofit mRetrofit;

    private BaseRetrofit(OkHttpClient mOkHttpClient, String baseUrl) {
        this.mOkHttpClient = mOkHttpClient;
        this.baseUrl = baseUrl;
    }

    public BaseRetrofit exe() {
        mRetrofit = new Retrofit
                .Builder()
                .client(mOkHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return this;
    }


//    public void get(HashMap<String, String> map) {
//        mRetrofit
//                .create(BaseApi.class)
//                .getBaseDate(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Result<BaseModel>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Result<BaseModel> baseModelResult) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    public <T>T getApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }


    public static class Builder {
        private OkHttpClient mOkHttpClient;
        private String baseUrl;
        private final String BASEURL = "http://test.ldcang.com";

        public Builder setClient(Context mContext) {
            BasicParamsInterceptor mBasicParamsInterceptor = new BasicParamsInterceptor
                    .Builder()
                    .addParam("app", "api")
                    .addParam("api_version", "3.4.8")
                    .build();

            File cacheFile = new File(mContext.getCacheDir(), "HttpCache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //10Mb

            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(12, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)//断线重新连接
                    .addInterceptor(new MyLoggerInterceptor(""))
                    .addInterceptor(mBasicParamsInterceptor)
                    .cache(cache)
                    .build();
            return this;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public BaseRetrofit build() {
            if (TextUtils.isEmpty(baseUrl)) {
                baseUrl = BASEURL;
            }
            return new BaseRetrofit(mOkHttpClient, baseUrl);
        }
    }


}
