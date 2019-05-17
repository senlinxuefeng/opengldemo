package com.yumingchuan.opengldemo1.chapter7.program;

import android.content.Context;
import android.opengl.GLES20;

import com.yumingchuan.opengldemo1.R;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/16
 */
public class ColorShaderProgram extends ShaderProgram {

    private final int uMatrixLocation;
    private final int aColorLocation;
    private final int aPositionLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_sharder_6, R.raw.simple_fragment_sharder_6);
        uMatrixLocation = GLES20.glGetUniformLocation(program, U_MATRIX);
        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(program, A_COLOR);
    }

    public void setUniforms(float[] matrix) {
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }

    public int getPositionLocation() {
        return aPositionLocation;
    }

    public int getColorLocation() {
        return aColorLocation;
    }
}
