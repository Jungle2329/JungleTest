package com.zy.jungletest.annotationTest;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Jungle on 2018/5/22 0022.
 *
 * @desc TODO
 */

@IntDef({Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY
        , Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER
        , Month.NOVEMBER, Month.DECEMBER})
@Retention(RetentionPolicy.SOURCE)
public @interface Month {
    int JANUARY = 1;
    int FEBRUARY = 2;
    int MARCH = 3;
    int APRIL = 4;
    int MAY = 5;
    int JUNE = 6;
    int JULY = 7;
    int AUGUST = 8;
    int SEPTEMBER = 9;
    int OCTOBER = 10;
    int NOVEMBER = 11;
    int DECEMBER = 12;
}
