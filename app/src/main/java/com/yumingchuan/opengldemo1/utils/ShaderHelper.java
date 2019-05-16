package com.yumingchuan.opengldemo1.utils;

import android.opengl.GLES20;
import android.view.View;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/14
 */
public class ShaderHelper {

    public static final String TAG = ShaderHelper.class.getName();

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    /**
     * @param type
     * @param shaderCode
     * @return 编译shader成功并且返回shader的ID
     */
    public static int compileShader(int type, String shaderCode) {
        int shaderObjectId = GLES20.glCreateShader(type);
        if (shaderObjectId == 0) {
            LoggerConfig.i(TAG, "不能创建新 shader");
            return 0;
        }
        GLES20.glShaderSource(shaderObjectId, shaderCode);
        GLES20.glCompileShader(shaderObjectId);

        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        if (compileStatus[0] == 0) {
            GLES20.glDeleteShader(shaderObjectId);
            LoggerConfig.i(TAG, "编译shader失败");
            return 0;
        }
        //shader的log日志信息
        LoggerConfig.i(TAG, shaderCode + "    " + GLES20.glGetShaderInfoLog(shaderObjectId));
        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        final int programId = GLES20.glCreateProgram();
        if (programId == 0) {
            LoggerConfig.i(TAG, "不能创建programId");
            return 0;
        }
        GLES20.glAttachShader(programId, vertexShaderId);
        GLES20.glAttachShader(programId, fragmentShaderId);
        GLES20.glLinkProgram(programId);

        if (!validateProgram(programId)) {
            GLES20.glDeleteProgram(programId);
            LoggerConfig.i(TAG, "link program 失败");
            return 0;
        }
        return programId;
    }


    public static boolean validateProgram(int programId) {
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        return linkStatus[0] != 0;
    }

    public static int buildProgram(String vertexShaderSource, String fragmentShaderSource) {
        int vertexShader = compileVertexShader(vertexShaderSource);
        int fragmentShader = compileFragmentShader(fragmentShaderSource);
        return linkProgram(vertexShader, fragmentShader);
    }

}
