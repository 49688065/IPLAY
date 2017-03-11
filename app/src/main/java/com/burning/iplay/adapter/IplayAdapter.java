package com.burning.iplay.adapter;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.detail.IplayCommunity;
import com.burning.iplay.utils.FixedViewUtil;

import butterknife.BindView;

/**
 * @author lam
 * @time 2017-03-06  15:36
 * @desc 社区-爱玩社区的adapter
 */
public class IplayAdapter extends BaseRecyclerViewAdapter<IplayCommunity.InfoBean.DiscuzListBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_fourm_iplay;
    }

    @Override
    protected BaseHolder<IplayCommunity.InfoBean.DiscuzListBean> creatHolder(View view, int viewType) {
        return new FourmIplayHolder(view);
    }

    static class FourmIplayHolder extends BaseHolder<IplayCommunity.InfoBean.DiscuzListBean> {

        @BindView(R.id.tv_title_iplay_fourm)
        TextView mTvTitleIplayFourm;
        @BindView(R.id.gv_content_fourm_iplay)
        GridView mGvContentFourmIplay;

        public FourmIplayHolder(View view) {
            super(view);
        }

        @Override
        public void bindHolder(IplayCommunity.InfoBean.DiscuzListBean bean) {
            mTvTitleIplayFourm.setText(bean.getType().getTypeName());
            mTvTitleIplayFourm.setBackgroundResource(R.color.iplay_background_color);
            FourmIplayItemAdapter fourmIplayItemAdapter = new FourmIplayItemAdapter(bean.getDetailList());
            mGvContentFourmIplay.setAdapter(fourmIplayItemAdapter);
            FixedViewUtil.setGridViewHeightBasedOnChildren(mGvContentFourmIplay,2);
        }
    }
}
