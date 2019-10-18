package com.zy.jungletest.ui.activity.testActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.jungletest.R;
import com.zy.jungletest.api.ApiRetrofit;
import com.zy.jungletest.model.TranslationBean;
import com.zy.quickretrofit.MyLoggerInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http://test.ldcang.com/api.php?app=api&api_version=3.4.8
 * &mod=Thread&act=detail&type=&since_time=0&auth=MmM4ZHVKWWJYcEtNNHlIM3Ntais0d3ZWTUlLWFRadlIyOWZqVlVhTmtSelp4bzNQc0xidlZn&tid=15172
 * Created by Jungle on 2018/3/7.
 */

public class RetrofitRxActivity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    Button btn_get;
    @BindView(R.id.tv_result)
    TextView tv_result;
    @BindView(R.id.et_words)
    EditText et_words;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_head);
        ButterKnife.bind(this);

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = et_words.getText().toString().trim();
                if (!TextUtils.isEmpty(words)) {
                    getDate(words);
                } else {
                    Toast.makeText(RetrofitRxActivity.this, "请输入词语", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getDate(String words) {
        //http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
        File cacheFile = new File(getCacheDir(), "HttpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10); //10Mb
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new MyLoggerInterceptor(""))
                .cache(cache)
                .build();

        new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiRetrofit.class)
                .getDate(words)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<TranslationBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<TranslationBean> translationResult) {
                        System.out.println(translationResult.response().code());
                        tv_result.setText(translationResult.response().body().getTranslateResult().get(0).get(0).getTgt());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
