package com.burning.iplay.base;

import com.burning.iplay.bean.RequestParamsBean;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Kiven
 * @time 2017-2-16  20:06
 * Email f842728368@163.com
 * @desc
 */
public abstract class BaseFragmentPresenter<T extends BaseFragmentContract.View>
        implements BaseFragmentContract.Presenter<T> {

    protected T mView;
    protected Subscription mSubscription;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.onLoading();
    }


    @Override
    public void detachView() {
        mView = null;
        if (mSubscription == null) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    @Override
    public abstract void requestData(RequestParamsBean params);

    /**
     * 在网络请求中转换线程
     *
     * @param <T> 返回的数据的类型，也就是Call中的参数的类型，保证
     */
    protected <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 网络请求时对网络错误的处理，LOG打印错误信息，并展示错误界面，提示用户重试
     */
    protected Action1<Throwable> mOnError = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            mView.onLoadError(getClass().getSimpleName(), throwable);
        }
    };
}
