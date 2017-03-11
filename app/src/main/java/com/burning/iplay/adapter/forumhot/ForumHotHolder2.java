package com.burning.iplay.adapter.forumhot;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.forum.ForumHotBean;
import com.burning.iplay.utils.ImageLoader;

import java.util.Random;

import butterknife.BindView;

/**
 * @FileName ForumHotHolder2
 * @Author Jay_Ping
 * @Time 2017/3/7
 * @Email 1054335787@qq.com
 * @Desc 社区 热门推荐 只加载两张图片的
 */
public class ForumHotHolder2 extends ForumHotAdapterSum.MessageHolder {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_fname)
    TextView mTvFname;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_subtitle)
    TextView mTvSubtitle;
    @BindView(R.id.iv_imag1)
    ImageView mIvImag1;
    @BindView(R.id.iv_imag2)
    ImageView mIvImag2;

    public ForumHotHolder2(View itemView) {
        super(itemView);
    }

    @Override
    public void bindHolder(ForumHotBean.InfoBean.ThreadListBean bean) {
        mTvAuthor.setText(bean.getAuthor());

        mTvTitle.setText(bean.getTitle());
        mTvSubtitle.setText(bean.getSubtitle());

        mTvFname.setText(bean.getFname());

        //fname来源背景随机
        Random ran = new Random();
        int coclor = ran.nextInt();
        mTvFname.setBackgroundColor(Color.rgb(coclor, coclor, coclor));

        mIvImag1.setVisibility(View.VISIBLE);
        mIvImag2.setVisibility(View.VISIBLE);
        ImageLoader.loadImage(itemView.getContext(), bean.getImgUrl(), mIvImag1);
        ImageLoader.loadImage(itemView.getContext(), bean.getImg2Url(), mIvImag2);
    }
}
