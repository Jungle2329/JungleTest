package com.zy.jungletest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Jungle on 2018/4/19 0019.
 *
 * @desc TODO
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    protected abstract int getViewId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();
}