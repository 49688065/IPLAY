package com.burning.iplay.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.comment.Comment;
import com.burning.iplay.bean.comment.UserBean;
import com.burning.iplay.utils.ImageLoader;
import com.burning.iplay.utils.TimeUtils;
import com.burning.iplay.view.FloorView;
import com.burning.iplay.view.SubFloorFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Kiven
 * @time 2017-3-7  14:17
 * Email f842728368@163.com
 * @desc 评论界面的Adapter
 */
public class CommentAdapter extends RecyclerView.Adapter {

    // 两个标题栏的常量
    public static final int TYPE_NORMAL = 0;
    private static final int TYPE_HOT_TITLE = 100;
    private static final int TYPE_NEW_TITLE = 101;

    protected List<List<Comment>> mDatas;
    private int mHotCommentCount;

    /**
     * 获得所有的item的数量
     */
    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HOT_TITLE;
        }
        if (position == mHotCommentCount + 1) {
            return TYPE_NEW_TITLE;
        }

        return TYPE_NORMAL;
    }


    public void setData(List<List<Comment>> lists, int hotCommentCount) {
        mHotCommentCount = hotCommentCount;
        mDatas = lists;
        notifyDataSetChanged();
    }


    public void addDatas(List<List<Comment>> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        if (viewType == TYPE_HOT_TITLE || viewType == TYPE_NEW_TITLE) {
            return new EmptyHolder(view);
        }
        // 创建ViewHolder
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0 || position == (mHotCommentCount + 1)) {
            return;
        } else if (position > 0 && position <= mHotCommentCount) {
            position = position - 1;
        } else {
            position = position - 2;
        }
        ((Holder) holder).bindHolder(mDatas.get(position));
    }

    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case TYPE_HOT_TITLE:
                return R.layout.item_comment_hot_itle;
            case TYPE_NEW_TITLE:
                return R.layout.item_comment_new_itle;
            case TYPE_NORMAL:
                return R.layout.item_comment;
        }
        return R.layout.item_comment;
    }


    /**
     * 正常的评论数据
     */
    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.comment_icon_iv)
        ImageView mCommentIconIv;
        @BindView(R.id.ueser_name_tv)
        TextView mUeserNameTv;
        @BindView(R.id.user_loaction_tv)
        TextView mUserLoactionTv;
        @BindView(R.id.comment_time_tv)
        TextView mCommentTimeTv;
        @BindView(R.id.zan_iv)
        ImageView mZanIv;
        @BindView(R.id.comment_vote)
        TextView mCommentVote;
        @BindView(R.id.floor_view)
        FloorView mFloorView;
        @BindView(R.id.main_comment_tv)
        TextView mMainCommentTv;


        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolder(List<Comment> bean) {
            List<Comment> list = new ArrayList<>();
            list.addAll(bean);

            if (list.size() == 0) {
                return;
            } else {
                Comment comment = list.get(list.size() - 1);
                UserBean user = comment.getUser();
                // 设置Icon
                if (!TextUtils.isEmpty(user.getAvatar())) {
                    ImageLoader.loadImage(itemView.getContext(), user.getAvatar(), mCommentIconIv);
                } else {
                    mCommentIconIv.setImageResource(R.drawable.ic_default_rect_angle);
                }
                // 设置用户名
                if (!TextUtils.isEmpty(user.getNu())) {
                    mUeserNameTv.setText(user.getNu());
                } else {
                    if (!TextUtils.isEmpty(user.getNickname())) {
                        mUeserNameTv.setText(user.getNickname());
                    } else {
                        mUeserNameTv.setText(itemView.getContext().getResources().getString(R.string.default_user_name));
                    }
                }
                // 设置评论主体内容
                mUserLoactionTv.setText(user.getLocation());

                mCommentTimeTv.setText(TimeUtils.getFriendlyTimeSpanByNow(comment.getCreateTime()));
                mCommentVote.setText(String.valueOf(comment.getVote()));
                mMainCommentTv.setText(comment.getContent().replaceAll("<br>", "\n"));

                // 下面是对楼中楼的处理
                list.remove(list.size() - 1);
                if (list.size() == 0) {
                    mFloorView.setDatas(new ArrayList<Comment>());
                } else {
                    mFloorView.setDatas(list);
                }
                list = new ArrayList<>();
                mFloorView.setFactory(new SubFloorFactory());
                mFloorView.setBoundDrawer(itemView.getContext().getResources().getDrawable(R.drawable.comment_sub_bound));
                mFloorView.init();
            }
        }
    }


    static class EmptyHolder extends RecyclerView.ViewHolder {
        public EmptyHolder(View itemView) {
            super(itemView);
        }
    }
}
