package com.yumingchuan.opengldemo1.chapter7.program;

import android.content.Context;
import android.opengl.GLES20;

import com.yumingchuan.opengldemo1.R;
import com.yumingchuan.opengldemo1.utils.ShaderHelper;
import com.yumingchuan.opengldemo1.utils.TextResourceReader;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/16
 */
public class ShaderProgram {
    //uniform constant
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

    //attributes constants
    protected static final String A_COLOR = "a_Color";
    protected static final String A_POSITION = "a_Position";
    protected static final String A_TEXTURE_COORDINATE = "a_TextureCoordinates";

    protected int program;

    public ShaderProgram(Context context, int vertexShaderResourceId, int fragmentShaderResourceId) {
        String vertexShaderSource = TextResourceReader.readTextFileResource(context, vertexShaderResourceId);
        String fragmentShaderSource = TextResourceReader.readTextFileResource(context, fragmentShaderResourceId);
        program = ShaderHelper.buildProgram(vertexShaderSource, fragmentShaderSource);
    }

    public void useProgram() {
        GLES20.glUseProgram(program);
    }
}
