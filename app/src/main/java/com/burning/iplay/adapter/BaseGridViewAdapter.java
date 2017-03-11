package com.burning.iplay.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author lam
 * @time 2017-03-06  23:27
 * @desc ${TODD}
 */
public abstract class BaseGridViewAdapter<T> extends BaseAdapter {
    protected List<T> mDatas;

    public BaseGridViewAdapter(List<T> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), getLayout(), null);
            viewHolder = getHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FourmIplayItemAdapter.ViewHolder) convertView.getTag();
        }
        T t = mDatas.get(position);
          setDataAndRefrech(viewHolder,t,parent.getContext());
        return convertView;
    }

    /**
     * 设置数据的方法具体由子类实现
     */
    protected abstract void setDataAndRefrech(BaseHolder viewHolder, T t, Context context);
    /**
     * 具体由子类返回对应的Holder
     */
    protected abstract BaseHolder getHolder(View convertView);
    /**
     * 具体由子类返回对应的布局id
     */
    protected abstract int getLayout();

    /**
     * Holder的基类
     */
    public static abstract class BaseHolder{

        public BaseHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
