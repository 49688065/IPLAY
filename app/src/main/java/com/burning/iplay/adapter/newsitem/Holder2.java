package com.burning.iplay.adapter.newsitem;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.utils.ImageLoader;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-2-18  18:58
 * Email f842728368@163.com
 * @desc
 */
public class Holder2 extends NewsItemAdapter.MessageHolder {

    @BindView(R.id.type2_iv)
    ImageView mType2Iv;
    @BindView(R.id.type2_title_tv)
    TextView mType2TitleTv;
    @BindView(R.id.type2_game_name_tv)
    TextView mType2GameNameTv;


    public Holder2(View itemView) {
        super(itemView);
    }

    @Override
    public void bindHolder(ArticleListBean bean) {
        mType2TitleTv.setText(bean.getTitle());
        ImageLoader.loadImage(itemView.getContext(), bean.getImgsrc().get(0), mType2Iv);
        if (!TextUtils.isEmpty(bean.getGameName())) {
            mType2GameNameTv.setText(bean.getGameName());
        }
    }
}
