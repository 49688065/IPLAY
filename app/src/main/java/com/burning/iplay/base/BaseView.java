package com.burning.iplay.base;

/**
 * @author Kiven
 * @time 2017-2-16  16:41
 * Email f842728368@163.com
 * @desc 所有V层的基类
 */
public interface BaseView {

    /**
     * 注入P层对象，要在V层中调用，每个V层都要实现的方法，具体格式为
     * mPresenter = new 对应的P层对象；
     * mPresenter.attachView(this);
     */
    void initPresenter();

}
