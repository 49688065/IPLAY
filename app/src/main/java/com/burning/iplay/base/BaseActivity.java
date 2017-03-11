package com.burning.iplay.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.burning.iplay.R;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author Kiven
 * @time 2017-2-16  16:41
 * Email f842728368@163.com
 * @desc 所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {


    /*运行时权限成功授权码*/
    protected static final int GRAND = PackageManager.PERMISSION_GRANTED;

    /**
     * 布局对应的View
     */
    protected View mView;
    /**
     * 减少内存泄漏用的解绑对象
     */
    protected Unbinder mUnBinder;
    /**
     * 是否是沉浸式
     */
    protected boolean isImmersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. 初始化窗口参数
        initWindowParams();

        // 2. 初始化窗口布局
        initWindowUI();

        // 3.动态申请权限
        requestPermissions();

    }


    // 1. 初始化窗口参数
    protected void initWindowParams() {
    }

    // 2. 初始化窗口布局
    private void initWindowUI() {
        // 设置布局
        mView = getLayoutInflater().inflate(getLayoutResId(), null);
        setContentView(mView);

        // 绑定控件
        mUnBinder = ButterKnife.bind(this);

        initUI();
    }


    /**
     * 子类必须实现的方法，返回布局ID
     *
     * @return 布局资源的ID
     */
    protected abstract int getLayoutResId();

    /**
     * 子类必须实现的方法，在初始化布局资源之后
     */
    protected abstract void initUI();

    /*请求权限*/
    protected void requestPermissions() {
        // 获得权限字符串数组
        final String[] permissions = AppUtil.getStringArr(getPermissionArrId());

        // 判断系统是不是大于等于M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean result = true;
            for (String permission : permissions) {
                if (GRAND != ActivityCompat.checkSelfPermission(this, permission)) {
                    result = false;
                    break;
                }
            }
            if (result) {
                requestPermissionSucceed();
            } else {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        ActivityCompat.requestPermissions(BaseActivity.this, permissions, 0);
                    }
                });
            }
        } else {
            requestPermissionSucceed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        List<String> failed = new ArrayList<>();

        // 检查权限请求结果
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != GRAND)
                failed.add(permissions[i]);
        }

        // 报告每个请求失败的权限
        if (!failed.isEmpty()) {
            requestPermissionFailed(failed);
            return;
        }

        // 权限请求成功
        requestPermissionSucceed();
    }


    /**
     * 权限请求成功时调用
     */
    protected abstract void requestPermissionSucceed();

    /**
     * 权限请求失败时调用
     */
    protected void requestPermissionFailed(List<String> failedPermissions) {
        ToastUtil.showToast(mView, failedPermissions.toString());

    }

    /**
     * 返回权限数组资源id
     */
    protected int getPermissionArrId() {
        return R.array.basic_permissions;
    }

    /**
     * 请求沉浸式
     */
    protected void requestImmersion() {
        isImmersion = true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isImmersion)
            return;
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }


}
