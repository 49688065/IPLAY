package com.burning.iplay.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.news.TopTopicListBean;
import com.burning.iplay.utils.ImageLoader;
import com.burning.iplay.utils.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Kiven
 * @time 2017-2-17  16:28
 * Email f842728368@163.com
 * @desc 资讯-关注界面没有关注时展示的view
 */
public class FocusedHeaderView extends LinearLayout {

    private final Context mContext;

    @BindView(R.id.item_focuse1_iv)
    ImageView mItemFocuse1Iv;
    @BindView(R.id.item_focuse1_tv)
    TextView mItemFocuse1Tv;
    @BindView(R.id.item_focuse2_iv)
    ImageView mItemFocuse2Iv;
    @BindView(R.id.item_focuse2_tv)
    TextView mItemFocuse2Tv;
    @BindView(R.id.item_focuse3_iv)
    ImageView mItemFocuse3Iv;
    @BindView(R.id.item_focuse3_tv)
    TextView mItemFocuse3Tv;
    @BindView(R.id.item_focuse_click)
    TextView mItemFocuseClick;

    public FocusedHeaderView(Context context) {
        super(context);
        mContext = context;
        initUI();

        mItemFocuseClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("FocusedHeaderView onClick()" + "NULL");
                if (mListener != null) {
                    LogUtils.i("FocusedHeaderView onClick()" + "NOTNULL");
                    mListener.onClick(v);
                }
            }
        });
    }

    private void initUI() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_not_focuse, this);
        ButterKnife.bind(this);
    }

    public void setData(final List<TopTopicListBean> data) {

        ImageLoader.loadImage(mContext, data.get(0).getNewIcon(), mItemFocuse1Iv);
        ImageLoader.loadImage(mContext, data.get(1).getNewIcon(), mItemFocuse2Iv);
        ImageLoader.loadImage(mContext, data.get(2).getNewIcon(), mItemFocuse3Iv);
        mItemFocuse1Tv.setText(data.get(0).getTopicName());
        mItemFocuse2Tv.setText(data.get(1).getTopicName());
        mItemFocuse3Tv.setText(data.get(2).getTopicName());

        mItemFocuse1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, data.get(0));
                }
            }
        });
        mItemFocuse2Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, data.get(1));
                }
            }
        });
        mItemFocuse3Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, data.get(2));
                }
            }
        });
    }

    //************************* 点击事件接口相关 ******************************/
    public interface OnClickListener {
        /**
         * 点击事件的回调，
         */
        void onClick(View v);

        void onItemClick(View v, TopTopicListBean bean);
    }

    private OnClickListener mListener;

    public void setOnViewClickListener(OnClickListener listener) {
        mListener = listener;
    }


}
