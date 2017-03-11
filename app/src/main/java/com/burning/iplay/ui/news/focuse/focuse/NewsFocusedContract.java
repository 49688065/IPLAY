package com.burning.iplay.ui.news.focuse.focuse;

import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.news.TopTopicListBean;
import com.burning.iplay.ui.news.item.NewsItemContract;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-17  14:59
 * Email f842728368@163.com
 * @desc 关注界面的协议
 */
public interface NewsFocusedContract {

    interface View<E> extends NewsItemContract.View<E> {



        void setIsFocused(boolean isFocused);

        /**
         * 更新头部关注列表的界面
         *
         * @param FocusedData 要展示的内容
         */
        void refreshFocusedView(List<TopTopicListBean> FocusedData);
    }

    interface Presenter<T extends View> extends NewsItemContract.Presenter<T> {
        @Override
        void start();

        @Override
        void detachView();

        @Override
        void attachView(T view);

        @Override
        void requestData(RequestParamsBean params);
    }
}
