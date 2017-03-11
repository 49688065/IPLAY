package com.burning.iplay.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-2-18  9:50
 * Email f842728368@163.com
 * @desc 下拉刷新，上拉加载更多的Fragment
 * <p>
 * T:P层对象的类型
 * K:adapter的类型
 * E:列表数据的bean的类型
 */
public abstract class RefreshFragment<T extends RefreshContract.Presenter, K extends BaseRecyclerViewAdapter, E>
        extends BaseFragment<T>
        implements RefreshContract.View<E> {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipe_layout)
    protected SwipeRefreshLayout mSwipeLayout;

    protected K mAdapter;

    protected boolean mLoadMoreEnable = true;
    protected boolean mNoMoreEnable = false;
    protected LinearLayoutManager mLinearLayoutManager;
    /**
     * “没有更多”的FooterView
     */
    protected View mNoMoreView;

    /**
     * 下拉刷新的监听
     */
    protected SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //LogUtils.i("RefreshFragment onRefresh()");
            mParams.startIndex = 0;
            mSwipeLayout.setRefreshing(true);
            mPresenter.requestData(mParams);
        }
    };


    /**
     * 返回对应的Adapter
     */
    protected abstract K getAdapter();

    @Override
    protected void initialize() {
        mPresenter.start();
        initUI();
        initListener();
    }


    @Override
    protected void requestPermissionSucceed() {





        mPresenter.requestData(mParams);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_common;
    }


    protected void initUI() {
        // 初始化RecyclerView和Adapter
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = getAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // 改变下拉刷新时图标的颜色
        mSwipeLayout.setColorSchemeResources(R.color.swipe_color_1,
                R.color.swipe_color_2,
                R.color.swipe_color_3,
                R.color.swipe_color_4);
    }


    /**
     * 显示“没有更多了”
     */
    protected void showNoMoreView() {
        if (mNoMoreView == null) {
            mNoMoreView = LayoutInflater.from(getActivity()).inflate(R.layout.footer_no_more, mRecyclerView, false);
            mAdapter.addFooterView(mNoMoreView);
        }
        mNoMoreView.setVisibility(View.VISIBLE);
    }


    protected void initListener() {
        // 这个是下拉刷新的监听事件
        mSwipeLayout.setOnRefreshListener(mRefreshListener);


        if (mLoadMoreEnable) {
            // 上拉加载更多的实现
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (!recyclerView.canScrollVertically(1)) {// 表示不能滚动，也就是到了最底部
                        //LogUtils.i("RefreshFragment onScrolled() loadMore");
                        mParams.startIndex += mParams.dataCount;
                        mPresenter.requestData(mParams);
                    }
                }
            });
        }

    }

    /**
     * 有数据后给RecyclerView添加数据，并刷新界面
     *
     * @param datas
     */
    @Override
    public void refreshRecyclerView(List<E> datas) {
        // LogUtils.i("RefreshFragment refreshRecyclerView() index:" + mParams.startIndex);
        // 根据数据的开始索引
        if (mParams.startIndex == 0) {
            //mAdapter.setData(datas);
            resetRecyclerViewData(datas);
        } else {
            addRecyclerViewData(datas);
            //mAdapter.addDatas(datas);
        }
        mSwipeLayout.setRefreshing(false);
    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    protected void resetRecyclerViewData(List<E> datas) {
        mAdapter.setData(datas);
        if (datas.size() < mParams.dataCount && mNoMoreEnable) {
            showNoMoreView();
        }
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    protected void addRecyclerViewData(List<E> datas) {
        mAdapter.addDatas(datas);
        if (datas.size() < mParams.dataCount && mNoMoreEnable) {
            showNoMoreView();
        }
    }

    /**
     * 播放自动刷新的动画
     */
    protected void autoRefreshAnimation() {
        mSwipeLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(true);
            }
        });
    }

}
