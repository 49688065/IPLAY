package com.burning.iplay.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

import java.io.File;

/**
 * @author Kiven
 * @time 2017-2-16  17:00
 * Email f842728368@163.com
 * @desc 整个应用常用的工具类
 */
public class AppUtil {
    private static Context sContext;

    private AppUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (sContext != null)
            return sContext;
        throw new NullPointerException("u should init first");
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到string.xml中的字符
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到string.xml中的字符,带占位符
     */
    public static String getString(int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    /**
     * 得到string.xml中的字符数组
     */
    public static String[] getStringArr(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到color.xml中的颜色值
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到应用程序的包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }


    /**
     * 获得缓存文件的路径
     */
    public static File getCacheDir() {
        return getContext().getCacheDir();
    }


    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * dip-->px
     *
     * @param dip
     * @return
     */
    public static int dip2Px(int dip) {
        // px/dp = density
        // px/(ppi/160) = px
        float density = getResources().getDisplayMetrics().density;//1.5
        int ppi = getResources().getDisplayMetrics().densityDpi;//240
        int px = (int) (dip * density + .5f);
        return px;
    }

    /**
     * px-->dip
     *
     * @param px
     * @return
     */
    public static int px2Dip(int px) {
        // px/dp = density
        float density = getResources().getDisplayMetrics().density;//1.5
        int dp = (int) (px / density + .5f);
        return dp;
    }


}
