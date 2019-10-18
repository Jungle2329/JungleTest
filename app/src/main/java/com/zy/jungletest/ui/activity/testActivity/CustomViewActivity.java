package com.zy.jungletest.ui.activity.testActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zy.jungletest.R;

import butterknife.ButterKnife;

/**
 * Created by Jungle on 2017/1/24.
 */

public class CustomViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        ButterKnife.bind(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
