package com.zy.jungletest.activity.testActivity;

import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zy.jungletest.R;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片信息测试
 * Created by Jungle on 2017/11/6.
 */

public class ExifInterfaceActivity extends AppCompatActivity {

    @BindView(R.id.bt_get_message)
    Button bt_get_message;
    @BindView(R.id.iv_image)
    ImageView iv_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exif_interface);
        ButterKnife.bind(this);
        iv_image.setImageURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/IMG_20171106_150315.jpg")));
        bt_get_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    ExifInterface mExif = new ExifInterface(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/IMG_20171106_150315.jpg");
                    String make = mExif.getAttribute(ExifInterface.TAG_MAKE);
                    String date_time = mExif.getAttribute(ExifInterface.TAG_DATETIME);
                    String width = mExif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
                    String orienta = mExif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    Log.i("zhangyi", "make: " + make);
                    Log.i("zhangyi", "date_time: " + date_time);
                    Log.i("zhangyi", "width: " + width);
                    Toast.makeText(ExifInterfaceActivity.this, make, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
