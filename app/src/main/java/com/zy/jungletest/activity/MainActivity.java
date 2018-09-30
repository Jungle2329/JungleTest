package com.zy.jungletest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;
import com.example.annotationslibrary.EasyLog;
import com.zy.commonlibrary.utils.AESUtil;
import com.zy.jungletest.R;
import com.zy.jungletest.activity.launchMode.LaunchModeTestActivity;
import com.zy.jungletest.activity.testActivity.AsyncTaskActivity;
import com.zy.jungletest.activity.testActivity.CustomViewActivity;
import com.zy.jungletest.activity.testActivity.ExifInterfaceActivity;
import com.zy.jungletest.activity.testActivity.GestureDetectorTestActivity;
import com.zy.jungletest.activity.testActivity.KotlinTestActivity;
import com.zy.jungletest.activity.testActivity.LayoutManagerTestActivity;
import com.zy.jungletest.activity.testActivity.MultipleViewPagerManagerActivity;
import com.zy.jungletest.activity.testActivity.NestedScrollViewTestActivity;
import com.zy.jungletest.activity.testActivity.RadarActivity;
import com.zy.jungletest.activity.testActivity.RetrofitRxActivity;
import com.zy.jungletest.activity.testActivity.RxJavaLearnActivity;
import com.zy.jungletest.activity.testActivity.SlidingMenuTestActivity;
import com.zy.jungletest.activity.testActivity.SuperBigImageActivity;
import com.zy.jungletest.activity.testActivity.TestGridViewActivity;
import com.zy.jungletest.activity.testActivity.TestLolipopDemoActivity;
import com.zy.jungletest.activity.testActivity.TextureViewDemo;
import com.zy.jungletest.activity.testActivity.TouchEventTestActivity;
import com.zy.jungletest.annotationTest.MethodInfo;
import com.zy.jungletest.api.ApiRetrofit;
import com.zy.jungletest.database.DatabaseHelper;
import com.zy.jungletest.model.RetrofitRxTestBean;
import com.zy.jungletest.model.TranslationBean;
import com.zy.jungletest.mvpTest.activity.MVPTestActivity;
import com.zy.jungletest.proxyTest.ProxyTest;
import com.zy.jungletest.view.AtTextView;
import com.zy.quickretrofit.RetrofitHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

@EasyLog(tag = "Zhang", value = "Yi")
public class MainActivity extends AppCompatActivity {

    public static final int WINTER = 0;
    public static final int SPRING = 1;
    public static final int SUMMER = 2;
    public static final int FALL = 3;

    @IntDef(value = {WINTER, SUMMER, SPRING, FALL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Season {
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.btm)
    Button btm;
    @BindView(R.id.lav_logo)
    LottieAnimationView lav_logo;
    @BindView(R.id.atv)
    AtTextView atv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (savedInstanceState != null) {
            String hello = savedInstanceState.getString("hello");
            Log.i("zhangyi", hello);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTransparencyBar(this);
        NewbieGuide.with(this)
                .setLabel("test")
                .alwaysShow(true)
                .addGuidePage(GuidePage.newInstance()
                        .addHighLight(btm, HighLight.Shape.OVAL)
                        .setLayoutRes(R.layout.activity_asynctask))
                .show();
//        atv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
    }


    /**
     * 修改状态栏为全透明，这个是自己写的，其他方法未验证
     * 这里如果布局的根部不设置android:fitsSystemWindows="true"
     * 布局会移动到状态栏里头，可以使用方法计算状态栏的高度，然后空开相应的高度，
     * 空开的地方也可以自己设置颜色
     * jungle
     *
     * @param activity
     */
    public static void setTransparencyBar(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //这里判断该手机是否有虚拟按键，如果有的话不设置该属性
            if (!checkDeviceHasNavigationBar(activity))
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("hello", "world");
        Log.i("zhangyi", "true");
    }

    /**
     * 获取是否存在NavigationBar虚拟按键
     *
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }

        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }


    @OnClick({R.id.bt01, R.id.bt02, R.id.bt03, R.id.bt04, R.id.bt05
            , R.id.bt06, R.id.bt07, R.id.bt08, R.id.bt10
            , R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15
            , R.id.bt16, R.id.bt17, R.id.bt18, R.id.bt19, R.id.bt20
            , R.id.btm, R.id.bt21, R.id.bt22, R.id.bt23, R.id.bt24
            , R.id.bt25, R.id.bt26, R.id.bt27, R.id.bt28})
    public void onClick(View view) {
        Intent mIntent = new Intent();
        switch (view.getId()) {
            case R.id.btm:
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START);
                    btm.setText("open");
                } else {
                    drawer_layout.openDrawer(GravityCompat.START);
                    btm.setText("close");
                }


                break;
            case R.id.bt01:
                mIntent.setClass(this, TestGridViewActivity.class);
                startActivity(mIntent);
                break;
            case R.id.bt02:
                new SweetAlertDialog(this).show();
//                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE).show();
//                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE).show();
//                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE).show();
//                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
//                        .setTitleText("等待")
//                        .setContentText("你好世界")
//                        .show();
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
                ss(MainActivity.FALL);
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

        }
    }

    private void ss(@Season int season) {
        Toast.makeText(this, season, Toast.LENGTH_SHORT).show();
    }
}
