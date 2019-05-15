package com.yumingchuan.opengldemo1.chapter3;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.yumingchuan.opengldemo1.base.BaseActivity;


public class Chapter3Activity extends BaseActivity {

    private static final String TAG = Chapter3Activity.class.getName();

    private GLSurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mSurfaceView = new GLSurfaceView(this);
        setContentView(mSurfaceView);
        mSurfaceView.setEGLContextClientVersion(2);
       AitHockeyRender aitHockeyRender = new AitHockeyRender(this);
        mSurfaceView.setRenderer(aitHockeyRender);
        mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
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
