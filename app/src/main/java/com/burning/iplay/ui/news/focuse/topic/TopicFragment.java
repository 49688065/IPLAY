package com.burning.iplay.ui.news.focuse.topic;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.adapter.newsitem.NewsItemAdapter;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.news.TopTopicListBean;
import com.burning.iplay.ui.refresh.RefreshFragment;
import com.burning.iplay.utils.ImageLoader;
import com.burning.iplay.utils.LogUtils;

/**
 * @author Kiven
 * @time 2017-2-20  0:34
 * Email f842728368@163.com
 * @desc
 */

public class TopicFragment
        extends RefreshFragment<TopicPresenter, NewsItemAdapter, ArticleListBean>
        implements TopicContract.View {


    private ImageButton mHeaderFocuseIv;
    private View mHeaderView;
    private TopTopicListBean mTopicBean;

    @Override
    protected void initParams() {
        mLoadMoreEnable = true;
        mTopicBean = getActivity().getIntent().getParcelableExtra(TopicActivity.GAME_ACTIVITY);
        mParams.id = mTopicBean.getTopicId();
    }

    @Override
    public TopicPresenter createPresenter() {
        return new TopicPresenter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_topic;
    }

    @Override
    protected NewsItemAdapter getAdapter() {
        return new NewsItemAdapter();
    }

    @Override
    protected void initialize() {
        super.initialize();
        initHeaderViews();
        // 对下拉放大做处理
        initzoomimage();

    }

    private void initHeaderViews() {
        mHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_game_header, mRecyclerView, false);
        mHeaderFocuseIv = (ImageButton) mHeaderView.findViewById(R.id.game_header_focuse_iv);
        setHeaderViewData();
        mAdapter.addHeaderView(mHeaderView);
    }

    private void setHeaderViewData() {
        ImageLoader.loadImage(getActivity(), mTopicBean.getNewIcon(), (ImageView) mHeaderView.findViewById(R.id.game_header_iv));
        ImageLoader.loadImage(getActivity(), mTopicBean.getNewIconBgImg(), (ImageView) mHeaderView.findViewById(R.id.game_header_bg_iv));

        ((TextView) mHeaderView.findViewById(R.id.game_header_title_tv)).setText(mTopicBean.getTopicName());
        ((TextView) mHeaderView.findViewById(R.id.game_header_des_tv)).setText(mTopicBean.getTopicDescription());
        ((TextView) mHeaderView.findViewById(R.id.game_header_fouce_num_tv)).setText(mTopicBean.getFollowUserCount() + " 人关注");

        mHeaderFocuseIv.setBackgroundResource(R.drawable.border_red);

        mHeaderFocuseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.changeFocuseState();
            }
        });
    }


    @Override
    public void updateFocuseState(boolean isFocused) {
        mHeaderFocuseIv.setBackgroundResource(isFocused ? R.drawable.border_gray : R.drawable.border_red);
    }



    //*************************  ******************************/
    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 是否正在放大
    private Boolean mScaling = false;
    private DisplayMetrics metric;

    /**
     * 图片缩放的处理
     */
    private void initzoomimage() {

        metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) mHeaderView.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // 播放自动刷新的动画
                        if (mScaling) {
                            LogUtils.i("TopicFragment onTouch()" + "refresh");
                            autoRefreshAnimation();
                            mRefreshListener.onRefresh();
                        }
                        mScaling = false;
                        replyImage();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!mScaling) {
                            //当图片也就是第一个item完全可见的时候，记录触摸屏幕的位置
                            if (mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                                mFirstPosition = event.getY();
                            } else {
                                return false;
                            }
                        }
                        int distance = (int) ((event.getY() - mFirstPosition) * 0.6); // 滚动距离乘以一个系数
                        if (distance < 0) {
                            break;
                        }
                        // 处理放大
                        mScaling = true;
                        lp.width = metric.widthPixels + distance;
                        lp.height = (int) ((metric.widthPixels + distance) * 0.44f);
                        mHeaderView.setLayoutParams(lp);

                        return true; // 返回true表示已经完成触摸事件，不再处理
                }
                return false;
            }
        });

    }


    /**
     * 让图片的大小返回
     */
    private void replyImage() {
        final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) mHeaderView.getLayoutParams();
        final float w = mHeaderView.getLayoutParams().width;// 图片当前宽度
        final float h = mHeaderView.getLayoutParams().height;// 图片当前高度
        final float newW = metric.widthPixels;// 图片原宽度
        final float newH = metric.widthPixels * 0.44f;// 图片原高度

        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration(200);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.width = (int) (w - (w - newW) * cVal);
                lp.height = (int) (h - (h - newH) * cVal);
                mHeaderView.setLayoutParams(lp);
            }
        });
        anim.start();

    }


}
