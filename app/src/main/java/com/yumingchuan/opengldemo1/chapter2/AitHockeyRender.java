package com.yumingchuan.opengldemo1.chapter2;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/14
 */
public class AitHockeyRender implements GLSurfaceView.Renderer {

    public AitHockeyRender() {
        float[] tableVertices = {
                0f, 0f,
                0f, 14f,
                9f, 14f,
                9f, 0f,
        };
    }

    private static final String TAG = AitHockeyRender.class.getName();

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
}
