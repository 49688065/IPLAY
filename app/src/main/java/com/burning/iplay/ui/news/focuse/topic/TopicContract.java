package com.burning.iplay.ui.news.focuse.topic;

import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.ui.refresh.RefreshContract;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-20  0:37
 * Email f842728368@163.com
 * @desc
 */
public interface TopicContract {

    interface View extends RefreshContract.View<ArticleListBean> {

        @Override
        void refreshRecyclerView(List<ArticleListBean> datas);

        /**
         * 根据P层传入的数据来决定ImageButton的显示图片
         */
        void updateFocuseState(boolean isFocused);
    }

    interface Presenter<T extends View> extends RefreshContract.Presenter<T> {
        @Override
        void requestData(RequestParamsBean params);

        /**
         * 关注ImageButton的点击事件，改变关注状态
         */
        void changeFocuseState();
    }
}
