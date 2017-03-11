package com.burning.iplay.base;

import com.burning.iplay.R;
import com.burning.iplay.config.Constants;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.utils.LogUtils;
import com.burning.iplay.utils.SPUtil;

import org.litepal.LitePalApplication;

import cn.jpush.android.api.JPushInterface;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;
import static android.support.v7.app.AppCompatDelegate.setDefaultNightMode;

/**
 * @author Kiven
 * @time 2017-2-16  16:50
 * Email f842728368@163.com
 * @desc Application类，对整个应用统一管理
 */
public class MyApplication extends LitePalApplication {

    /**
     * 全局的内存泄漏的检测对象
     */
    //public static RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();


        initUtils();

        initLeakCanary();

        initJPush();

        initMode();

        initFontFamily();
    }

    /**
     * 初始化字体
     */
    private void initFontFamily() {
        String fontFamily = SPUtil.getString(Constants.FONT_FAMILY, Constants.WEI_RUAN_YA_HEI);
        changeFontFamily(fontFamily);
    }

    /**
     * 修改字体
     */
    public void changeFontFamily(String fontAssetPath) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(fontAssetPath)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }


    /**
     * 初始化极光推送
     */
    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    /**
     * 初始化LeakCanary
     */
    private void initLeakCanary() {
        //mRefWatcher = LeakCanary.install(this);
    }

    /**
     * 初始化工具类
     */
    private void initUtils() {
        AppUtil.init(getApplicationContext());
        LogUtils.init(true, false, 'v', "IPLAY");

    }

    /**
     * 初始化是否是夜间模式
     */
    private void initMode() {
        boolean isNight = SPUtil.getBoolean(Constants.IS_NIGHT_MODE);
        setDefaultNightMode(isNight ? MODE_NIGHT_YES : MODE_NIGHT_NO);
    }
}
