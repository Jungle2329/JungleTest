package com.zy.jungletest.annotationTest;

import android.support.annotation.IntDef;

import com.zy.jungletest.Constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Jungle on 2018/5/22 0022.
 *
 * @desc TODO
 */

@IntDef({Constant.JANUARY, Constant.FEBRUARY, Constant.MARCH, Constant.APRIL, Constant.MAY
        , Constant.JUNE, Constant.JULY, Constant.AUGUST, Constant.SEPTEMBER, Constant.OCTOBER
        , Constant.NOVEMBER, Constant.DECEMBER})
@Retention(RetentionPolicy.SOURCE)
public @interface Month {
}
