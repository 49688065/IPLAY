package com.burning.iplay.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.burning.iplay.config.Constants;

/**
 * @author Kiven
 * @time 2017-2-16  17:18
 * Email f842728368@163.com
 * @desc SP工具类
 */
public class SPUtil {

    private static SharedPreferences sp;

    private static SharedPreferences getSp() {
        if (sp == null)
            sp = AppUtil.getContext().getSharedPreferences(
                    Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        return sp;
    }

    /**
     * 保存boolean
     */
    public static void putBoolean(String key, boolean value) {
        // 获得sp文件后，根据事务的特点保存数据
        getSp().edit().putBoolean(key, value).commit();
    }


    /**
     * 得到boolean，默认是false
     */
    public static boolean getBoolean(String key) {
        return getSp().getBoolean(key, false);
    }

    /**
     * 得到boolean，默认值由调用者来确定
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getSp().getBoolean(key, defValue);
    }


    /**
     * 保存String
     */
    public static void putString(String key, String value) {
        // 获得sp文件后，根据事务的特点保存数据
        getSp().edit().putString(key, value).commit();
    }

    /**
     * gutString，默认值是空字符串
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * gutString，默认值由调用者决定
     */
    public static String getString(String key, String defValue) {
        return getSp().getString(key, defValue);
    }


    /**
     * 保存int
     */
    public static void putInt(String key, int value) {
        // 获得sp文件后，根据事务的特点保存数据
        getSp().edit().putInt(key, value).commit();
    }

    /**
     * gutInt，默认值是0
     */
    public static int getInt(String key) {

        return getInt(key, 0);
    }

    /**
     * gutInt，默认值由调用者决定
     */
    public static int getInt(String key, int defValue) {
        return getSp().getInt(key, defValue);
    }
}
