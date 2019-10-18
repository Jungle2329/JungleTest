package com.zy.jungletest.ui.activity.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zy.jungletest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jungle on 2018/3/5.
 */

public class LaunchModeTestActivity extends AppCompatActivity {

    @BindView(R.id.a)
    Button a;
    @BindView(R.id.b)
    Button b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        ButterKnife.bind(this);

        Log.i("zhangyi", "onCreate----Test");
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeTestActivity.this, LaunchModeAActivity.class);
                startActivity(intent);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeTestActivity.this, LaunchModeBActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("zhangyi", "onResume----Test");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("zhangyi", "onPause----Test");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("zhangyi", "onStop----Test");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("zhangyi", "onDestroy----Test");
    }
}
