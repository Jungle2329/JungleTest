package com.zy.jungletest.activity.testActivity;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.zy.jungletest.R;
import com.zy.jungletest.api.ApiRetrofit;
import com.zy.jungletest.base.BaseActivity;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Jungle on 2018/7/18 0018.
 *
 * @desc TODO
 */

public class SuperBigImageActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView iv_image;

    @Override
    protected int getViewId() {
        return R.layout.activity_big_image;
    }

    @Override
    protected void initView() {
        new Retrofit.Builder()
                .baseUrl("http://www.zcool.com.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(ApiRetrofit.class)
                .downloadImage("work/ZMjM3MDAwMjA=.html")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                        iv_image.setImageBitmap(BitmapFactory.decodeStream(response.body().byteStream()));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("zhangyi", "shibai");
                    }
                });

//        iv_image.setImageResource(R.drawable.long_img);
//        try {
//
//            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(,true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
