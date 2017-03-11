package com.burning.iplay.ui.jingxuan.zla;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.news.NewsData;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.ui.refresh.RefreshPresenter;
import com.burning.iplay.utils.LogUtils;

import rx.functions.Action1;

/**
 * @author Kiven
 * @time 2017-3-1  21:09
 * Email f842728368@163.com
 * @desc
 */

public class ZlaPresenter extends RefreshPresenter<ZlaFragment> {


    @Override
    public void requestData(RequestParamsBean params) {
        LogUtils.i("ZlaPresenter requestData()");
        mSubscription = ApiManager.getInstance().getNewsApi()
                .getNews(params.id, params.startIndex, params.dataCount, false)
                .compose(this.<NewsData>applySchedulers())
                .subscribe(new Action1<NewsData>() {
                    @Override
                    public void call(NewsData newsData) {
                        LogUtils.i("ZlaPresenter call()" );
                        mView.onLoadSuccesed();
                        mView.refreshRecyclerView(newsData.getInfo());
                    }
                }, mOnError);
    }
}
