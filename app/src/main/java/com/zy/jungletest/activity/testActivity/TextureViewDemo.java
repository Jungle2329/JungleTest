package com.zy.jungletest.activity.testActivity;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.FrameLayout;

import com.zy.jungletest.R;

import java.io.IOException;

/**
 * Created by Jungle on 2017/1/3.
 */

public class TextureViewDemo extends AppCompatActivity{

    private TextureView mTextureView;
    private Camera mCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_textureview);

        mTextureView = (TextureView) findViewById(R.id.texture);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mCamera = Camera.open();
                Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
                mTextureView.setLayoutParams(new FrameLayout.LayoutParams(previewSize.width, previewSize.height, Gravity.CENTER));
                try {
                    mCamera.setPreviewTexture(surface);
                } catch (IOException t) {
                }
                mCamera.startPreview();
                mTextureView.setAlpha(1.0f);
                mTextureView.setRotation(90.0f);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                mCamera.stopPreview();
                mCamera.release();
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

    }
}
