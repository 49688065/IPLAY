package com.burning.iplay.base;

import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.burning.iplay.R;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.utils.AppUtil;
import com.burning.iplay.utils.LogUtils;
import com.burning.iplay.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Kiven
 * @time 2017-2-16  17:33
 * Email f842728368@163.com
 * @desc Fragment的基类
 */
public abstract class BaseFragment<T extends BaseFragmentContract.Presenter> extends Fragment
        implements BaseFragmentContract.View {

    /*运行时权限成功授权码*/
    private static final int GRAND = PackageManager.PERMISSION_GRANTED;


    @BindView(R.id.loading_rl)
    protected ViewGroup mLoadingRl;
    @BindView(R.id.loading_iv)
    protected ImageView mLoadingIv;
    @BindView(R.id.load_error_rl)
    protected ViewGroup mLoadErrorContainer;
    @BindView(R.id.load_error)
    protected ViewGroup mLoadErrorRetry;
    @BindView(R.id.content_container)
    protected ViewGroup mContentContainer;

    /**
     * Loading时的帧动画
     */
    private AnimationDrawable mLoadAnimation;

    protected RequestParamsBean mParams = new RequestParamsBean();
    protected T mPresenter;
    protected View thisView;
    private Unbinder mUnbinder;

    /**
     * 子类必须实现的方法，返回布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化发送网络请求的参数，方法调用在ActivityCreated之后，
     */
    protected abstract void initParams();

    /**
     * 控件绑定之后的初始化的方法，方法调用在initParams之后
     */
    protected abstract void initialize();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (thisView == null)
            thisView = inflater.inflate(getLayoutResId(), container, false);
        mUnbinder = ButterKnife.bind(this, thisView);
        initPresenter();
        return thisView;
    }

    public void initPresenter() {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    /**
     * 创建对应的P层对象
     */
    protected abstract T createPresenter();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initParams();
        initialize();
        // 5. 请求权限
        requestPermissions();
    }


    /*请求权限*/
    private void requestPermissions() {
        // 获得权限字符串数组
        final String[] permissions = AppUtil.getStringArr(getPermissionArrId());

        // 判断系统是不是大于等于M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean result = true;
            for (String permission : permissions) {
                if (GRAND != ActivityCompat.checkSelfPermission(getActivity(), permission)) {
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
                        ActivityCompat.requestPermissions(getActivity(), permissions, 0);
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
        ToastUtil.showToast(getView(), failedPermissions.toString());

    }

    /**
     * 返回权限数组资源id
     */
    protected int getPermissionArrId() {
        return R.array.basic_permissions;
    }

    @Override
    public void onLoading() {
        mLoadingRl.setVisibility(View.VISIBLE);
        mLoadAnimation = (AnimationDrawable) mLoadingIv.getDrawable();
        if (mLoadAnimation != null) {
            mLoadAnimation.start();
        }
        mContentContainer.setVisibility(View.GONE);
        mLoadErrorContainer.setVisibility(View.GONE);
    }


    /**
     * 数据错误时调用的方法
     */
    @Override
    public void onLoadError(String msg, Throwable e) {
        if (mLoadAnimation != null) {
            mLoadAnimation.stop();
        }
        mLoadingRl.setVisibility(View.GONE);
        mLoadErrorContainer.setVisibility(View.VISIBLE);
        mContentContainer.setVisibility(View.GONE);
        e.printStackTrace();
        LogUtils.i("BaseFragment onLoadError()" + msg + e.getMessage());
        mLoadErrorRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestData(mParams);
            }
        });
    }


    /**
     * 有数据时调用的方法
     */
    @Override
    public void onLoadSuccesed() {
        if (mLoadAnimation != null) {
            mLoadAnimation.stop();
        }
        if (mLoadingRl != null) {
            mLoadingRl.setVisibility(View.GONE);
        }
        mLoadErrorContainer.setVisibility(View.GONE);
        mContentContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter = null;
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //MyApplication.mRefWatcher.watch(this);
    }
}
