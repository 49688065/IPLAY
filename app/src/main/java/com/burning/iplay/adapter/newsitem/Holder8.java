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
 * @time 2017-2-13  15:11
 * Email f842728368@163.com
 * @desc 游戏史上的今天item对应的Holder
 */
public class Holder8 extends NewsItemAdapter.MessageHolder {

    @BindView(R.id.type8_iv)
    ImageView mIv;
    //    @BindView(R.id.type8_title_tv)
    //    TextView mTitleTv;
    @BindView(R.id.type8_subtitle_tv)
    TextView mSubTitleTv;


    public Holder8(View itemView) {
        super(itemView);
    }

    @Override
    public void bindHolder(ArticleListBean bean) {
        super.bindHolder(bean);
        ImageLoader.loadImage(itemView.getContext(), bean.getImgsrc().get(0), mIv);
        //        mTitleTv.setText(bean.getTitle());
        mSubTitleTv.setText(bean.getSubtitle());
    }
}
