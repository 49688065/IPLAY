package com.burning.iplay.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.comment.Comment;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Kiven
 * @time 2017-3-7  15:06
 * Email f842728368@163.com
 * @desc 评论的楼中楼效果
 */
public class FloorView extends LinearLayout {

    private int density;
    private Drawable mBoundDrawable;
    private List<Comment> mDatas;                // 评论的数据
    private SubFloorFactory mFactory;

    public FloorView(Context context) {
        super(context);
        init(context);
    }

    public FloorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setBoundDrawer(Drawable drawable) {
        mBoundDrawable = drawable;
    }

    public void setDatas(List<Comment> datas) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.clear();
        mDatas.addAll(datas);
    }

    public void setFactory(SubFloorFactory fac) {
        mFactory = fac;
    }

    public int getFloorNum() {
        return getChildCount();
    }

    private void init(Context context) {
        // 设置为垂直方向
        this.setOrientation(LinearLayout.VERTICAL);
        // 获取手机屏幕相对密度(常见于 dp和px转换的公式里面)
        density = (int) (3.0F * context.getResources().getDisplayMetrics().density);
    }

    public void init() {
        removeAllViews();
        // 如果没有数据直接返回
        if (mDatas.size() == 0)
            return;
        // 如果评论回复数小于7个，则直接显示完
        if (mDatas.size() < 5) {
            showAllSubComment();
        } else {
            // 如果评论回复数很多，则显示一个“显示隐藏楼层”的按钮
            View view;
            view = mFactory.buildSubFloor(mDatas.get(0), 0, this);
            addView(view);
            view = mFactory.buildSubFloor(mDatas.get(1), 1, this);
            addView(view);
            // 可以点击显示更多的楼层
            view = mFactory.buildSubHideFloor(mDatas.get(2), this);
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    TextView hide_text = (TextView) v
                            .findViewById(R.id.hide_text);

                    hide_text.setVisibility(View.VISIBLE);
                    removeAllViews();
                    for (int i = 0; i < mDatas.size(); i++) {
                        View view = mFactory.buildSubFloor(mDatas.get(i), i, FloorView.this);
                        addView(view);
                    }
                    reLayoutChildren();
                }
            });
            addView(view);
            view = mFactory.buildSubFloor(mDatas.get(mDatas.size() - 1), mDatas.size() - 1, this);
            addView(view);
        }

        reLayoutChildren();
    }

    /**
     * 显示所有的评论
     */
    private void showAllSubComment() {
        for (int i = 0; i < mDatas.size(); i++) {
            View view = mFactory.buildSubFloor(mDatas.get(i), i, this);
            addView(view);
        }
    }

    /**
     * 重新布局子View的位置，呈现出楼层的效果
     */
    public void reLayoutChildren() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT);
            layout.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            int margin = Math.min((count - i - 1), 4) * density;
            layout.leftMargin = margin;
            layout.rightMargin = margin;
            if (i == count - 1) {
                layout.topMargin = 0;
            } else {
                layout.topMargin = Math.min((count - i), 4) * density;
            }
            view.setLayoutParams(layout);
        }
    }

    /**
     * 分发给子组件进行绘制，给每个子View画背景
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        int i = getChildCount();
        if (null != mBoundDrawable && i > 0) {
            for (int j = i - 1; j >= 0; j--) {
                View view = getChildAt(j);
                // drawable将在被绘制在canvas的哪个矩形区域内。
                mBoundDrawable.setBounds(view.getLeft(), view.getLeft(),
                        view.getRight(), view.getBottom());
                mBoundDrawable.draw(canvas);
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.getChildCount() <= 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
