package com.burning.iplay.ui.jingxuan.zhuanlan;

import android.content.Intent;
import android.view.View;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.adapter.ZhuanlanAdapter;
import com.burning.iplay.bean.jingxuan.Zhuanlan;
import com.burning.iplay.ui.jingxuan.zla.ZhuanlanActivity;
import com.burning.iplay.ui.refresh.RefreshFragment;

/**
 * @author Kiven
 * @time 2017-2-27  23:14
 * Email f842728368@163.com
 * @desc 精选-专栏的List
 */
public class ZhuanlanFragment
        extends RefreshFragment<ZhuanlanPresenter, ZhuanlanAdapter, Zhuanlan.InfoBean> {

    @Override
    protected ZhuanlanAdapter getAdapter() {
        return new ZhuanlanAdapter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_common;
    }

    @Override
    protected void initParams() {
        mLoadMoreEnable = false;
        mParams.startIndex = 0;
    }


    @Override
    protected ZhuanlanPresenter createPresenter() {
        return new ZhuanlanPresenter();
    }


    @Override
    protected void initUI() {
        super.initUI();
        showNoMoreView();
    }


    @Override
    protected void initListener() {
        super.initListener();
        // 设置点击事件，跳转到新的Activity中
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int index) {
                Intent intent = new Intent(getActivity(), ZhuanlanActivity.class);
                intent.putExtra(ZhuanlanActivity.ZHUANLAN, mAdapter.getData(index));
                getActivity().startActivity(intent);
            }
        });
    }
}
