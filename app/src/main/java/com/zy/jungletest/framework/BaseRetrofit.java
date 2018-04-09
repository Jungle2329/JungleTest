package com.zy.jungletest.framework;

import android.content.Context;


/**
 * Created by Jungle on 2017/4/5.
 */

public class BaseRetrofit {

    public static BaseRetrofit mInstance;

    public static BaseRetrofit getInstance(Context context) {
        if (mInstance == null) {
            synchronized (BaseRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new BaseRetrofit(context);
                }
            }
        }
        return mInstance;
    }

    private  BaseRetrofit(Context context) {

//        File cacheFile = new File(context.getCacheDir(), "HttpCache");
//        Log.d("zgx", "cacheFile=====" + cacheFile.getAbsolutePath());
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //10Mb
//        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(12, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
//                .addInterceptor(mHttpLogInterceptor)
//                .addInterceptor(mBaseParamsInterceptor)
//                .addInterceptor(mUrlInterceptor)
//                .cache(cache)
//                .build();
//
//        mRetrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(mGson))
//                .client(mOkHttpClient)
//                .build();

//        return new Retrofit.Builder()
//                .baseUrl("http://test.ldcang.com")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build()
//                .create(ApiService.class)
//                .getInfo("api", "3.3.8", getParams());


    }



//    protected abstract HashMap<String, Object> getParams();

//    protected abstract void onSuccess(Call<String> call, Response<String> response);

//    protected abstract void onFailure(Call<String> call, Throwable t);

//    public void exe() {
//        getCall().enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                onSuccess(call, response);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }

}
