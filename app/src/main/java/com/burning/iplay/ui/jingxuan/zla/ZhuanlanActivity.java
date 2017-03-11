package com.burning.iplay.ui.jingxuan.zla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.jingxuan.Zhuanlan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Kiven
 * @time 2017-3-1  18:21
 * Email f842728368@163.com
 * @desc 精选-专栏-Activity
 */
public class ZhuanlanActivity extends AppCompatActivity {

    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.fav_iv)
    ImageView mFavIv;
    @BindView(R.id.fav_tv)
    TextView mFavTv;


    public static final String ZHUANLAN = "zhuanlan";
    private Unbinder mUnBinder;
    private Zhuanlan.InfoBean mBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuanlan);
        mUnBinder = ButterKnife.bind(this);

        initData();
        initUI();
    }


    private void initData() {
        mBean = getIntent().getParcelableExtra(ZHUANLAN);
    }

    private void initUI() {
        mTitleTv.setText(mBean.getTopicName());
        mFavTv.setText(mBean.getFollowUserCount() + "人喜欢");
        // TODO 待完成，点击喜欢，推送消息
        // mFavIv.setVisibility(View.GONE);

        // 初始化Fragment
        ZlaFragment zlaFragment = new ZlaFragment(mBean.getTopicId());
        getSupportFragmentManager().beginTransaction().replace(R.id.zhuanlan_container, zlaFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
    }
}
