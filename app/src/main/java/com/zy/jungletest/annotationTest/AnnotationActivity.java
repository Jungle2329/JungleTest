package com.zy.jungletest.annotationTest;

import com.zy.commonlibrary.base.Constant;
import com.zy.jungletest.base.BaseActivity;

/**
 * Created by Jungle on 2018/5/22 0022.
 *
 */

public class AnnotationActivity extends BaseActivity {

    @Override
    protected int getViewId() {
        return 0;
    }

    @Override
    protected void initView() {
        testAnnotation1(Constant.APRIL);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    public void testAnnotation1(@Month int month) {
        testAnnotation2(1);

    }

    @MethodInfo(author = "Jungle1",date = "2018/5/22", versionCode = 11)
    public void testAnnotation2(int month) {

    }

    @MethodInfo(author = "Jungle2",date = "2018/5/22", versionCode = 12)
    public void testAnnotation3(int month) {

    }

    @MethodInfo(author = "Jungle3",date = "2018/5/22", versionCode = 13)
    public void testAnnotation4(int month) {

    }

    @MethodInfo(author = "Jungle4",date = "2018/5/22", versionCode = 14)
    public void testAnnotation5(int month) {

    }

}
