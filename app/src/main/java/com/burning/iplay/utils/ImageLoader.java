package com.burning.iplay.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.burning.iplay.config.Constants;

/**
 * @author Kiven
 * @time 2017-2-16  17:00
 * Email f842728368@163.com
 * @desc 加载图片的工具类
 */
public class ImageLoader {

    private ImageLoader() {
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        // 夜间模式的处理
        if (SPUtil.getBoolean(Constants.IS_NIGHT_MODE)) {
            imageView.setAlpha(0.2f);
            imageView.setBackgroundColor(Color.BLACK);
        }
        Glide.with(context).load(url).into(imageView);
    }
}
