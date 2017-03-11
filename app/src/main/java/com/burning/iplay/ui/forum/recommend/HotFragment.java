package com.burning.iplay.ui.forum.recommend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.adapter.forumhot.ForumHotAdapterSum;
import com.burning.iplay.bean.forum.ForumHotBean;
import com.burning.iplay.ui.refresh.RefreshFragment;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.view.FlyBanner1;

import java.util.List;

/**
 * @FileName HotFragment
 * @Author Jay_Ping
 * @Time 2017/3/6
 * @Email 1054335787@qq.com
 * @Desc 社区中的热门推荐
 */
public class HotFragment extends RefreshFragment<HotPresenter, ForumHotAdapterSum, ForumHotBean.InfoBean.ThreadListBean> implements HotContract.View {
    private View mHeaderView1;
    private View mHeaderView2;
    private View mHeaderView3;


    private FlyBanner1 mFlyBanner;
    private String mImg;


    @Override
    protected ForumHotAdapterSum getAdapter() {
        return new ForumHotAdapterSum();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_common;
    }

    @Override
    protected void initParams() {
        mParams.startIndex=1;
        mLoadMoreEnable = true;
    }

    @Override
    protected HotPresenter createPresenter() {
        return new HotPresenter();
    }

    @Override
    protected void initUI() {
        super.initUI();
        initHeaderViews();
    }


    //
    private void initHeaderViews() {
        //搜索头部
        mHeaderView1 = LayoutInflater.from(getActivity()).inflate(R.layout.frag_forumhot_header1, mRecyclerView, false);

        mAdapter.addHeaderView(mHeaderView1);
        mHeaderView1.setVisibility(View.VISIBLE);

        //图片头部

        //mHeaderView2 = LayoutInflater.from(getActivity()).inflate(R.layout.frag_forumhot_header2, mRecyclerView, false);


        mFlyBanner = new FlyBanner1(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2Px(220));
        mFlyBanner.setLayoutParams(params);
        mAdapter.addHeaderView(mFlyBanner);
        mFlyBanner.setVisibility(View.GONE);

        mHeaderView2 = LayoutInflater.from(getActivity()).inflate(R.layout.frag_forumhot_header3, mRecyclerView, false);
        mAdapter.addHeaderView(mHeaderView2);
        mHeaderView2.setVisibility(View.GONE);

        //ImageLoader.loadImage(getActivity(), mImg, (ImageView) mHeaderView2.findViewById(R.id.iv_forumhot_img));
        //mAdapter.addHeaderView(mHeaderView2);
        //mHeaderView2.setVisibility(View.VISIBLE);





        //今日热帖头部
       /* mHeaderView3 = LayoutInflater.from(getActivity()).inflate(R.layout.frag_forumhot_header3, mRecyclerView, false);


       // ((TextView) mHeaderView3.findViewById(R.id.tv_online)).setText(mOnlineUserNum + "在线");


        mAdapter.addHeaderView(mHeaderView3);
        mHeaderView3.setVisibility(View.VISIBLE);*/
    }

    @Override
    protected void initListener() {
        super.initListener();

        // item的点击事件
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View itemView, int index) {
            }
        });
    }


    @Override
    public void refreshScorrview(List<ForumHotBean.InfoBean.FocusListBean> bannerData) {
        //LogUtils.i("RecommendFragment refreshBanner()" + bannerData);
        if (bannerData != null && bannerData.size() > 0) {
            mHeaderView2.setVisibility(View.VISIBLE);
            mFlyBanner.setVisibility(View.VISIBLE);
            mFlyBanner.setDatas(bannerData);
            mFlyBanner.startAutoPlay();
            mAdapter.notifyDataSetChanged();

        }
    }
}
