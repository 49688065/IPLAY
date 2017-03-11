package com.burning.iplay.ui.forum.recommend;

import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.forum.ForumHotBean;
import com.burning.iplay.ui.refresh.RefreshContract;

import java.util.List;

/**
 * @FileName HotContract
 * @Author Jay_Ping
 * @Time 2017/3/6
 * @Email 1054335787@qq.com
 * @Desc 规定HotFragment和HotPresenter的方法
 */
public interface HotContract {

    interface View extends RefreshContract.View<ForumHotBean.InfoBean.ThreadListBean> {

        @Override
        void refreshRecyclerView(List<ForumHotBean.InfoBean.ThreadListBean> datas);

        void refreshScorrview(List<ForumHotBean.InfoBean.FocusListBean> bannerData);
    }

    interface Presenter<T extends View> extends RefreshContract.Presenter<T> {

        @Override
        void requestData(RequestParamsBean params);
    }
}
