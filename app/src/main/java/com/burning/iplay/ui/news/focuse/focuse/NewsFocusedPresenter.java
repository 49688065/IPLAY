package com.burning.iplay.ui.news.focuse.focuse;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.jingxuan.FocuseData;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.ui.news.item.NewsItemPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author Kiven
 * @time 2017-2-17  13:51
 * Email f842728368@163.com
 * @desc 关注的P层
 */
public class NewsFocusedPresenter extends NewsItemPresenter<NewsFocusedFragment>
        implements NewsFocusedContract.Presenter<NewsFocusedFragment> {

    List<String> mFocuseIds;
    boolean mIsFocused;

    @Override
    public void start() {
        super.start();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // TODO 从磁盘中读取topicId和IsFocused
        mFocuseIds = new ArrayList<>();
        //mFocuseIds.add("T1461396524087");

        mView.setIsFocused(true);
    }


    @Override
    public void requestData(RequestParamsBean params) {
        mSubscription =ApiManager.getInstance().getNewsApi()
                .getFocuseData(params.startIndex, params.dataCount, mFocuseIds)
                .compose(this.<FocuseData>applySchedulers())
                .subscribe(new Action1<FocuseData>() {
                    @Override
                    public void call(FocuseData focuseData) {

                        mView.onLoadSuccesed();
                        // 刷新新闻列表
                        mView.refreshRecyclerView(focuseData.getInfo().getArticleList());

                        // 刷新关注列表
                        mView.refreshFocusedView(focuseData.getInfo().getTopTopicList());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        mView.onLoadError("关注Fragment出错", throwable);
                    }
                });
    }
}
