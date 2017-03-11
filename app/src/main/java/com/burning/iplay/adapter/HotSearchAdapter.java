package com.burning.iplay.adapter;

import android.view.View;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.news.HotSearchData;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-3-6  18:49
 * Email f842728368@163.com
 * @desc 热门搜索的Adapter
 */
public class HotSearchAdapter extends BaseRecyclerViewAdapter<HotSearchData.InfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot_search;
    }

    @Override
    protected BaseHolder<HotSearchData.InfoBean> creatHolder(View view, int viewType) {
        return new Holder(view);
    }

    static class Holder extends BaseHolder<HotSearchData.InfoBean> {

        @BindView(R.id.hot_search_tv)
        TextView mHotSearchTv;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindHolder(HotSearchData.InfoBean bean) {
            mHotSearchTv.setText(bean.getKeyword());
        }
    }
}
