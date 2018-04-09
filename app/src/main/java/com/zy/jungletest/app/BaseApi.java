package com.zy.jungletest.app;

import com.zy.jungletest.model.BaseModel;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Jungle on 2018/4/3.
 */

public interface BaseApi {

    @GET("/api.php")
    <T extends BaseModel> Observable<Result<T>> getBaseDate(@QueryMap HashMap<String, String> map);

}
