package com.zy.jungletest.annotationTest;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Jungle on 2019/10/17 0017.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description 季节Def
 */
@IntDef(value = {Season.WINTER, Season.SUMMER, Season.SPRING, Season.FALL})
@Retention(RetentionPolicy.SOURCE)
public @interface Season {
    int WINTER = 0;
    int SPRING = 1;
    int SUMMER = 2;
    int FALL = 3;
}
