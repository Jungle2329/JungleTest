package com.zy.jungletest.ui.activity.testActivity;

import android.util.Log;

import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;
import com.zy.jungletest.view.InterceptButton;
import com.zy.jungletest.view.InterceptLinearLayout;
import com.zy.jungletest.view.InterceptTextView;

import butterknife.BindView;

/**
 * Created by Jungle on 2018/4/19 0019.
 *
 * @desc TODO
 */

public class TouchEventTestActivity extends BaseActivity {

    @BindView(R.id.ll_parent)
    InterceptLinearLayout ll_parent;
    @BindView(R.id.btn_a)
    InterceptButton btn_a;
    @BindView(R.id.tv_b)
    InterceptTextView tv_b;

    @Override
    protected int getViewId() {
        return R.layout.activity_touch_event;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        ll_parent.setOnClickListener((v) -> {
            Log.i("zhangyi", "ll_parent点击");
        });

        btn_a.setOnTouchListener((v, event) -> {
            Log.i("zhangyi", "A点击");
            return false;
        });

        tv_b.setOnTouchListener((v, event) -> {
            Log.i("zhangyi", "B点击");
            return false;
        });
    }


}
