package com.yumingchuan.opengldemo1.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author yumingchuan
 * @version 1.0
 * @since 2019/5/14
 */
public class TextResourceReader {
    public static String readTextFileResource(Context context, int resourceId) {
        StringBuffer body = new StringBuffer();
        try {
            InputStream is = context.getResources().openRawResource(resourceId);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String nextLine;

            while ((nextLine = bufferedReader.readLine()) != null) {
                body.append(nextLine);
                body.append("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException("can not found resourceId" + resourceId, e);
        }
        return body.toString();
    }
}
