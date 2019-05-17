package com.yumingchuan.opengldemo1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLUtils;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/16
 */
public class TextureHelper {
    private static final String TAG = TextureHelper.class.getName();

    /**
     * 返回处理后的文理ID
     *
     * @param context
     * @param resourceId
     * @return
     */
    public static int loadTexture(Context context, int resourceId) {
        final int[] textureObjectId = new int[1];
        GLES20.glGenTextures(1, textureObjectId, 0);
        if (textureObjectId[0] == 0) {
            LoggerConfig.i(TAG, "不能生成textureObjectId");
            return 0;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        if (bitmap == null) {
            LoggerConfig.i(TAG, "加载文理bitmap失败");
            GLES20.glDeleteTextures(1, textureObjectId, 0);
            return 0;
        }

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObjectId[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        return textureObjectId[0];
    }
}
