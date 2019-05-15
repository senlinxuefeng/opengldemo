package com.yumingchuan.opengldemo1.chapter5;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.yumingchuan.opengldemo1.R;
import com.yumingchuan.opengldemo1.utils.ShaderHelper;
import com.yumingchuan.opengldemo1.utils.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.WeakHashMap;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glViewport;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/14
 */
public class AitHockeyRender implements GLSurfaceView.Renderer {

    private Context context;

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final String A_COLOR = "a_Color";
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int BYTES_PER_FLOAT = 4;

    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;

    private FloatBuffer vertexData;

    private int mProgram;
    private static final String U_COLOR = "u_Color";

    private int uColorLocation;
    private int aColorLocation;

    private static final String A_POSITION = "a_Position";


    private static final String U_MATRIX = "u_Matrix";
    private static final float[] projectionMatrix = new float[16];
    private int uMatrixLocation;


    private int aPositionLocation;
    float[] tableVerticesWithTriangles = {
            //三角形1
            0f, 0f, 1f, 1f, 1f,
            -0.5f, -0.8f, 0.7f, 0.7f, 0.7f,
            0.5f, -0.8f, 0.7f, 0.7f, 0.7f,

            //三角形2
            0.5f, 0.8f, 0.7f, 0.7f, 0.7f,
            -0.5f, 0.8f, 0.7f, 0.7f, 0.7f,
            -0.5f, -0.8f, 0.7f, 0.7f, 0.7f,


            //line1
            -0.5f, 0f, 0.5f, 0.5f, 0.5f,
            0.5f, 0f, 0.5f, 0.5f, 0.5f,

            //Mallets
            0f, -0.25f, 0.5f, 0.5f, 0.5f,
            0f, 0.25f, 0.5f, 0.5f, 0.5f,
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
        String vertexShaderSource = TextResourceReader.readTextFileResource(context, R.raw.simple_vertex_sharder_5);
        String fragmentShaderSource = TextResourceReader.readTextFileResource(context, R.raw.simple_fragment_sharder_5);

        int vertexShaderId = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShaderId = ShaderHelper.compileFragmentShader(fragmentShaderSource);

        mProgram = ShaderHelper.linkProgram(vertexShaderId, fragmentShaderId);

        if (ShaderHelper.validateProgram(mProgram)) {
            GLES20.glUseProgram(mProgram);
        }


        aPositionLocation = GLES20.glGetAttribLocation(mProgram, A_POSITION);
        vertexData.position(0);
        GLES20.glEnableVertexAttribArray(aPositionLocation);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, vertexData);


        aColorLocation = GLES20.glGetAttribLocation(mProgram, A_COLOR);
        vertexData.position(POSITION_COMPONENT_COUNT);
        GLES20.glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(aColorLocation);

        uMatrixLocation = GLES20.glGetUniformLocation(mProgram, U_MATRIX);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
        float aspectRatio = width > height ? width / height : height / width;
        if (width > height) {
            Matrix.orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        } else {
            Matrix.orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG, "onDrawFrame");
        glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);

        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
        glDrawArrays(GL_LINES, 6, 2);
        glDrawArrays(GL_POINTS, 8, 1);
        glDrawArrays(GL_POINTS, 9, 1);

    }
}
