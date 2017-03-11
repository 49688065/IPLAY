package com.burning.iplay.adapter.newsitem;

import android.view.View;
import android.widget.ImageView;

import com.burning.iplay.R;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.utils.ImageLoader;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-2-13  14:54
 * Email f842728368@163.com
 * @desc 图集对应的Holder
 */
public class Holder3 extends NewsItemAdapter.MessageHolder {

    @BindView(R.id.type3_left_iv)
    ImageView mLeftIv;
    @BindView(R.id.type3_top_iv)
    ImageView mTopIv;
    @BindView(R.id.type3_bottom_iv)
    ImageView mBottomIv;
//    @BindView(R.id.type3_title_tv)
//    TextView mTitleTv;


    public Holder3(View itemView) {
        super(itemView);
    }

    @Override
    public void bindHolder(ArticleListBean bean) {
        super.bindHolder(bean);
        ImageLoader.loadImage(itemView.getContext(), bean.getImgsrc().get(0), mLeftIv);
        ImageLoader.loadImage(itemView.getContext(), bean.getImgsrc().get(1), mTopIv);
        ImageLoader.loadImage(itemView.getContext(), bean.getImgsrc().get(2), mBottomIv);
//        mTitleTv.setText(bean.getTitle());
    }
}
