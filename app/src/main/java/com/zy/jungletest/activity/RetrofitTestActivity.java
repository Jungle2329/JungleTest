package com.zy.jungletest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zy.jungletest.R;
import com.zy.jungletest.api.ApiService;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Jungle on 2017/4/5.
 */

public class RetrofitTestActivity extends AppCompatActivity {
    //    http://test.ldcang.com/api.php?app=api&api_version=3.3.8&mod=Goods&act=index&senceTime=0&listtype=2&page=1&auth=
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fab})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://test.ldcang.com")
//                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                ApiService mApiService = retrofit.create(ApiService.class);
                HashMap<String, String> map = new HashMap<>();
                map.put("mod", "Goods");
                map.put("act", "index");
                map.put("listtype", "2");
                map.put("page", "1");
                Call<String> call = mApiService.getInfo("api","3.3.8", map);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String model = response.body();
                        Log.i("zhangyi", model);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("zhangyi", t.toString());
                    }
                });
//                new BaseRetrofit() {
//                    @Override
//                    protected HashMap<String, Object> getParams() {
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("mod", "Goods");
//                        map.put("act", "index");
//                        map.put("listtype", "2");
//                        map.put("page", "1");
//                        return map;
//                    }
//
//                    @Override
//                    protected void onSuccess(Call<String> call, Response<String> response) {
//                        String model = response.body();
//                        Log.i("zhangyi", model);
//                    }
//
//                    @Override
//                    protected void onError(Call<String> call, Throwable t) {
//                        Log.i("zhangyi", t.toString());
//                    }
//                };
//                new BaseRetrofit() {
//                    @Override
//                    protected HashMap<String, Object> getParams() {
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("mod", "Goods");
//                        map.put("act", "index");
//                        map.put("listtype", "2");
//                        map.put("page", "1");
//                        return map;
//                    }
//
//                    @Override
//                    protected void onSuccess(Call<String> call, Response<String> response) {
//                        String model = response.body();
//                        Log.i("zhangyi", model);
//                    }
//
//                }.exe();

//                OkHttpUtils
//                        .get()
//                        .url("http://test.ldcang.com/api.php")
//                        .addParams("app", "api")
//                        .addParams("api_version", "3.3.9")
//                        .addParams("mod", "Activity")
//                        .addParams("act", "vipGoodsDetail")
//                        .addParams("gid", "4346")
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(okhttp3.Call call, Exception e, int id) {
//                                Log.i("zhangyi", "error");
//                            }
//
//                            @Override
//                            public void onResponse(String response, int id) {
//                                Log.i("zhangyi", response);
//                            }
//                        });


                break;
        }
    }
}
