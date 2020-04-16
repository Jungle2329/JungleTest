package com.zy.jungletest.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.annotationslibrary.EasyLog;
import com.zy.commonlibrary.utils.AESUtil;
import com.zy.commonlibrary.utils.AtTextView;
import com.zy.jungletest.R;
import com.zy.jungletest.annotationTest.MethodInfo;
import com.zy.jungletest.annotationTest.Season;
import com.zy.jungletest.api.ApiRetrofit;
import com.zy.jungletest.base.BaseActivity;
import com.zy.jungletest.database.DatabaseHelper;
import com.zy.jungletest.decrypt.ModifyZJTX;
import com.zy.jungletest.layoutManager.FlowLayoutManager;
import com.zy.jungletest.model.RetrofitRxTestBean;
import com.zy.jungletest.model.TranslationBean;
import com.zy.jungletest.mvpTest.activity.MVPTestActivity;
import com.zy.jungletest.proxyTest.ProxyTest;
import com.zy.jungletest.ui.activity.launchMode.LaunchModeTestActivity;
import com.zy.jungletest.ui.activity.testActivity.AsyncTaskActivity;
import com.zy.jungletest.ui.activity.testActivity.CustomViewActivity;
import com.zy.jungletest.ui.activity.testActivity.ExifInterfaceActivity;
import com.zy.jungletest.ui.activity.testActivity.GestureDetectorTestActivity;
import com.zy.jungletest.ui.activity.testActivity.HandlerTestActivity;
import com.zy.jungletest.ui.activity.testActivity.JsonFileLoader;
import com.zy.jungletest.ui.activity.testActivity.KotlinTestActivity;
import com.zy.jungletest.ui.activity.testActivity.LayoutManagerTestActivity;
import com.zy.jungletest.ui.activity.testActivity.MultipleViewPagerManagerActivity;
import com.zy.jungletest.ui.activity.testActivity.NestedScrollViewTestActivity;
import com.zy.jungletest.ui.activity.testActivity.RadarActivity;
import com.zy.jungletest.ui.activity.testActivity.RetrofitRxActivity;
import com.zy.jungletest.ui.activity.testActivity.RxJavaLearnActivity;
import com.zy.jungletest.ui.activity.testActivity.SlidingMenuTestActivity;
import com.zy.jungletest.ui.activity.testActivity.SuperBigImageActivity;
import com.zy.jungletest.ui.activity.testActivity.TestGridViewActivity;
import com.zy.jungletest.ui.activity.testActivity.TestLolipopDemoActivity;
import com.zy.jungletest.ui.activity.testActivity.TextureViewDemo;
import com.zy.jungletest.ui.activity.testActivity.TouchEventTestActivity;
import com.zy.quickretrofit.RetrofitHelper;

import org.json.JSONException;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

@EasyLog(tag = "Zhang", value = "Yi")
@Route(path = "/main/mainActivity")
public class MainActivity extends BaseActivity {

    @BindView(R.id.atv)
    AtTextView atv;
    @BindView(R.id.rv_main)
    RecyclerView rv_main;

    /**
     * 保存数据测试
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("hello", "world");
        Log.i("zhangyi", "true");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String hello = savedInstanceState.getString("hello");
            Log.i("zhangyi", hello);
        }
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        rv_main.setLayoutManager(new FlowLayoutManager());
        rv_main.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.bt01, R.id.bt02, R.id.bt03, R.id.bt04, R.id.bt05
            , R.id.bt06, R.id.bt07, R.id.bt08, R.id.bt10
            , R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15
            , R.id.bt16, R.id.bt17, R.id.bt18, R.id.bt19, R.id.bt20
            , R.id.bt21, R.id.bt22, R.id.bt23, R.id.bt24
            , R.id.bt25, R.id.bt26, R.id.bt27, R.id.bt28, R.id.bt29
            , R.id.bt30})
    public void onClick(View view) {
        Intent mIntent = new Intent();
        switch (view.getId()) {
            case R.id.bt01:
                mIntent.setClass(this, TestGridViewActivity.class);
                startActivity(mIntent);
                break;
            case R.id.bt02:
                mIntent.setClass(this, HandlerTestActivity.class);
                startActivity(mIntent);
                break;
            case R.id.bt03://5.0效果测试
                mIntent.setClass(this, TestLolipopDemoActivity.class);
                startActivity(mIntent);
                break;
            case R.id.bt04://TextureVie
                mIntent.setClass(this, TextureViewDemo.class);
                startActivity(mIntent);
                break;
            case R.id.bt05:
                mIntent.setClass(this, CustomViewActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt06://数据库测试
                DatabaseHelper helper = new DatabaseHelper(this);
                SQLiteDatabase db = helper.getWritableDatabase();

                //查询
                Cursor cursor = db.query("ldcang", null, null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    int personid = cursor.getInt(0); //获取第一列的值,第一列的索引从0开始
                    String name = cursor.getString(1);//获取第二列的值
                    int age = cursor.getInt(2);//获取第三列的值
                    String flag = cursor.getString(3);
                    Log.i("zhangyi", "personid: " + personid);
                    Log.i("zhangyi", "name: " + name);
                    Log.i("zhangyi", "age: " + age);
                    Log.i("zhangyi", "flag: " + flag);
                }
                cursor.close();
                db.close();

//              增加字段
//              db.execSQL("insert into ldcang values(1, 'zhangyi' ,26 ,'true')");
//              db.close();
                break;

            case R.id.bt07://手势测试
                mIntent.setClass(this, GestureDetectorTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt08://SlidingMenu
                mIntent.setClass(this, SlidingMenuTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt10:
//                Log.i("zhangyi", "" + AESUtil.decryptBase64("qiQhxc79AiJOZ3qSUOVtEQ==", "U1MjU1M0FDOUZ.Qz", ""));
                Log.i("zhangyi", AESUtil.encrypt("123", "ldcangldcang", "ldcangldcang"));
                break;

            case R.id.bt11://正则测试
                String html = "07-11 09:58:32.162 15911-15911/com.etwod.wpxia I/zhangyi: shouldOverrideUrlLoading:https://maliprod.alipay.com/w/trade_pay.do?tcode=eyJiaXpPcmRlcklkcyI6IjM1OTEwMDg4NDM5NzE4ODczIiwiYnV5ZXJJZCI6IjI4MzcxNzM4OCIsInR5cGUiOiIzIn0=&alipay_trade_no=2017071121001001620243586011&s_id=15cc43ab997317ce526d95fbee158c6c&return_url=https%3A%2F%2Fh5.m.taobao.com%2Fapp%2Ftrade%2Fpaysuc.html%3F_wx_tpl%3Dhttps%3A%2F%2Fowl.alicdn.com%2Fmupp-dy%2Fdevelop%2Ftaobao%2Ftrade%2FpaySuccess%2Fmain.js%26orderIds%3D35910088439718873%26degrade%3D0%26act%3Dfalse&pay_order_id=35910088439718873&sid=15cc43ab997317ce526d95fbee158c6c";
                Pattern pattern = Pattern.compile("&pay_order_id=([0-9]+)");
                Matcher matcher = pattern.matcher(html);
                if (matcher.find()) {
                    String group = matcher.group();
                    String[] results = group.split("=");
                    String result = results[1];
                    Log.i("HTMLOUT", result);
                }
                break;

            case R.id.bt12:
                //使用flatMap实现多个接口依次请求
                String url = "http://fanyi.youdao.com/";
                new RetrofitHelper()
                        .with(this)
                        .setBaseUrl(url)
                        .build()
                        .create(ApiRetrofit.class)
                        .getDate("1")
                        .flatMap(new Function<Result<TranslationBean>, ObservableSource<Result<RetrofitRxTestBean>>>() {
                            @Override
                            public ObservableSource<Result<RetrofitRxTestBean>> apply(Result<TranslationBean> translationBeanResult) throws Exception {
                                return new RetrofitHelper()
                                        .with(MainActivity.this)
                                        .build()
                                        .create(ApiRetrofit.class)
                                        .getCall("2");
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Result<RetrofitRxTestBean>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Result<RetrofitRxTestBean> retrofitRxTestBeanResult) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;

            case R.id.bt13:
                defTest(Season.FALL);
                break;

            case R.id.bt14://ExifInterface研究
                mIntent.setClass(this, ExifInterfaceActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt15://radar
                mIntent.setClass(this, RadarActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt16://AsyncTaskActivity
                mIntent.setClass(this, AsyncTaskActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt17://启动模式
                mIntent.setClass(this, LaunchModeTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt18://Rx+Retrofit 翻译单词
                mIntent.setClass(this, RetrofitRxActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt19://MVP测试
                mIntent.setClass(this, MVPTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt20://事件分发的学习
                mIntent.setClass(this, TouchEventTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt21://NestedScrollTest
                mIntent.setClass(this, NestedScrollViewTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt22://代理模式学习
                ProxyTest test = new ProxyTest();
                test.getProxy(this).eat();
                test.getProxy(this).drink();
                test.getProxy(this).happy();
                break;

            case R.id.bt23://利用反射取注解上的信息
                try {
                    Class<?> clazz = Class.forName("com.zy.jungletest.annotationTest.AnnotationActivity");
                    for (Method method : clazz.getMethods()) {
                        if (method.isAnnotationPresent(MethodInfo.class)) {
                            MethodInfo mMethodInfo = method.getAnnotation(MethodInfo.class);
                            if (mMethodInfo != null) {
                                Log.i("zhangyi", method.getName());
                                Log.i("zhangyi", mMethodInfo.author());
                                Log.i("zhangyi", mMethodInfo.date());
                                Log.i("zhangyi", mMethodInfo.versionCode() + "");
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.bt24:// LayoutManager学习
                mIntent.setClass(this, LayoutManagerTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt25:
                mIntent.setClass(this, SuperBigImageActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt26:
                mIntent.setClass(this, KotlinTestActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt27:
                mIntent.setClass(this, MultipleViewPagerManagerActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt28:
                mIntent.setClass(this, RxJavaLearnActivity.class);
                startActivity(mIntent);
                break;

            case R.id.bt29:
//                mIntent.setClass(this, MakeSoundsActivity.class);
//                startActivity(mIntent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ModifyZJTX.excuteAll();
                }
                break;

            case R.id.bt30:
                JsonFileLoader loader = new JsonFileLoader(this, R.raw.json_data);
                try {
                    loader.getJson();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //2121
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                break;

        }
    }

    private void defTest(@Season int season) {
        Toast.makeText(this, season, Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends RecyclerView.Adapter {

        @android.support.annotation.NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@android.support.annotation.NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@android.support.annotation.NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
