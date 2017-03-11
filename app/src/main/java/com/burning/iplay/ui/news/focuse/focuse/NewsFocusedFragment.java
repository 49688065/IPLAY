package com.burning.iplay.ui.news.focuse.focuse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.burning.iplay.R;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.news.TopTopicListBean;
import com.burning.iplay.ui.news.focuse.topic.TopicActivity;
import com.burning.iplay.ui.news.item.NewsItemFragment;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.utils.ImageLoader;
import com.burning.iplay.utils.ToastUtil;
import com.burning.iplay.view.FocusedHeaderView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-17  13:48
 * Email f842728368@163.com
 * @desc 关注界面，与其他的Fragment不同的是界面的更新不一样，网络请求也是只有一次
 */
@SuppressLint("ValidFragment")
public class NewsFocusedFragment extends NewsItemFragment<NewsFocusedPresenter>
        implements NewsFocusedContract.View<ArticleListBean> {


    private FocusedHeaderView mFocuseView;
    private View mFocusedView;
    private RecyclerView mFocusedRv;
    private List<TopTopicListBean> mFocusedData;
    private ImageView mAddView;
    private boolean mIsFocused;

    public NewsFocusedFragment() {
        super();
    }


    public NewsFocusedFragment(String id) {
        super(id);
    }

    @Override
    public NewsFocusedPresenter createPresenter() {
        return new NewsFocusedPresenter();
    }

    @Override
    protected void initParams() {
        mParams.startIndex = 0;
        // 关注界面对topicIds的处理较复杂，放在P层中
    }

    @Override
    public void setIsFocused(boolean isFocused) {
        mIsFocused = isFocused;
    }

    /**
     * 初始化Header
     */
    @Override
    protected void addSearchView() {
        super.addSearchView();
        // 添加FocusedView
        if (!mIsFocused) {
            mFocuseView = new FocusedHeaderView(getActivity());
            mAdapter.addHeaderView(mFocuseView);
        } else {
            mFocusedView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_focused, mRecyclerView, false);
            mFocusedRv = (RecyclerView) mFocusedView.findViewById(R.id.focused_rv);
            // mFocusedRv.addItemDecoration(new MyItemDecoration(getActivity()));
            mFocusedRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            mAdapter.addHeaderView(mFocusedView);

        }
    }

    // 初始化添加按钮
    private void initAddView() {
        mAddView = new ImageView(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                AppUtil.dip2Px(70), AppUtil.dip2Px(70));
        params.setMargins(30, 30, 30, 30);
        mAddView.setLayoutParams(params);
        mAddView.setImageResource(R.drawable.img_pgmall_add);

    }


    /**
     * 显示头部的关注列表
     *
     * @param focusedData
     */
    @Override
    public void refreshFocusedView(List<TopTopicListBean> focusedData) {
        // 作为全局变量，后面的点击事件要用到
        mFocusedData = focusedData;
        // 如果没有关注，显示3个item
        if (!mIsFocused) {
            mFocuseView.setData(focusedData);
        } else {
            // 如果有关注，显示N个item + 添加按钮
            CommonAdapter<TopTopicListBean> adapter =
                    new CommonAdapter<TopTopicListBean>(getActivity(), R.layout.item_focused, focusedData) {
                        @Override
                        protected void convert(ViewHolder holder, TopTopicListBean bean, int position) {
                            ImageLoader.loadImage(getActivity(), bean.getNewIcon(),
                                    (ImageView) holder.getView(R.id.item_focused_iv));
                        }
                    };
            // 添加Footer
            //HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper<>(adapter);
            //headerAndFooterWrapper.addFootView(mAddView);
            //mFocusedRv.setAdapter(headerAndFooterWrapper);
            mFocusedRv.setAdapter(adapter);
            //headerAndFooterWrapper.notifyDataSetChanged();
            // 设置点击事件
            initFocusedItemClickListener(adapter);

        }

    }

    /**
     * 有数据时显示的列表的监听事件
     *
     * @param headerAndFooterWrapper
     */
    private void initFocusedItemClickListener(CommonAdapter<TopTopicListBean> headerAndFooterWrapper) {
        headerAndFooterWrapper.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                startFocuseItemActivity(mFocusedData.get(position));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                startFocusedOrderActivity();
                return true;
            }
        });
    }


    @Override
    protected void initListener() {
        super.initListener();

        if (mAddView != null) {
            mAddView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startFocuseActivity();
                }
            });
        }

        /**
         * 没有关注时的view
         */
        if (mFocuseView != null) {
            mFocuseView.setOnViewClickListener(new FocusedHeaderView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startFocuseActivity();
                }

                @Override
                public void onItemClick(View v, TopTopicListBean bean) {
                    startFocuseItemActivity(bean);
                }
            });
        }
    }

    /**
     * TODO 跳转到关注列表界面也就是游戏专区管理界面
     */
    private void startFocuseActivity() {
        ToastUtil.showToast(mFocuseView, "To New  Activity");
        //
    }

    /**
     * 点击item跳转到关注某个专区的新闻界面
     */
    private void startFocuseItemActivity(TopTopicListBean bean) {

        ToastUtil.showToast(mRecyclerView, bean.getTopicId());

        Intent intent = new Intent(getActivity(), TopicActivity.class);
        intent.putExtra(TopicActivity.GAME_ACTIVITY, bean);
        getActivity().startActivity(intent);

    }

    /**
     * TODO  长按跳转到排序界面
     */
    private void startFocusedOrderActivity() {
        ToastUtil.showToast(mRecyclerView, "跳转到排序界面");
    }

}
