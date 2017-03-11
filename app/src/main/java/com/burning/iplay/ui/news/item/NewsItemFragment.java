package com.burning.iplay.ui.news.item;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.adapter.newsitem.NewsItemAdapter;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.ui.imagedetail.ImageDetailActivity;
import com.burning.iplay.ui.refresh.RefreshContract;
import com.burning.iplay.ui.refresh.RefreshFragment;
import com.burning.iplay.ui.search.SearchActivity;
import com.burning.iplay.utils.ActivityUtil;

/**
 * @author Kiven
 * @time 2017-2-16  22:12
 * Email f842728368@163.com
 * @desc 头条Fragment
 */
@SuppressLint("ValidFragment")
public class NewsItemFragment<T extends RefreshContract.Presenter>
        extends RefreshFragment<T, NewsItemAdapter, ArticleListBean> {


    private String mId;//对应的ID
    protected View mSearchView;
    private long mRefreshTime;


    public NewsItemFragment() {
    }

    /**
     * 构造方法，传入item对应的topicId，对不同的网址做网络请求
     *
     * @param id
     */
    public NewsItemFragment(String id) {
        mId = id;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_common;
    }

    /**
     * 初始化P层对象
     */
    @Override
    public T createPresenter() {
        // TODO 关于泛型
        return (T) new NewsItemPresenter();
    }

    /**
     * 初始化方法
     */
    @Override
    protected void initialize() {
        super.initialize();
        initStartAnimation();
    }


    @Override
    protected void initParams() {
        mParams.startIndex = 0;
        mParams.id = mId;
    }

    @Override
    protected NewsItemAdapter getAdapter() {
        return new NewsItemAdapter();
    }

    /**
     * 对布局中的控件初始化
     */
    protected void initUI() {
        super.initUI();

        addSearchView();

    }


    // 添加HeaderView，即搜索框
    protected void addSearchView() {
        mSearchView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_search, mRecyclerView, false);
        mAdapter.addHeaderView(mSearchView);
    }

    /**
     * 初始化各种事件的监听
     */
    protected void initListener() {
        super.initListener();

        // item的点击事件
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int index) {

                // 如果是图片新闻，就进入图片浏览界面
                if (mAdapter.getData(index).getShowType() == 3) {

                    String photosetId = mAdapter.getData(index).getPhotosetId();
                    String id = photosetId.substring(photosetId.lastIndexOf("|") + 1, photosetId.length());
                    Intent intent = new Intent(getActivity(), ImageDetailActivity.class);
                    intent.putExtra(ImageDetailActivity.PHOTO_SET_ID, id);
                    startActivity(intent);
                    return;
                }

                String docid = mAdapter.getData(index).getDocid();

                // 跳转到详情界面
                ActivityUtil.startDetailActivity(getActivity(), docid);
            }
        });


        // TODO 跳转到搜索界面
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 初始化Fragment开始时的动画效果也就是自动刷新
     */
    private void initStartAnimation() {
        // 动画参数
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.15f);
        animation.setDuration(1000);

        long currentTime = System.currentTimeMillis();

        // 间隔超过30秒才会自动刷新
        if (currentTime - mRefreshTime > 30 * 1000) {
            // 记录刷新时的时间
            mRefreshTime = System.currentTimeMillis();
            // 自动播放动画
            mRecyclerView.startAnimation(animation);


            // 发送网络请求
            mRefreshListener.onRefresh();
            // 播放动画
            autoRefreshAnimation();
        } else {
            // TODO 有本地数据之后从本地获得数据
            mRefreshListener.onRefresh();
        }
    }


}
