package com.burning.iplay.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.detail.IplayCommunity;
import com.burning.iplay.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;

/**
 * @author lam
 * @time 2017-03-06  23:34
 * @desc 社区-爱玩社区的item的gildView的adapter
 */
public class FourmIplayItemAdapter extends BaseGridViewAdapter<IplayCommunity.InfoBean.DiscuzListBean.DetailListBean> {

    public FourmIplayItemAdapter(List<IplayCommunity.InfoBean.DiscuzListBean.DetailListBean> datas) {
        super(datas);
    }

    @Override
    protected void setDataAndRefrech(BaseHolder viewHolder,
                                     IplayCommunity.InfoBean.DiscuzListBean.DetailListBean detailListBean,
                                     Context context) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ImageLoader.loadImage(context, detailListBean.getIconUrl(),
                holder.mIvItemIconIplay);
        holder.mTvModelDesc.setText(detailListBean.getModelDesc());
        holder.mTvModelName.setText(detailListBean.getModelName());
        holder.mTvTodayPosts.setText(detailListBean.getTodayPosts() + "");
    }

    @Override
    protected BaseHolder getHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_item_fourm_iplay;
    }

    public static class ViewHolder extends BaseGridViewAdapter.BaseHolder{
        @BindView(R.id.iv_item_icon_iplay)
        ImageView mIvItemIconIplay;
        @BindView(R.id.tv_model_name)
        TextView mTvModelName;
        @BindView(R.id.tv_today_posts)
        TextView mTvTodayPosts;
        @BindView(R.id.tv_model_desc)
        TextView mTvModelDesc;


        public ViewHolder(View view) {
            super(view);
        }
    }


}

