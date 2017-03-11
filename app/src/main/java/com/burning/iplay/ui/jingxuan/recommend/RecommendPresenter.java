package com.burning.iplay.ui.jingxuan.recommend;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.news.NewsData;
import com.burning.iplay.bean.jingxuan.RecommendBanner;
import com.burning.iplay.bean.RequestParamsBean;

import rx.Subscription;
import rx.functions.Action1;


/**
 * @author Kiven
 * @time 2017-2-18  19:20
 * Email f842728368@163.com
 * @desc 精选-推荐
 */
public class RecommendPresenter<T extends RecommendContract.View>
        extends com.burning.iplay.base.BaseFragmentPresenter<T>
        implements RecommendContract.Presenter<T>, com.burning.iplay.ui.refresh.RefreshContract.Presenter<T> {

    private Subscription mSubscription2;

    @Override
    public void requestData(RequestParamsBean params) {

        mSubscription = ApiManager.getInstance().getNewsApi()
                .getRecommendData()
                .compose(this.<NewsData>applySchedulers())
                .subscribe(new Action1<NewsData>() {
                    @Override
                    public void call(NewsData newsData) {
                        mView.onLoadSuccesed();
                        // 加载主要数据
                        mView.refreshRecyclerView(newsData.getInfo());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onLoadError("精选-推荐出现了问题", throwable);
                    }
                });


        mSubscription2 = ApiManager.getInstance().getNewsApi()
                .getRecommendBannerData()
                .compose(this.<RecommendBanner>applySchedulers())
                .subscribe(new Action1<RecommendBanner>() {
                    @Override
                    public void call(RecommendBanner recommendBanner) {
                        mView.refreshBanner(recommendBanner.getInfo());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onLoadError("Banner数据出现了异常", throwable);
                    }
                });

        // TODO  Rxjava的组合操作符

    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription2 != null) {
            mSubscription2.unsubscribe();
            mSubscription2 = null;
        }
    }
}
