package com.burning.iplay.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.burning.iplay.ui.detail.DetailActivity;

/**
 * @author Kiven
 * @time 2017-2-18  23:35
 * Email f842728368@163.com
 * @desc Activity相关工具
 */
public class ActivityUtil {

    private ActivityUtil() {
    }

    /**
     * 跳转到某个Activity中
     */
    public static void startActivity(Activity activity, Class clz) {
        activity.startActivity(new Intent(activity, clz));
    }

    /**
     * 跳转到某个Activity中
     */
    public static void startActivityAndFinishSelf(Activity activity, Class clz) {
        activity.startActivity(new Intent(activity, clz));
        activity.finish();
    }

    /**
     * 跳转到新闻详情界面
     */
    public static void startDetailActivity(Activity activity, String docid) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.DOCID, docid);
        activity.startActivity(intent);
    }


    /**
     * 这里我设置的图片在状态栏的下面，并且给状态栏设置了一个浅浅的透明色。
     *
     * @param color
     */
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // 如果想要给状态栏加点颜色，例如加点透明的阴影，就需要加上下面的三行代码
            // 这是生成一个状态栏大小的矩形，给这个矩形添加颜色，添加 statusView 到布局中
            View statusView = createStatusView(activity, color);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);


            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            // 这个是决定我们的布局是否是在状态栏的下面
            rootView.setFitsSystemWindows(false);
            rootView.setClipToPadding(true);
        }

    }

    /**
     * 这个生成一个状态栏大小的矩形，给这个矩形，添加 statusView 到布局中
     *
     * @param activity
     * @param color
     * @return
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }
}
