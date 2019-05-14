package com.yumingchuan.opengldemo1.chapter2;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.yumingchuan.opengldemo1.base.BaseActivity;


public class Chapter2Activity extends BaseActivity {

    private static final String TAG = Chapter2Activity.class.getName();

    private GLSurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mSurfaceView = new GLSurfaceView(this);
        setContentView(mSurfaceView);
        mSurfaceView.setRenderer(new AitHockeyRender());
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mSurfaceView != null) {
            mSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSurfaceView != null) {
            mSurfaceView.onResume();
        }
    }
}
