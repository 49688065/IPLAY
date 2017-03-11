package com.burning.iplay.ui.jingxuan.recommend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.adapter.RecommendAdapter;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.jingxuan.RecommendBanner;
import com.burning.iplay.ui.refresh.RefreshFragment;
import com.burning.iplay.utils.ActivityUtil;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.view.FlyBanner;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-18  19:19
 * Email f842728368@163.com
 * @desc 精选--最新推荐
 */
public class RecommendFragment
        extends RefreshFragment<RecommendPresenter, RecommendAdapter, ArticleListBean>
        implements RecommendContract.View {

    private FlyBanner mFlyBanner;
    private View mHeaderView2;

    @Override
    protected void initParams() {
        mLoadMoreEnable = false;
    }

    @Override
    public RecommendPresenter createPresenter() {
        return new RecommendPresenter();
    }


    @Override
    protected RecommendAdapter getAdapter() {
        return new RecommendAdapter();
    }

    @Override
    protected void initUI() {
        super.initUI();
        initHeaderViews();
        showNoMoreView();
    }


    /**
     * 初始化头部
     */
    private void initHeaderViews() {
        mFlyBanner = new FlyBanner(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2Px(220));
        mFlyBanner.setLayoutParams(params);
        mAdapter.addHeaderView(mFlyBanner);
        mFlyBanner.setVisibility(View.GONE);

        mHeaderView2 = LayoutInflater.from(getActivity()).inflate(R.layout.recommend_header2, mRecyclerView, false);
        mAdapter.addHeaderView(mHeaderView2);
        mHeaderView2.setVisibility(View.GONE);
    }




    @Override
    public void refreshBanner(List<RecommendBanner.InfoBean> bannerData) {
        //LogUtils.i("RecommendFragment refreshBanner()" + bannerData);
        if (bannerData != null && bannerData.size() > 0) {
            mHeaderView2.setVisibility(View.VISIBLE);
            mFlyBanner.setVisibility(View.VISIBLE);
            mFlyBanner.setDatas(bannerData);
            mFlyBanner.startAutoPlay();
            mAdapter.notifyDataSetChanged();

        }
    }


    @Override
    protected void initListener() {
        super.initListener();

        // item的点击事件
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int index) {
                String docid = mAdapter.getData(index).getDocId();

                // 跳转到详情界面
                ActivityUtil.startDetailActivity(getActivity(), docid);
            }
        });

        mFlyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position, RecommendBanner.InfoBean infoBean) {
                ActivityUtil.startDetailActivity(getActivity(), infoBean.getDocid());
            }
        });
    }


}
