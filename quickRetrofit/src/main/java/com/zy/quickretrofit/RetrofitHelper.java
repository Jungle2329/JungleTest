package com.zy.quickretrofit;

import android.content.Context;

import java.util.HashMap;

import retrofit2.adapter.rxjava2.Result;


/**
 * Created by Jungle on 2018/4/4.
 */

public class RetrofitHelper {

    private Context context;
    private HashMap<String, String> map;
    private OnListener mOnListener;
    private Class apiClass;

    private RetrofitHelper(Context context, HashMap<String, String> map, OnListener mOnListener, Class apiClass) {
        this.context = context;
        this.map = map;
        this.mOnListener = mOnListener;
        this.apiClass = apiClass;
    }

    public void a(){
//        new BaseRetrofit
//                .Builder<>()
//                .setClient(context)
//                .build()
//                .getApi(BaseApi.class)
//                .getBaseDate(map)
//                .subscribe(new Observer<Result<BaseModel>>() {
//                })
//        new BaseRetrofit
//                .Builder()
//                .setClient(context)
//                .build()
//                .exe()
//                .getApi(apiClass)
//                .getBaseDate(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Result<T>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Result<T> baseModelResult) {
//                        Log.i("zhangyi", baseModelResult.response().code() + "");
//                        mOnListener.setAListener(baseModelResult);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mOnListener.setBListener(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }




    public interface OnListener {
        <T> void setAListener(Result<T> baseModelResult);

        void setBListener(Throwable e);
    }

    public static class Builder {

        private Context context;
        private HashMap<String, String> map;
        private OnListener setListener;
        private Class apiClass;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setBaseDate(HashMap<String, String> map) {
            this.map = map;
            return this;
        }

        public Builder setListener(OnListener setListener) {
            this.setListener = setListener;
            return this;
        }

        public Builder setApiClass(Class apiClass) {
            this.apiClass = apiClass;
            return this;
        }

        public RetrofitHelper build() {
            return new RetrofitHelper(context, map, setListener, apiClass);
        }
    }
}
