package com.zy.jungletest.activity.testActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.zy.jungletest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/27.
 */

public class DatabaseTestActivity extends AppCompatActivity {

    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);
        ButterKnife.bind(this);

    }
}
