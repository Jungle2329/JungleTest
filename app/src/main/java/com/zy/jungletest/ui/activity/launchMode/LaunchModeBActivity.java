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

public class LaunchModeBActivity extends AppCompatActivity {

    @BindView(R.id.a)
    Button a;
    @BindView(R.id.b)
    Button b;
    @BindView(R.id.back)
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_ab);
        ButterKnife.bind(this);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeBActivity.this, LaunchModeAActivity.class);
                startActivity(intent);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeBActivity.this, LaunchModeBActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchModeBActivity.this, LaunchModeTestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("zhangyi", "onResume----B");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("zhangyi", "onPause----B");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("zhangyi", "onStop----B");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("zhangyi", "onDestroy----B");
    }
}
