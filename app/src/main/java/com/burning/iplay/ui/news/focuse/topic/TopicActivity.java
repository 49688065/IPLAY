package com.burning.iplay.ui.news.focuse.topic;

import android.graphics.Color;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseActivity;
import com.burning.iplay.utils.ActivityUtil;

/**
 * @author Kiven
 * @time 2017-2-19  23:45
 * Email f842728368@163.com
 * @desc
 */
public class TopicActivity extends BaseActivity {

    public static final String GAME_ACTIVITY = "game_activity";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_commom;
    }

    @Override
    protected void initUI() {
        ActivityUtil.setColor(this, Color.parseColor("#33000000"));
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_fl, new TopicFragment()).commit();
    }

    @Override
    protected void requestPermissionSucceed() {
    }


}
