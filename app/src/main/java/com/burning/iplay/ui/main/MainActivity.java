package com.burning.iplay.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.burning.iplay.R;
import com.burning.iplay.config.Constants;
import com.burning.iplay.event.NightModeEvent;
import com.burning.iplay.event.RxBus;
import com.burning.iplay.ui.out.MeFragment;
import com.burning.iplay.ui.out.ForumFragment;
import com.burning.iplay.ui.out.JingXuanFragment;
import com.burning.iplay.ui.out.NewsFragment;
import com.burning.iplay.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;
import static android.support.v7.app.AppCompatDelegate.setDefaultNightMode;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rb_group)
    RadioGroup mRbGroup;

    private NewsFragment mNewsFragment;
    private JingXuanFragment mJingXuanFragment;
    private ForumFragment mForumFragment;
    private Unbinder mUnBinder;
    private MeFragment mMeFragment;
    private Subscription mRxSubscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局
        setContentView(getLayoutInflater().inflate(R.layout.activity_main, null));
        // 绑定控件
        mUnBinder = ButterKnife.bind(this);
        // 2. 初始化窗口布局
        initUI();

        // 3.监听更改夜间模式
        // 获得数据
        // 设置模式
        // 重新加载
        mRxSubscription = RxBus.getInstance().toObserverable(NightModeEvent.class)
                .subscribe(new Action1<NightModeEvent>() {
                    @Override
                    public void call(NightModeEvent nightModeEvent) {
                        // 获得数据
                        boolean isNight = SPUtil.getBoolean(Constants.IS_NIGHT_MODE);
                        // 设置模式
                        setDefaultNightMode(isNight ? MODE_NIGHT_YES : MODE_NIGHT_NO);
                        // 重新加载
                        recreate();
                    }
                });

    }

    private void initUI() {
        initFragments();
        mRbGroup.setOnCheckedChangeListener(this);
    }


    private void initFragments() {
        mNewsFragment = new NewsFragment();
        mJingXuanFragment = new JingXuanFragment();
        mForumFragment = new ForumFragment();
        mMeFragment = new MeFragment();
        replaceOutFragment(mNewsFragment);
    }


    private void replaceOutFragment(Fragment f) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.replace(R.id.main_fl, f);
        transaction.commit();
    }


    /**
     * 最下方，点击切换Fragment的管理
     */
    // @OnCheckedChanged({R.id.rb_news, R.id.rb_jingxuan, R.id.rb_forum, R.id.rb_my})
    //void onTagChecked(RadioButton rbFragment) {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.rb_news:
                //LogUtils.i("MainActivity onTagChecked() 资讯");
                replaceOutFragment(mNewsFragment);
                break;
            case R.id.rb_jingxuan:
                //LogUtils.i("MainActivity onTagChecked() 精选");
                replaceOutFragment(mJingXuanFragment);
                break;
            case R.id.rb_forum:
                //LogUtils.i("MainActivity onTagChecked() 社区");
                replaceOutFragment(mForumFragment);
                break;
            case R.id.rb_my:
                //LogUtils.i("MainActivity onTagChecked() 我");
                replaceOutFragment(mMeFragment);
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRxSubscription != null) {
            mRxSubscription.unsubscribe();
            mRxSubscription = null;
        }
    }
}
