package com.burning.iplay.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @author Kiven
 * @time 2017-2-15  19:36
 * Email f842728368@163.com
 * @desc Toast工具类
 */
public class ToastUtil {
    private ToastUtil() {
    }

    public static void showToast(View view, String msg) {
        if (view != null && msg != null) {
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        }
    }
}
