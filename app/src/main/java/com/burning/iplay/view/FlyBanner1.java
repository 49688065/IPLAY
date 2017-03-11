package com.burning.iplay.view;


/**
 * @author Kiven
 * @time 2017-2-18  23:47
 * Email f842728368@163.com
 * @desc 自定义的精选--推荐的BannerView
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.forum.ForumHotBean;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.utils.ImageLoader;

import java.util.List;

/**
 * 轮播图，内部的网络图片加载实用的框架是 Picasso（compile 'com.squareup.picasso:picasso:2.5.2'）
 * 功能：
 * 1.可以加在本地或者网络图片；
 * 2.开始和停止自动播放；
 * 3.设置指示器的位置(左中右)和背景图片
 * 使用：
 * 1.设置宽高；
 * 2.添加数据；
 * 3.设置是否自定开始播放
 */
public class FlyBanner1 extends RelativeLayout {

    private static final int RMP = LayoutParams.MATCH_PARENT;
    private static final int RWP = LayoutParams.WRAP_CONTENT;
    private static final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private static final int WHAT_AUTO_PLAY = 2000;

    //Point位置
    public static final int CENTER = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private LinearLayout mPointRealContainerLl;

    private ViewPager mViewPager;
    //本地图片资源
    private List<Integer> mImages;
    //网络图片资源
    private List<String> mImageUrls;
    //是否是网络图片
    private boolean mIsImageUrl = false;
    //是否只有一张图片
    private boolean mIsOneImg = false;
    //是否可以自动播放
    private boolean mAutoPlayAble = true;
    //是否正在播放
    private boolean mIsAutoPlaying = false;


    //自动播放时间
    private int mAutoPalyTime = 3000;
    //当前页面位置
    private int mCurrentPositon;
    //指示点位置
    private int mPointPosition = CENTER;
    //指示点资源
    private int mPointDrawableResId = R.drawable.selector_bgabanner_point;
    //指示容器背景
    private Drawable mPointContainerBackgroundDrawable;
    //指示容器布局规则
    private LayoutParams mPointRealContainerLp;

    //提示语
    private TextView mTips;
    private List<String> mTipsDatas;

    //指示点是否可见
    private boolean mPointsIsVisible = true;

    /**
     * 主要标题
     */
    private TextView mTitleTv;
    /**
     * 副标题
     */
    private TextView mSubTitleTv;
    private List<ForumHotBean.InfoBean.FocusListBean> mDatas;

    private Handler mAutoPlayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPositon++;
            mViewPager.setCurrentItem(mCurrentPositon);
            mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);
        }
    };


    public FlyBanner1(Context context) {
        this(context, null);
    }

    public FlyBanner1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlyBanner1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlyBanner);

        mPointsIsVisible = a.getBoolean(R.styleable.FlyBanner_points_visibility, true);
        mPointPosition = a.getInt(R.styleable.FlyBanner_points_position, CENTER);
        mPointContainerBackgroundDrawable
                = a.getDrawable(R.styleable.FlyBanner_points_container_background);

        a.recycle();

        setLayout(context);
    }

    private void setLayout(Context context) {
        //关闭view的OverScroll
        setOverScrollMode(OVER_SCROLL_NEVER);
        //设置指示器背景
        if (mPointContainerBackgroundDrawable == null) {
            //mPointContainerBackgroundDrawable = new ColorDrawable(Color.parseColor("#00aaaaaa"));
            mPointContainerBackgroundDrawable = new ColorDrawable(getResources().getColor(R.color.colorGrayTransparent));
        }
        //添加ViewPager
        mViewPager = new ViewPager(context);
        addView(mViewPager, new LayoutParams(RMP, RMP));

        // 添加半透明的背景
        ImageView imageView = new ImageView(getContext());
        LayoutParams ivLp = new LayoutParams(RMP, RMP);
        imageView.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorGrayTransparent)));
        addView(imageView, ivLp);


        //设置指示器背景容器
        RelativeLayout pointContainerRl = new RelativeLayout(context);
        if (Build.VERSION.SDK_INT >= 16) {
            pointContainerRl.setBackground(mPointContainerBackgroundDrawable);
        } else {
            pointContainerRl.setBackgroundDrawable(mPointContainerBackgroundDrawable);
        }
        //设置内边距
        pointContainerRl.setPadding(0, 10, 0, 10);
        //设定指示器容器布局及位置
        LayoutParams pointContainerLp = new LayoutParams(RMP, RWP);
        pointContainerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(pointContainerRl, pointContainerLp);
        //设置指示器容器
        mPointRealContainerLl = new LinearLayout(context);
        mPointRealContainerLl.setOrientation(LinearLayout.HORIZONTAL);
        mPointRealContainerLp = new LayoutParams(RWP, RWP);
        pointContainerRl.addView(mPointRealContainerLl, mPointRealContainerLp);
        //设置指示器容器是否可见
        if (mPointRealContainerLl != null) {
            if (mPointsIsVisible) {
                mPointRealContainerLl.setVisibility(View.VISIBLE);
            } else {
                mPointRealContainerLl.setVisibility(View.GONE);
            }
        }
        //设置指示器布局位置
        if (mPointPosition == CENTER) {
            mPointRealContainerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else if (mPointPosition == LEFT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (mPointPosition == RIGHT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }


        // 设置标题
        mTitleTv = new TextView(getContext());
        LayoutParams mTitleTvLp = new LayoutParams(RWP, RWP);
        mTitleTvLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTitleTv.setBackgroundResource(R.drawable.bg_recommend_header_title);
        mTitleTv.setGravity(Gravity.CENTER);
        mTitleTv.setTextColor(Color.BLACK);
        mTitleTv.setTextSize(12);
        addView(mTitleTv, mTitleTvLp);


        // 设置子标题
        mSubTitleTv = new TextView(getContext());
        LayoutParams mSubTitleTvLp = new LayoutParams(RWP, RWP);
        mSubTitleTvLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mSubTitleTvLp.topMargin = AppUtil.dip2Px(35);
        mSubTitleTv.setTextColor(Color.WHITE);
        mSubTitleTv.setTextSize(15);
        addView(mSubTitleTv, mSubTitleTvLp);

    }

    /**
     * 设置本地图片
     *
     * @param images
     */
    public void setImages(List<Integer> images) {
        //加载本地图片
        mIsImageUrl = false;
        this.mImages = images;
        if (images.size() <= 1)
            mIsOneImg = true;
        //初始化ViewPager
        initViewPager();
    }

    /**
     * 设置数据，这里是根据setImagesUrl改的
     */
    public void setDatas(List<ForumHotBean.InfoBean.FocusListBean> datas) {
        mDatas = datas;
        //加载网络图片
        mIsImageUrl = true;
        if (datas.size() <= 1)
            mIsOneImg = true;
        //初始化ViewPager
        initViewPager();
    }

    /**
     * 设置网络图片
     *
     * @param urls
     */
    public void setImagesUrl(List<String> urls) {
        //加载网络图片
        mIsImageUrl = true;
        this.mImageUrls = urls;
        if (urls.size() <= 1)
            mIsOneImg = true;
        //初始化ViewPager
        initViewPager();
    }

    /**
     * 设置指示点是否可见
     *
     * @param isVisible
     */
    public void setPointsIsVisible(boolean isVisible) {
        if (mPointRealContainerLl != null) {
            if (isVisible) {
                mPointRealContainerLl.setVisibility(View.VISIBLE);
            } else {
                mPointRealContainerLl.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 对应三个位置 CENTER,RIGHT,LEFT
     *
     * @param position
     */
    public void setPointsPosition(int position) {
        //设置指示器布局位置
        if (position == CENTER) {
            mPointRealContainerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        } else if (position == LEFT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else if (position == RIGHT) {
            mPointRealContainerLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
    }

    private void initViewPager() {
        //当图片多于1张时添加指示点
        if (!mIsOneImg) {
            addPoints();
        }
        //设置ViewPager
        FlyPageAdapter adapter = new FlyPageAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        //跳转到首页
        mViewPager.setCurrentItem(1, false);
        //当图片多于1张时开始轮播
        if (!mIsOneImg) {
            startAutoPlay();
        }
    }


    /**
     * 返回真实的位置
     *
     * @param position
     * @return
     */
    private int toRealPosition(int position) {
        int realPosition;
        if (mIsImageUrl) {
            //realPosition = (position - 1) % mImageUrls.size();
            realPosition = (position - 1) % mDatas.size();
            if (realPosition < 0)
                //realPosition += mImageUrls.size();
                realPosition += mDatas.size();
        } else {
            realPosition = (position - 1) % mImages.size();
            if (realPosition < 0)
                realPosition += mImages.size();
        }

        return realPosition;
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (mIsImageUrl) {
                mCurrentPositon = position % (mDatas.size() + 2);
                //mCurrentPositon = position % (mImageUrls.size() + 2);
            } else {
                mCurrentPositon = position % (mImages.size() + 2);
            }
            switchToPoint(toRealPosition(mCurrentPositon));

            mTitleTv.setText(mDatas.get(toRealPosition(position)).getTitle());

            // 对子表逼做数据的处理
            String subtitle = mDatas.get(toRealPosition(position)).getSubtitleOne();
//            StringBuilder sb = new StringBuilder();
//            for (char c : subtitle.toCharArray()) {
//                sb.append(c).append("  /  ");
//            }
//            String substring = sb.substring(0, sb.length() - 5);

            mSubTitleTv.setText(subtitle);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int current = mViewPager.getCurrentItem();
                int lastReal = mViewPager.getAdapter().getCount() - 2;
                if (current == 0) {
                    mViewPager.setCurrentItem(lastReal, false);
                } else if (current == lastReal + 1) {
                    mViewPager.setCurrentItem(1, false);
                }
            }
        }
    };

    private class FlyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //当只有一张图片时返回1
            if (mIsOneImg) {
                return 1;
            }
            //当为网络图片，返回网页图片长度
            if (mIsImageUrl)
                //return mImageUrls.size() + 2;
                return mDatas.size() + 2;
            //当为本地图片，返回本地图片长度
            return mImages.size() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            final int realPosition = toRealPosition(position);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(realPosition, mDatas.get(realPosition));
                    }
                }
            });
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (mIsImageUrl) {
                /*Picasso.with(getContext())
                        .load(mImageUrls.get(toRealPosition(position)))
                        .into(imageView);*/
                ImageLoader.loadImage(getContext(), mDatas.get(toRealPosition(position)).getImg(), imageView);
            } else {
                imageView.setImageResource(mImages.get(toRealPosition(position)));
            }
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            if (object != null)
                object = null;
        }
    }


    /**
     * 添加指示点
     */
    private void addPoints() {
        mPointRealContainerLl.removeAllViews();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LWC, LWC);
        lp.setMargins(10, 10, 10, 10);
        ImageView imageView;

        //int length = mIsImageUrl ? mImageUrls.size() : mImages.size();
        int length = mDatas.size();
        for (int i = 0; i < length; i++) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(lp);
            imageView.setImageResource(mPointDrawableResId);
            mPointRealContainerLl.addView(imageView);
        }

        switchToPoint(0);
    }

    /**
     * 切换指示器
     *
     * @param currentPoint
     */
    private void switchToPoint(final int currentPoint) {
        for (int i = 0; i < mPointRealContainerLl.getChildCount(); i++) {
            mPointRealContainerLl.getChildAt(i).setEnabled(false);
        }
        mPointRealContainerLl.getChildAt(currentPoint).setEnabled(true);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mAutoPlayAble && !mIsOneImg) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    stopAutoPlay();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_OUTSIDE:
                    startAutoPlay();
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 开始播放
     */
    public void startAutoPlay() {
        if (mAutoPlayAble && !mIsAutoPlaying) {
            mIsAutoPlaying = true;
            mAutoPlayHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, mAutoPalyTime);
        }
    }

    /**
     * 停止播放
     */
    public void stopAutoPlay() {
        if (mAutoPlayAble && mIsAutoPlaying) {
            mIsAutoPlaying = false;
            mAutoPlayHandler.removeMessages(WHAT_AUTO_PLAY);
        }
    }


    /**
     * 设置自动播放的间隔时间，默认值是3000毫秒
     */
    public void setAutoPalyTime(int autoPalyTime) {
        mAutoPalyTime = autoPalyTime;
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, ForumHotBean.InfoBean.FocusListBean infoBean);
    }
}