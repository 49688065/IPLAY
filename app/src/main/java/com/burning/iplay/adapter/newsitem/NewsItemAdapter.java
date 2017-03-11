package com.burning.iplay.adapter.newsitem;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.bean.news.ArticleListBean;

import butterknife.BindView;


/**
 * @author Kiven
 * @time 2017-2-16  14:48
 * Email f842728368@163.com
 * @desc 资讯界面的通用Adapter
 */
public class NewsItemAdapter extends BaseRecyclerViewAdapter<ArticleListBean> {


    public static final int TYPE_5 = 1;
    public static final int TYPE_3 = 2;
    public static final int TYPE_8 = 3;
    public static final int TYPE_1 = 4;
    public static final int TYPE_2 = 5;


    @Override
    public int getItemType(int index) {
        switch (mDatas.get(index).getShowType()) {
            case 5:
                return TYPE_5;
            case 3:
                return TYPE_3;
            case 8:
                return TYPE_8;
            case 1:
                return TYPE_1;
            case 2:
                return TYPE_2;
            default:
                return TYPE_5;
        }
    }

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case TYPE_3:
                return R.layout.item_type3;
            case TYPE_5:
                return R.layout.item_type5;
            case TYPE_8:
                return R.layout.item_type8;
            case TYPE_1:
                return R.layout.item_type1;
            case TYPE_2:
                return R.layout.item_type2;
            default:
                return R.layout.item_type5;
        }
    }

    @Override
    protected BaseHolder<ArticleListBean> creatHolder(View view, int viewType) {
        switch (viewType) {
            case TYPE_3:
                return new Holder3(view);
            case TYPE_5:
                return new Holder5(view);
            case TYPE_8:
                return new Holder8(view);
            case TYPE_1:
                return new Holder1(view);
            case TYPE_2:
                return new Holder2(view);
            default:
                return new Holder5(view);
        }
    }

    public static abstract class MessageHolder extends BaseHolder<ArticleListBean> {

        @BindView(R.id.news_item_content)
        TextView mTitleTv;

        public MessageHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindHolder(ArticleListBean bean) {
            if (bean.isLooked()) {
                mTitleTv.setTextColor(itemView.getContext().getResources().getColor(R.color.colorGrey));
            } else {
                mTitleTv.setTextColor(Color.BLACK);
            }
            mTitleTv.setText(bean.getTitle());
        }
    }
}
