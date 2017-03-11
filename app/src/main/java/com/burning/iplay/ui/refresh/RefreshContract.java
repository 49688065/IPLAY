package com.burning.iplay.ui.refresh;

import com.burning.iplay.base.BaseFragmentContract;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-18  9:53
 * Email f842728368@163.com
 * @desc
 */
public interface RefreshContract {

    interface View<E> extends BaseFragmentContract.View {
        @Override
        void initPresenter();

        void refreshRecyclerView(List<E> datas);
    }


    interface Presenter<T extends View> extends BaseFragmentContract.Presenter<T> {

        @Override
        void start();

        @Override
        void detachView();


    }
}
