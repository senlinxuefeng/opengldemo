package com.yumingchuan.opengldemo1.chapter3;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.yumingchuan.opengldemo1.R;
import com.yumingchuan.opengldemo1.utils.ShaderHelper;
import com.yumingchuan.opengldemo1.utils.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1f;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/14
 */
public class AitHockeyRender implements GLSurfaceView.Renderer {

    private Context context;

    private static final int POSITION_COMPONENT_COUNT = 2;

    private static final int BYTES_PER_FLOAT = 4;
    private FloatBuffer vertexData;

    private int mProgram;
    private static final String U_COLOR = "u_Color";
    private int uColorLocation;

    private static final String A_POSITION = "a_Position";
    private int aPositionLocation;
    float[] tableVertices = {
            0f, 0f,
            0f, 14f,
            9f, 14f,
            9f, 0f,
    };
    float[] tableVerticesWithTriangles = {
            //三角形1
            -0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,

            //三角形2
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,

            //line1
            -0.5f, 0f,
            0.5f, 0f,

            //Mallets
            0f, -0.25f,
            0f, 0.25f,
    };

    public AitHockeyRender(Context context) {
        this.context = context;
        vertexData = ByteBuffer
                .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(tableVerticesWithTriangles);
    }

    private static final String TAG = AitHockeyRender.class.getName();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        String vertexShaderSource = TextResourceReader.readTextFileResource(context, R.raw.simple_vertex_sharder);
        String fragmentShaderSource = TextResourceReader.readTextFileResource(context, R.raw.simple_fragment_sharder);

        int vertexShaderId = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShaderId = ShaderHelper.compileFragmentShader(fragmentShaderSource);

        mProgram = ShaderHelper.linkProgram(vertexShaderId, fragmentShaderId);

        if (ShaderHelper.validateProgram(mProgram)) {
            GLES20.glUseProgram(mProgram);
        }

        uColorLocation = GLES20.glGetUniformLocation(mProgram, U_COLOR);

        aPositionLocation = GLES20.glGetAttribLocation(mProgram, A_POSITION);
        vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, vertexData);

        GLES20.glEnableVertexAttribArray(aPositionLocation);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG, "onDrawFrame");
        glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
        glDrawArrays(GL_LINES, 6, 2);


        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        glDrawArrays(GL_POINTS, 8, 1);

        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
        glDrawArrays(GL_POINTS, 9, 1);

    }
}
