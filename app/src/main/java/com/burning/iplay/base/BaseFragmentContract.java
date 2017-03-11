package com.burning.iplay.base;

import com.burning.iplay.bean.RequestParamsBean;

/**
 * @author Kiven
 * @time 2017-2-16  18:31
 * Email f842728368@163.com
 * @desc Fragment和对应P层应该遵守的规范
 */
public interface BaseFragmentContract {


    interface View extends BaseView {

        void onLoading();

        @Override
        void initPresenter();

        void onLoadSuccesed();

        void onLoadError(String msg, Throwable e);
    }

    interface Presenter<T extends View> extends BasePresenter<T> {

        @Override
        void start();

        @Override
        void attachView(T view);

        @Override
        void detachView();

        void requestData(RequestParamsBean params);
    }
}
