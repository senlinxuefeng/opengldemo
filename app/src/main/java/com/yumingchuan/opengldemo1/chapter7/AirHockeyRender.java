package com.yumingchuan.opengldemo1.chapter7;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.yumingchuan.opengldemo1.R;
import com.yumingchuan.opengldemo1.bean.Mallet;
import com.yumingchuan.opengldemo1.bean.Table;
import com.yumingchuan.opengldemo1.chapter7.program.ColorShaderProgram;
import com.yumingchuan.opengldemo1.chapter7.program.TextureShaderProgram;
import com.yumingchuan.opengldemo1.utils.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/14
 */
public class AirHockeyRender implements GLSurfaceView.Renderer {


    private static final String TAG = AirHockeyRender.class.getName();
    private final Context context;
    private static final float[] projectionMatrix = new float[16];
    private static final float[] modelMatrix = new float[16];
    private TextureShaderProgram textureShaderProgram;
    private ColorShaderProgram colorShaderProgram;
    private Table table;
    private Mallet mallet;
    private int textureID;

    public AirHockeyRender(Context context) {
        this.context = context;

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        table = new Table();
        mallet = new Mallet();
        textureShaderProgram = new TextureShaderProgram(context);
        colorShaderProgram = new ColorShaderProgram(context);
        textureID = TextureHelper.loadTexture(context, R.drawable.air_hockey_surface);
        Matrix.setIdentityM(projectionMatrix, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG, "onDrawFrame");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        textureShaderProgram.useProgram();
        textureShaderProgram.setUniforms(projectionMatrix, textureID);
        table.bindData(textureShaderProgram);
        table.draw();

        colorShaderProgram.useProgram();
        colorShaderProgram.setUniforms(projectionMatrix);
        mallet.bindData(colorShaderProgram);
        mallet.draw();
    }
}
