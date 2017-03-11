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
 * @time 2017-2-13  20:08
 * @Email f842728368@163.com
 * @desc showType1对应的样式，只有icon没有大图
 */
public class Holder1 extends NewsItemAdapter.MessageHolder {


    @BindView(R.id.type1_comment_tv)
    TextView mType1CommentTv;
    @BindView(R.id.type1_editor_tv)
    TextView mType1EditorTv;
    @BindView(R.id.type1_source_tv)
    TextView mType1SourceTv;
    @BindView(R.id.type1_iv)
    ImageView mType1Iv;


    public Holder1(View itemView) {
        super(itemView);
    }

    @Override
    public void bindHolder(ArticleListBean bean) {
        super.bindHolder(bean);
        mType1EditorTv.setText(bean.getEditor().get(0).getEditorName());
        mType1CommentTv.setText(String.valueOf(bean.getReplyCount()));
        mType1SourceTv.setText(bean.getSource());
        ImageLoader.loadImage(itemView.getContext(), bean.getFidIconUrl(), mType1Iv);
    }

}
