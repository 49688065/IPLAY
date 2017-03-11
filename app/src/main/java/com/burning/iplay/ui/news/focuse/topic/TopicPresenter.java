package com.burning.iplay.ui.news.focuse.topic;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.base.BaseFragmentPresenter;
import com.burning.iplay.bean.news.NewsData;
import com.burning.iplay.bean.RequestParamsBean;

import rx.functions.Action1;

/**
 * @author Kiven
 * @time 2017-2-20  0:37
 * Email f842728368@163.com
 * @desc
 */
public class TopicPresenter
        extends BaseFragmentPresenter<TopicFragment>
        implements TopicContract.Presenter<TopicFragment> {


    private boolean mIsFocused;

    @Override
    public void requestData(RequestParamsBean params) {
        mSubscription = ApiManager.getInstance().getNewsApi()
                .getNews(params.id, params.startIndex, params.dataCount, false)
                .compose(this.<NewsData>applySchedulers())
                .subscribe(new Action1<NewsData>() {
                    @Override
                    public void call(NewsData newsData) {
                        mView.onLoadSuccesed();
                        // 更新数据
                        mView.refreshRecyclerView(newsData.getInfo());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onLoadError("Topic Fragment出问题了", throwable);
                    }
                });
    }

    @Override
    public void changeFocuseState() {
        // TODO  更改磁盘中的关注状态，并且保证更改成功之后才会更新界面
        mIsFocused = !mIsFocused;
        mView.updateFocuseState(mIsFocused);
    }
}
