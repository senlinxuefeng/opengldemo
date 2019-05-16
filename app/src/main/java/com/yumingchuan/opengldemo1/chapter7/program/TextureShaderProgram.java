package com.yumingchuan.opengldemo1.chapter7.program;

import android.content.Context;
import android.opengl.GLES20;

import com.yumingchuan.opengldemo1.R;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/16
 */
public class TextureShaderProgram extends ShaderProgram {

    private final int uMatrixLocation;
    private final int uTextureLocation;
    private final int aPositionLocation;
    private final int aTextureCoordinateLocation;

    public TextureShaderProgram(Context context) {
        super(context, R.raw.texture_vertex_sharder_7, R.raw.texture_fragment_sharder_7);
        uMatrixLocation = GLES20.glGetUniformLocation(program, U_MATRIX);
        uTextureLocation = GLES20.glGetUniformLocation(program, U_TEXTURE_UNIT);

        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aTextureCoordinateLocation = GLES20.glGetAttribLocation(program, A_TEXTURE_COORDINATE);
    }

    public void setUniforms(float[] matrix, int textureId) {
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        GLES20.glUniform1i(uTextureLocation, 0);
    }

    public int getPositionLocation() {
        return aPositionLocation;
    }

    public int getTextureCoordinateLocation() {
        return aTextureCoordinateLocation;
    }
}
