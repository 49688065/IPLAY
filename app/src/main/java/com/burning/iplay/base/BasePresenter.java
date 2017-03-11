package com.burning.iplay.base;

/**
 * @author Kiven
 * @time 2017-2-16  16:42
 * Email f842728368@163.com
 * @desc 所有P层的总接口
 */
public interface BasePresenter<T extends BaseView> {

    /**
     * 订阅开始之前的准备工作
     */
    void start();

    /**
     * 让P层持有V层的对象
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 取消持有，防止内存泄漏
     */
    void detachView();

}
