package com.burning.iplay.ui.refresh;

import com.burning.iplay.base.BaseFragmentPresenter;

/**
 * @author Kiven
 * @time 2017-2-18  10:26
 * Email f842728368@163.com
 * @desc T : 对应的V层对象的类型，本层没有规定方法，只是规范了泛型
 */
public abstract class RefreshPresenter<T extends RefreshContract.View>
        extends BaseFragmentPresenter<T>
        implements RefreshContract.Presenter<T> {



}
