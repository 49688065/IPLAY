package com.burning.iplay.ui.news.item;

import com.burning.iplay.ui.refresh.RefreshContract;

/**
 * @author Kiven
 * @time 2017-2-16  22:13
 * Email f842728368@163.com
 * @desc 新闻列表的协议
 */
public interface NewsItemContract {

    interface View<E> extends RefreshContract.View<E> {


    }

    interface Presenter<T extends View> extends RefreshContract.Presenter<T> {

    }

}
