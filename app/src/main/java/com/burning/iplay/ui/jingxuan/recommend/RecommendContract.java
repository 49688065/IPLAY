package com.burning.iplay.ui.jingxuan.recommend;

import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.jingxuan.RecommendBanner;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.ui.refresh.RefreshContract;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-18  20:02
 * Email f842728368@163.com
 * @desc 规定RecommendFragment和RecommendPresenter的方法
 */
public interface RecommendContract {

    interface View extends RefreshContract.View<ArticleListBean> {

        @Override
        void refreshRecyclerView(List<ArticleListBean> datas);

        void refreshBanner(List<RecommendBanner.InfoBean> bannerData);
    }

    interface Presenter<T extends View> extends RefreshContract.Presenter<T> {

        @Override
        void requestData(RequestParamsBean params);
    }
}
