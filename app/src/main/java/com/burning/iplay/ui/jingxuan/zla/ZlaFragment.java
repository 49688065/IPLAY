package com.burning.iplay.ui.jingxuan.zla;

import android.annotation.SuppressLint;
import android.view.View;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.adapter.RecommendAdapter;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.ui.refresh.RefreshFragment;
import com.burning.iplay.utils.ActivityUtil;
import com.burning.iplay.utils.LogUtils;

/**
 * @author Kiven
 * @time 2017-3-1  21:03
 * Email f842728368@163.com
 * @desc
 */
@SuppressLint("ValidFragment")
public class ZlaFragment
        extends RefreshFragment<ZlaPresenter, RecommendAdapter, ArticleListBean> {
    private String mDocId;

    public ZlaFragment(String docId) {
        LogUtils.i("ZlaFragment ZlaFragment()" + docId);
        this.mDocId = docId;
    }

    @Override
    protected RecommendAdapter getAdapter() {
        return new RecommendAdapter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_common;
    }

    @Override
    protected void initParams() {
        mLoadMoreEnable = true;
        mParams.id = mDocId;
    }

    @Override
    protected ZlaPresenter createPresenter() {
        return new ZlaPresenter();
    }

    @Override
    protected void initListener() {
        super.initListener();
        // item的点击事件
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int index) {
                String docid = mAdapter.getData(index).getDocid();
                LogUtils.i("ZlaFragment onItemClick()" + docid);
                // 跳转到详情界面
                ActivityUtil.startDetailActivity(getActivity(), docid);
            }
        });
    }
}
