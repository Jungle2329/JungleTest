package com.example.annotationslibrary;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Jungle on 2018/5/23 0023.
 *
 * @desc TODO
 */

@Retention(RetentionPolicy.CLASS)
public @interface EasyLog {
    String tag();
    String value();
}
