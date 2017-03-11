package com.burning.iplay.ui.search;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.news.HotSearchData;
import com.burning.iplay.bean.news.NewsData;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.ui.refresh.RefreshPresenter;
import com.burning.iplay.utils.LogUtils;

import rx.Subscription;
import rx.functions.Action1;

/**
 * @author Kiven
 * @time 2017-3-6  16:09
 * Email f842728368@163.com
 * @desc 搜索界面对应的P层对象
 */
public class SearchPresenter extends RefreshPresenter<SearchFragment>
        implements SearchContract.Presetter {

    private Subscription mResultSubscription;

    @Override
    public void requestData(RequestParamsBean params) {

        mSubscription = ApiManager.getInstance().getNewsApi()
                .getHotSearchData()
                .compose(this.<HotSearchData>applySchedulers())
                .subscribe(new Action1<HotSearchData>() {
                    @Override
                    public void call(HotSearchData hotSearchData) {
                        mView.onLoadSuccesed();
                        // 得到数据之后显示搜索热词
                        mView.showHotSearch(hotSearchData.getInfo());
                    }
                }, mOnError);
    }

    @Override
    public void requestSearchData(RequestParamsBean params) {

        LogUtils.i("SearchPresenter requestSearchData()" );
        // 第一次请求的时候显示数据请求界面，后面都不再显示
        if (params.startIndex == 0) {
            mView.onLoading();
        }

        mResultSubscription = ApiManager.getInstance().getNewsSearchApi()
                .getSearchResult(params.keyWord, params.startIndex, params.dataCount)
                .compose(this.<NewsData>applySchedulers())
                .subscribe(new Action1<NewsData>() {
                    @Override
                    public void call(NewsData newsData) {

                        mView.onLoadSuccesed();

                        mView.updateSearchUI(newsData.getInfo());
                    }
                }, mOnError);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mResultSubscription == null) {
            mResultSubscription.unsubscribe();
            mResultSubscription = null;
        }
    }
}
