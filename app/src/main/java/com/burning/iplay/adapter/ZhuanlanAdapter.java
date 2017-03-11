package com.burning.iplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.jingxuan.Zhuanlan;
import com.burning.iplay.utils.ImageLoader;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-2-27  23:21
 * Email f842728368@163.com
 * @desc 精选-专栏的Adapter
 */
public class ZhuanlanAdapter extends BaseRecyclerViewAdapter<Zhuanlan.InfoBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_zhuanlan;
    }

    @Override
    protected BaseHolder<Zhuanlan.InfoBean> creatHolder(View view, int viewType) {
        return new Holder(view);
    }

    static class Holder extends BaseHolder<Zhuanlan.InfoBean> {

        @BindView(R.id.zhuanlan_icon_iv)
        ImageView mIconIv;
        @BindView(R.id.zhualan_title_tv)
        TextView mTitleTv;
        @BindView(R.id.zhualan_desc_tv)
        TextView mDescTv;
        @BindView(R.id.zhualan_follow_tv)
        TextView mFollowTv;


        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindHolder(Zhuanlan.InfoBean bean) {
            ImageLoader.loadImage(itemView.getContext(),bean.getListImg(),mIconIv);
            mTitleTv.setText(bean.getTopicName());
            mDescTv.setText(bean.getTopicDescription());
            mFollowTv.setText(bean.getFollowUserCount());
        }
    }

}
