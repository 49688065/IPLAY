package com.burning.iplay.ui.forum.kaien;

import com.burning.iplay.R;
import com.burning.iplay.adapter.IplayAdapter;
import com.burning.iplay.bean.detail.IplayCommunity;
import com.burning.iplay.ui.refresh.RefreshFragment;

/**
 * @author lam
 * @time 2017-03-06  15:31
 * @desc 社区-爱玩社区的fragment
 */
public class KaienFragment extends
        RefreshFragment<KaienPresenter, IplayAdapter, IplayCommunity.InfoBean.DiscuzListBean> {
    @Override
    protected IplayAdapter getAdapter() {
        return new IplayAdapter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_common;
    }

    @Override
    protected void initParams() {
        mLoadMoreEnable = false;

        mRecyclerView.setBackgroundColor(getResources()
                .getColor(R.color.iplay_background_color));
    }

    @Override
    protected KaienPresenter createPresenter() {
        return new KaienPresenter();
    }
}
