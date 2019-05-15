package com.yumingchuan.opengldemo1.utils;

import android.util.Log;

/**
 * @author yumingchuan
 * @version v
 * @since 2019/5/14
 */
public class LoggerConfig {
    public static final boolean DEBUG = true;

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

}
