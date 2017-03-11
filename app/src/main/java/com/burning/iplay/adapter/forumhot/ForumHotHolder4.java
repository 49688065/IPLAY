package com.burning.iplay.adapter.forumhot;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.forum.ForumHotBean;

import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * @FileName ForumHotHolder4
 * @Author Jay_Ping
 * @Time 2017/3/7
 * @Email 1054335787@qq.com
 * @Desc 社区 热门推荐 加载评论的
 */
public class ForumHotHolder4 extends ForumHotAdapterSum.MessageHolder {

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
    @BindView(R.id.tv_comment1)
    TextView mTvComment1;
    @BindView(R.id.tv_comment2)
    TextView mTvComment2;
    @BindView(R.id.tv_comment3)
    TextView mTvComment3;
    @BindView(R.id.tv_comment4)
    TextView mTvComment4;
    @BindView(R.id.tv_comment5)
    TextView mTvComment5;
    @BindView(R.id.tv_comment6)
    TextView mTvComment6;

    public ForumHotHolder4(View itemView) {
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

        List<ForumHotBean.InfoBean.ThreadListBean.ReplyListBean> replyList = bean.getReplyList();

        if (replyList != null) {

            for (int i = 0; i < replyList.size(); i++) {

                if (replyList.size() == 1) {
                    mTvComment1.setText(replyList.get(i).getReplyUserName() + " : ");
                    mTvComment2.setText(replyList.get(i).getReplyTieContent());
                    mTvComment3.setVisibility(View.GONE);
                    mTvComment4.setVisibility(View.GONE);
                    return;
                } else if (replyList.size() == 2) {

                    mTvComment1.setText(replyList.get(i).getReplyUserName() + " : ");
                    mTvComment2.setText(replyList.get(i).getReplyTieContent());
                    mTvComment3.setVisibility(View.VISIBLE);
                    mTvComment4.setVisibility(View.VISIBLE);
                    mTvComment5.setVisibility(View.GONE);
                    mTvComment6.setVisibility(View.GONE);
                    i++;
                    mTvComment3.setText(replyList.get(i).getReplyUserName() + " : ");
                    mTvComment4.setText(replyList.get(i).getReplyTieContent());

                } else if (replyList.size() == 3) {

                    mTvComment1.setText(replyList.get(i).getReplyUserName() + " : ");
                    mTvComment2.setText(replyList.get(i).getReplyTieContent());
                    mTvComment3.setVisibility(View.VISIBLE);
                    mTvComment4.setVisibility(View.VISIBLE);
                    mTvComment5.setVisibility(View.VISIBLE);
                    mTvComment6.setVisibility(View.VISIBLE);
                    i++;
                    mTvComment3.setText(replyList.get(i).getReplyUserName() + " : ");
                    mTvComment4.setText(replyList.get(i).getReplyTieContent());
                    i++;
                    mTvComment5.setText(replyList.get(i).getReplyUserName() + " : ");
                    mTvComment6.setText(replyList.get(i).getReplyTieContent());
                }
            }
        }
    }
}
