package com.zy.jungletest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.zy.jungletest.R;

/**
 * 5.0特性测试
 * Created by Jungle on 2016/12/22.
 */

public class TestLolipopDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lolipopdemo);

        CardView cv1 = (CardView) findViewById(R.id.cv1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(TestLolipopDemoActivity.this, "click!!", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(TestLolipopDemoActivity.this, "click!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
