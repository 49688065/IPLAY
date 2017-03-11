package com.burning.iplay.adapter.newsitem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.utils.ImageLoader;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-2-13  14:08
 * Email f842728368@163.com
 * @desc 一般Item的ViewHolder
 */
public class Holder5 extends NewsItemAdapter.MessageHolder {

//    @BindView(R.id.type5_title_tv)
//    TextView mItemTitleTv;
    @BindView(R.id.type5_comment_count_tv)
    TextView mItemCommentCountTv;
    @BindView(R.id.type5_game_name_tv)
    TextView mGameNameTv;
    @BindView(R.id.type5_iv)
    ImageView mItemIv;

    public Holder5(View itemView) {
        super(itemView);
    }


    @Override
    public void bindHolder(ArticleListBean bean) {
//        mItemTitleTv.setText(bean.getTitle());
        super.bindHolder(bean);
        mItemCommentCountTv.setText(String.valueOf(bean.getReplyCount()));
        mGameNameTv.setText(bean.getGameName());
        String imgUrl = getImageUrl(bean);
        ImageLoader.loadImage(itemView.getContext(), imgUrl, mItemIv);

    }

    /**
     * 根据不同的情况获得icon的url
     *
     * @param bean
     * @return
     */
    private String getImageUrl(ArticleListBean bean) {
        if (bean.getImgsrc().size() > 0) {
            return bean.getImgsrc().get(0);
        } else {
            return bean.getFidIconUrl();
        }
    }

}
