package com.yumingchuan.opengldemo1;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

public class MainActivity extends AppCompatActivity implements GLSurfaceView.Renderer {

    private static final String TAG = MainActivity.class.getName();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private GLSurfaceView mSurfaceView;

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mSurfaceView = new GLSurfaceView(this);
        setContentView(mSurfaceView);
        mSurfaceView.setRenderer(this);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
      glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG, "onDrawFrame");
        glClear(GL10.GL_COLOR_BUFFER_BIT);
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
