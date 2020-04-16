package com.zy.jungletest.api;

import com.zy.jungletest.decrypt.BaseModelReq;
import com.zy.jungletest.model.RetrofitRxTestBean;
import com.zy.jungletest.model.TranslationBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Jungle on 2018/3/22.
 */

public interface ApiRetrofit {
    @GET("ajax.php?a=fy&f=auto&t=auto")
    Observable<Result<RetrofitRxTestBean>> getCall(@Query("w") String words);

    //不支持背压
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<Result<TranslationBean>> getDate(@Field("i") String words);

    //支持背压
    @POST("test")
    @FormUrlEncoded
    Flowable<Result<TranslationBean>> supportBackPresses(
            @FieldMap HashMap<String, String> pararms);

    @Streaming
    @GET
    Call<ResponseBody> downloadImage(@Url String fileUrl);

    // zjtx接口
    @POST("class/app/classLibrary/classlookInfo")
    Observable<ResponseBody> recordCourseTime(@Body BaseModelReq baseModelReq);

}
