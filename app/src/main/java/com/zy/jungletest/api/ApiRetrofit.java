package com.zy.jungletest.api;

import com.zy.jungletest.model.RetrofitRxTestBean;
import com.zy.jungletest.model.TranslationBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Jungle on 2018/3/22.
 */

public interface ApiRetrofit {
    @GET("ajax.php?a=fy&f=auto&t=auto")
    Call<RetrofitRxTestBean> getCall(@Query("w") String words);

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<Result<TranslationBean>> getDate(@Field("i") String words);
}
