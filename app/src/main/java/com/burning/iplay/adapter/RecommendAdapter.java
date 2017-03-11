package com.burning.iplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.utils.ImageLoader;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-2-18  19:29
 * Email f842728368@163.com
 * @desc 精选--推荐列表的Adapter
 */
public class RecommendAdapter extends BaseRecyclerViewAdapter<ArticleListBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_recommend;
    }

    @Override
    protected BaseHolder<ArticleListBean> creatHolder(View view, int viewType) {
        return new Holder(view);
    }

    static class Holder extends BaseHolder<ArticleListBean> {

        @BindView(R.id.type2_iv)
        ImageView mIv;
        @BindView(R.id.type2_title_tv)
        TextView mTitleTv;
        @BindView(R.id.type2_game_name_tv)
        TextView mGameNameTv;
        @BindView(R.id.recommend_icon)
        ImageView mIcon;
        @BindView(R.id.recommend_penName)
        TextView mPenName;
        @BindView(R.id.author_role)
        TextView mRole;
        @BindView(R.id.recommend_replay)
        TextView mReplayCount;
        @BindView(R.id.recommend_read_seconds)
        TextView mReadSeconds;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindHolder(ArticleListBean bean) {

            ImageLoader.loadImage(itemView.getContext(), bean.getImgUrl(), mIv);
            ImageLoader.loadImage(itemView.getContext(), bean.getLargeLogoUrl(), mIcon);


            mTitleTv.setText(bean.getTitle());
            mGameNameTv.setText(bean.getGameName());
            mPenName.setText(bean.getPenName());
            mRole.setText(bean.getRole());
            mReplayCount.setText(String.valueOf(bean.getReplyCount()));
            mReadSeconds.setText(String.valueOf(bean.getReadSeconds()) + "分钟");

        }
    }
}
