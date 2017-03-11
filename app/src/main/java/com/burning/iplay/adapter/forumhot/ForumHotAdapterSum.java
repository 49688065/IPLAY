package com.burning.iplay.adapter.forumhot;

import android.view.View;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.bean.forum.ForumHotBean;

/**
 * @FileName ForumHotAdapterSum
 * @Author Jay_Ping
 * @Time 2017/3/7
 * @Email 1054335787@qq.com
 * @Desc 管理社区热门推荐总的adapter
 */
public class ForumHotAdapterSum extends BaseRecyclerViewAdapter<ForumHotBean.InfoBean.ThreadListBean> {

    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;
    private static final int TYPE_3 = 3;
    private static final int TYPE_4 = 4;

    //加载不同类型布局文件
    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case TYPE_1:
                return R.layout.layout_item_forumhot1;
            case TYPE_2:
                return R.layout.layout_item_forumhot2;
            case TYPE_3:
                return R.layout.layout_item_forumhot3;//三张
            default:
                return R.layout.layout_item_forumhot4;
        }
    }

    @Override
    public int getItemType(int index) {
        switch (mDatas.get(index).getShowType()) {
            case 1:
                return TYPE_1;
            case 2:
                return TYPE_2;
            case 3:
                return TYPE_3;
            default:
                return TYPE_4;
        }

    }

    @Override
    protected BaseHolder<ForumHotBean.InfoBean.ThreadListBean> creatHolder(View view, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new ForumHotHolder1(view);
            case TYPE_2:
                return new ForumHotHolder2(view);
            case TYPE_3:
                return new ForumHotHolder3(view);
            default:
                return new ForumHotHolder4(view);

        }
    }

    public static abstract class MessageHolder extends BaseHolder<ForumHotBean.InfoBean.ThreadListBean> {

        public MessageHolder(View itemView) {
            super(itemView);
        }

        @Override
        public abstract void bindHolder(ForumHotBean.InfoBean.ThreadListBean bean);
    }
}
