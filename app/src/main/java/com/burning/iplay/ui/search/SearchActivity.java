package com.burning.iplay.ui.search;

import android.os.Build;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseActivity;

/**
 * @author Kiven
 * @time 2017-3-6  16:04
 * Email f842728368@163.com
 * @desc 搜索界面的Activity
 */
public class SearchActivity extends BaseActivity {

    public static final String SEARCH_ACTIVITY = "search_activity";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_commom;
    }

    @Override
    protected void initUI() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorGrey));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_fl, new SearchFragment()).commit();
    }

    @Override
    protected void requestPermissionSucceed() {
    }
}
