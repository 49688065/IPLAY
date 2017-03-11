package com.burning.iplay.ui.me.set;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseActivity;
import com.burning.iplay.base.MyApplication;
import com.burning.iplay.config.Constants;
import com.burning.iplay.event.NightModeEvent;
import com.burning.iplay.event.RxBus;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.utils.CleanUtils;
import com.burning.iplay.utils.ConvertUtils;
import com.burning.iplay.utils.FileUtils;
import com.burning.iplay.utils.SPUtil;
import com.burning.iplay.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;
import static android.support.v7.app.AppCompatDelegate.setDefaultNightMode;
import static com.burning.iplay.config.Constants.PANGZHONGHUA_XING_SHU;
import static com.burning.iplay.config.Constants.WEI_RUAN_YA_HEI;

/**
 * @author Kiven
 * @time 2017-3-8  17:11
 * Email f842728368@163.com
 * @desc 设置界面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.detail_back_btn)
    ImageButton mDetailBackBtn;
    @BindView(R.id.setting_font_family)
    LinearLayout mSettingFontFamily;
    @BindView(R.id.setting_day_night)
    ImageView mSettingDayNight;
    @BindView(R.id.setting_about_me)
    LinearLayout mSettingAboutMe;
    @BindView(R.id.setting_clear_cache)
    LinearLayout mSettingClearCache;
    @BindView(R.id.setting_update)
    TextView mSettingUpdate;
    @BindView(R.id.setting_jpush)
    ImageView mSettingJpush;
    @BindView(R.id.cache_size_tv)
    TextView mCacheSizeTv;
    @BindView(R.id.font_change_tv)
    TextView mFontChangeTv;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void requestPermissionSucceed() {
    }

    /**
     * 因为会重新加载，
     */
    @Override
    protected void initUI() {
        boolean isNight = SPUtil.getBoolean(Constants.IS_NIGHT_MODE);
        setImageButtonState(mSettingDayNight, isNight);

        boolean isPush = !JPushInterface.isPushStopped(getApplicationContext());
        setImageButtonState(mSettingJpush, isPush);

        String fontFamily = SPUtil.getString(Constants.FONT_FAMILY, WEI_RUAN_YA_HEI);
        mFontChangeTv.setText(fontFamily.equals(WEI_RUAN_YA_HEI) ? "雅黑 --> 行书" : "行书 --> 雅黑");
    }


    /**
     * 在OnResume方法中显示缓存大小
     */
    @Override
    protected void onResume() {
        super.onResume();
        mCacheSizeTv.setText(getCacheSize());
    }

    @OnClick({R.id.detail_back_btn, R.id.setting_font_family, R.id.setting_day_night, R.id.setting_jpush, R.id.setting_about_me, R.id.setting_clear_cache, R.id.setting_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_back_btn:
                onBackPressed();
                break;
            case R.id.setting_font_family:
                changeFontFamily();
                break;
            case R.id.setting_day_night:
                // 设置夜间模式
                switchAllNightMode();
                break;
            case R.id.setting_jpush:
                // 开启或关闭推送
                switchJPushMode();
                break;
            case R.id.setting_about_me:
                //跳到关于界面
                ToastUtil.showToast(mSettingFontFamily, "关于本产品");
                break;
            case R.id.setting_clear_cache:
                // 直接清理缓存
                clearFileCache();
                break;
            case R.id.setting_update:
                // 弹出更新提示
                ToastUtil.showToast(mSettingFontFamily, "更新应用");
                break;
        }
    }

    /**
     * 更改字体
     */
    private void changeFontFamily() {
        MyApplication application = (MyApplication) getApplication();
        String fontFamily = SPUtil.getString(Constants.FONT_FAMILY, WEI_RUAN_YA_HEI);

        if (fontFamily.equals(WEI_RUAN_YA_HEI)) {
            application.changeFontFamily(PANGZHONGHUA_XING_SHU);
            SPUtil.putString(Constants.FONT_FAMILY, PANGZHONGHUA_XING_SHU);
        } else {
            application.changeFontFamily(WEI_RUAN_YA_HEI);
            SPUtil.putString(Constants.FONT_FAMILY, WEI_RUAN_YA_HEI);
        }
        recreate();
        // 更改MainActivity的模式
        RxBus.getInstance().post(new NightModeEvent());
    }

    /**
     * 清除缓存
     */
    private void clearFileCache() {
        CleanUtils.cleanInternalCache();
        CleanUtils.cleanInternalFiles();
        CleanUtils.cleanExternalCache();
        mCacheSizeTv.setText(getCacheSize());
    }

    /**
     * 更改推送的状态
     */
    private void switchJPushMode() {
        // 获得之前的状态
        boolean isStoped = JPushInterface.isPushStopped(getApplicationContext());
        // 取反并设置
        setImageButtonState(mSettingJpush, isStoped);
        // 更改推送状态
        if (isStoped) {
            JPushInterface.resumePush(getApplicationContext());
        } else {
            JPushInterface.stopPush(getApplicationContext());
        }

    }

    /**
     * 更改夜间模式
     */
    private void switchAllNightMode() {
        // 更改自身的模式
        switchSelfNightMode();
        // 更改MainActivity的模式
        RxBus.getInstance().post(new NightModeEvent());
    }


    /**
     * 切换日夜间模式
     */
    public void switchSelfNightMode() {
        // 默认不是夜间模式-->isNight=false，切换后是true
        boolean isNight = !SPUtil.getBoolean(Constants.IS_NIGHT_MODE);
        // 现在是true，那就设置是夜晚
        setDefaultNightMode(isNight ? MODE_NIGHT_YES : MODE_NIGHT_NO);
        // 重新putSP中的值
        SPUtil.putBoolean(Constants.IS_NIGHT_MODE, isNight);
        // 重新加载
        recreate();
    }

    /**
     * 获得缓存的大小
     */
    private String getCacheSize() {
        long cacheDirLength = FileUtils.getDirLength(getCacheDir());
        long fileDirLength = FileUtils.getDirLength(getFilesDir());
        long externalCacheLength = 0;
        if (AppUtil.isSDCardEnable()) {
            externalCacheLength = FileUtils.getDirLength(getExternalCacheDir());
        }
        return ConvertUtils.byte2FitMemorySize(cacheDirLength + fileDirLength + externalCacheLength);
    }


    /**
     * 更改图片按钮的显示状态
     */
    private void setImageButtonState(ImageView iv, boolean isPush) {
        iv.setImageResource(isPush ? R.drawable.switch_pgrecommend_turnon : R.drawable.switch_pgrecommend_turnoff);
    }

}
