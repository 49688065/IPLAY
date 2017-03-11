package com.burning.iplay.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * @author Kiven
 * @time 2017-2-16  11:49
 * Email f842728368@163.com
 * @desc RecyclerView的Adapter的基类
 */
public abstract class BaseRecyclerViewAdapter<T> extends
        RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseHolder> {

    public static final int TYPE_NORMAL = 0;

    protected List<T> mDatas;


    //************************************* Listener ******************************************/
    protected BaseRecyclerViewAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        /**
         * 此处的index是item在Data中的index
         *
         * @param itemView
         * @param index
         */
        void onItemClick(View itemView, int index);
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }
    //************************************* Listener ******************************************/


    public void setData(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }


    public void addDatas(List<T> datas) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.addAll(datas);
        notifyDataSetChanged();

    }

    /**
     * 根据数据的索引，返回对应的数据，，当没有数据集合的时候，返回值为空
     */
    public T getData(int index) {
        return mDatas == null ? null : mDatas.get(index);
    }

    /**
     * 获得所有的item的数量
     */
    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size() + mHeaderViews.size() + mFooterViews.size();
    }

    /**
     * 获得列表中除去Header和Footer的数据的数量
     */
    public int getDataCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    @Override
    public int getItemViewType(int position) {

        // 如果是Header
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        }
        // 如果是Footer
        if (isFooterViewPos(position)) {

            return mFooterViews.keyAt(position - getHeadersCount() - mDatas.size());
        }
        // 普通的Item
        int index = position - getHeadersCount();
        return getItemType(index);
    }


    /**
     * 获得普通的itemType，如果子类中有不同的item要复写这个方法
     *
     * @param index 在mData中的位置
     */
    public int getItemType(int index) {
        return TYPE_NORMAL;
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return new EmptyHolder(mHeaderViews.get(viewType));
        }
        if (mFooterViews.get(viewType) != null) {
            return new EmptyHolder(mFooterViews.get(viewType));
        }

        // 创建ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        //return new Holder5(view);
        return creatHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

        // 是Header或者Footer，不做处理
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return;
        }
        // 普通Item，获得数据对应的position，和数据，并处理
        final int index = position - getHeadersCount();
        final T bean = mDatas.get(index);
        final View itemView = holder.itemView;
        holder.bindHolder(bean);

        // 点击事件，这里没有为头和尾设置点击事件
        if (mListener != null && itemView != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(itemView, index);
                }
            });
        }
    }

    /**
     * 子类必须实现的方法，返回item布局对应的id
     *
     * @param viewType
     * @return
     */
    protected abstract int getLayoutId(int viewType);

    /**
     * 子类必须实现的方法，根据不同的itemType返回对应的Holder
     *
     * @param view
     * @param viewType
     * @return
     */
    protected abstract BaseHolder<T> creatHolder(View view, int viewType);


    /**
     * Holder的基类
     */
    public static abstract class BaseHolder<K> extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 设置数据的方法
         *
         * @param bean
         */
        public abstract void bindHolder(K bean);

    }

    /**
     * Header和Footer的空实现
     */
    static class EmptyHolder<K> extends BaseHolder<K> {
        public EmptyHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindHolder(K bean) {
            return;
        }

    }

    //************************* 多个Header和Footer的实现 ******************************/
    protected static final int BASE_ITEM_TYPE_HEADER = 10000;
    protected static final int BASE_ITEM_TYPE_FOOTER = 20000;
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
        notifyItemInserted(getHeadersCount());
    }

    public void addFooterView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
        notifyItemInserted(getItemCount());
    }

    public View getHeaderView() {
        return getHeaderView(0);
    }

    public View getFooterView() {
        return getFooterView(0);
    }

    public View getHeaderView(int index) {
        return mHeaderViews.get(mHeaderViews.keyAt(index));
    }

    public View getFooterView(int index) {
        return mFooterViews.get(mFooterViews.keyAt(index));
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getDataCount();
    }
    //************************* 多个Header和Footer的实现 ******************************/


}
