package com.zy.jungletest.decrypt;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request.Builder;
import okhttp3.Response;


/**
 * Created by Jungle on 2019/12/2 0002.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */
public class BusinessRequestInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Builder newBuilder = chain.request().newBuilder();
        newBuilder.addHeader("token", "f0ad02c924c7791af6ddd223added61a");
        newBuilder.addHeader("ip", "10.10.17.41");
        newBuilder.addHeader("version", "4.2.1");
        newBuilder.addHeader("appType", "0");
        newBuilder.addHeader("reqSource", "1");
        return chain.proceed(newBuilder.build());
    }
}