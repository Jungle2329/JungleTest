package com.zy.commonlibrary.base;

import android.util.Log;

import com.zy.commonlibrary.BuildConfig;


/**
 * Created by jungle on 2016/9/20.
 * 程序是否Debug版本,如果是Debug版本可以输出日志，如果是正式版本特殊操作后可以输出日志
 */

public class LogUtils {

    public static final boolean IsDebug = BuildConfig.DEBUG;

    public static void i(String tag, String str) {
        if (IsDebug) {
            Log.i(tag, str);
        } else {
            if (AppValue.superAdminMode) {
                Log.i(tag, str);
            }
        }
    }

    public static void d(String tag, String str) {
        if (IsDebug) {
            Log.d(tag, str);
        } else {
            if (AppValue.superAdminMode) {
                Log.d(tag, str);
            }
        }
    }

    public static void e(String tag, String str) {
        if (IsDebug) {
            Log.e(tag, str);
        } else {
            if (AppValue.superAdminMode) {
                Log.e(tag, str);
            }
        }
    }

    public static void v(String tag, String str) {
        if (IsDebug) {
            Log.v(tag, str);
        } else {
            if (AppValue.superAdminMode) {
                Log.v(tag, str);
            }
        }
    }

    public static void w(String tag, String str) {
        if (IsDebug) {
            Log.w(tag, str);
        } else {
            if (AppValue.superAdminMode) {
                Log.w(tag, str);
            }
        }
    }
}
