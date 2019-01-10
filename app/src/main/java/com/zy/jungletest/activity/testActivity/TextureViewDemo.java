package com.zy.jungletest.activity.testActivity;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.FrameLayout;

import com.zy.commonlibrary.utils.PermissionHelper;
import com.zy.jungletest.R;

import java.io.IOException;

/**
 * Created by Jungle on 2017/1/3.
 */

public class TextureViewDemo extends AppCompatActivity {

    private TextureView mTextureView;
    private Camera mCamera;
    private PermissionHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_textureview);

        helper = new PermissionHelper(TextureViewDemo.this
                , PermissionHelper.CAMERA
                , PermissionHelper.WRITE_EXTERNAL_STORAGE);

        mTextureView = findViewById(R.id.texture);

        helper.checkPermissionForce(new PermissionHelper.ForcePermissionCallbacks() {
            @Override
            public void onPermissionsAllGranted() {
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
                        if (mCamera != null) {
                            mCamera.stopPreview();
                            mCamera.release();
                        }
                        return true;
                    }

                    @Override
                    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

                    }
                });
            }

            @Override
            public void onPermissionsDenied() {
                finish();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        helper.bindRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        helper.bindActivityResult(requestCode, resultCode, data);
    }
}
