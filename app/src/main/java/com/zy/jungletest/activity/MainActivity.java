package com.zy.jungletest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.zy.commonlibrary.BaseRetrofit;
import com.zy.jungletest.R;
import com.zy.jungletest.activity.launchMode.LaunchModeTestActivity;
import com.zy.jungletest.database.DatabaseHelper;
import com.zy.jungletest.utils.AESUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    public static final int WINTER = 0;
    public static final int SPRING = 1;
    public static final int SUMMER = 2;
    public static final int FALL = 3;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.btm)
    Button btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTransparencyBar(this);
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
            , R.id.bt06, R.id.bt07, R.id.bt08, R.id.bt09, R.id.bt10
            , R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15
            , R.id.bt16, R.id.bt17, R.id.bt18, R.id.btm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btm:
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START);
                    btm.setText("open");
                } else {
                    drawer_layout.openDrawer(GravityCompat.START);
                    btm.setText("close");
                }

//                new BaseRetrofit.Builder()
//                        .a().b().c().d().b().build().a();

                break;
            case R.id.bt01:
                Intent a = new Intent(this, TestGridViewActivity.class);
                startActivity(a);
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
                Intent i = new Intent(this, TestLolipopDemoActivity.class);
                startActivity(i);
                break;
            case R.id.bt04://TextureVie
                Intent asd = new Intent(this, TextureViewDemo.class);
                startActivity(asd);
                break;
            case R.id.bt05:
                Intent i5 = new Intent(this, CustomViewActivity.class);
                startActivity(i5);
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
                Intent intent07 = new Intent(this, GestureDetectorTestActivity.class);
                startActivity(intent07);
                break;

            case R.id.bt08://SlidingMenu
                Intent intent08 = new Intent(this, SlidingMenuTestActivity.class);
                startActivity(intent08);
                break;

            case R.id.bt09:
                Intent intent09 = new Intent(this, RetrofitTestActivity.class);
                startActivity(intent09);
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
                String url = "https://baike.baidu.com/vbaike/%E8%A7%A3%E8%AF%BB%E4%B9%9D%E5%AF%A8%E6%B2%9F%E5%9C%B0%E9%9C%87/18581";
//                OkHttpUtils.get().url(url).build().execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.i("zhangyi", response);
//                    }
//                });
                break;

            case R.id.bt13:
                ss(MainActivity.FALL);
                break;

            case R.id.bt14://ExifInterface研究
                Intent exifIntent = new Intent(this, ExifInterfaceActivity.class);
                startActivity(exifIntent);
                break;

            case R.id.bt15://radar
                Intent radarIntent = new Intent(this, RadarActivity.class);
                startActivity(radarIntent);
                break;

            case R.id.bt16://AsyncTaskActivity
                Intent asyncIntent = new Intent(this, AsyncTaskActivity.class);
                startActivity(asyncIntent);
                break;

            case R.id.bt17://启动模式
                Intent launchIntent = new Intent(this, LaunchModeTestActivity.class);
                startActivity(launchIntent);
                break;

            case R.id.bt18://Rx+Retrofit
                Intent limit = new Intent(this, RetrofitRxActivity.class);
                startActivity(limit);
                new BaseRetrofit.Builder().build().test();
                break;

        }
    }


    @IntDef({WINTER, SUMMER, SPRING, FALL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Season {
    }

    private void ss(@Season int season) {
        Toast.makeText(this, season, Toast.LENGTH_SHORT).show();
    }
}
