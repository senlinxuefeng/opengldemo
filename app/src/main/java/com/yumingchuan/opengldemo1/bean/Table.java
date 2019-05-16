package com.yumingchuan.opengldemo1.bean;

import android.opengl.GLES20;

import com.yumingchuan.opengldemo1.chapter7.program.TextureShaderProgram;
import com.yumingchuan.opengldemo1.constant.Constants;
import com.yumingchuan.opengldemo1.data.VertexArray;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/16
 */
public class Table {
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * Constants.BYTE_PER_FLOAT;
    private static final float[] vertexData = {
            //x,y,s,t
            0f, 0f, 0.5f, 0.5f,
            -0.5f, -0.8f, 0f, 0.9f,
            0.5f, -0.8f, 1f, 0.9f,

            0.5f, 0.8f, 1f, 0.1f,
            -0.5f, 0.8f, 0f, 0.1f,
            -0.5f, -0.8f, 0f, 0.9f,
    };
    private final VertexArray vertexArray;

    public Table() {
        vertexArray = new VertexArray(vertexData);
    }

    public void bindData(TextureShaderProgram textureShaderProgram) {
        vertexArray.setVertexAttribPointer(0, textureShaderProgram.getPositionLocation(), POSITION_COMPONENT_COUNT, STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, textureShaderProgram.getTextureCoordinateLocation(), TEXTURE_COORDINATES_COMPONENT_COUNT, STRIDE);
    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);
    }
}
