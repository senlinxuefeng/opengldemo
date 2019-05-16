package com.yumingchuan.opengldemo1.bean;

import android.opengl.GLES20;

import com.yumingchuan.opengldemo1.chapter7.program.ColorShaderProgram;
import com.yumingchuan.opengldemo1.chapter7.program.TextureShaderProgram;
import com.yumingchuan.opengldemo1.constant.Constants;
import com.yumingchuan.opengldemo1.data.VertexArray;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/16
 */
public class Mallet {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * Constants.BYTE_PER_FLOAT;
    private static final float[] VERTEX_DATA = {
            //x,y,r,g,g
            0f, -0.4f, 0f, 0f, 1f,
            0f, 0.4f, 1f, 0f, 0f
    };
    private final VertexArray vertexArray;

    public Mallet() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram colorShaderProgram) {
        vertexArray.setVertexAttribPointer(0, colorShaderProgram.getPositionLocation(), POSITION_COMPONENT_COUNT, STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, colorShaderProgram.getColorLocation(), COLOR_COMPONENT_COUNT, STRIDE);
    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 2);
    }
}
