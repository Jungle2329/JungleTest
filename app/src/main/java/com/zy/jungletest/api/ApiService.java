package com.zy.jungletest.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/4/5.
 */

public interface ApiService {
    @GET("/api.php")
    Call<String> getInfo(@Query("app") String app, @Query("api_version") String api_version, @QueryMap HashMap<String, String> map);
}
