package com.burning.iplay.ui.forum.recommend;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.forum.ForumHotBean;
import com.burning.iplay.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;


/**
 * @FileName HotPresenter
 * @Author Jay_Ping
 * @Time 2017/3/6
 * @Email 1054335787@qq.com
 * @Desc 社区 热门推荐
 */
public class HotPresenter<T extends HotContract.View>
        extends com.burning.iplay.base.BaseFragmentPresenter<T>
        implements HotContract.Presenter<T>, com.burning.iplay.ui.refresh.RefreshContract.Presenter<T> {

    private Subscription mSubscription2;
    private int mStart;

    @Override
    public void requestData(RequestParamsBean params) {

        //mStart = params.startIndex + 1;

        mSubscription = ApiManager.getInstance().getNewsApi()
                .getForumHotData(params.startIndex, params.dataCount).compose(this.<ForumHotBean>applySchedulers()).subscribe(new Action1<ForumHotBean>() {
                    @Override
                    public void call(ForumHotBean forumHotBean) {
                        LogUtils.d("HotPresenter ping", forumHotBean.toString());

                        mView.onLoadSuccesed();
                        // 1加载 列表 数据

                        List<ForumHotBean.InfoBean.ThreadListBean> threadListData = forumHotBean.getInfo().getThreadList();
                        if (threadListData != null) {
                            mView.refreshRecyclerView(threadListData);
                        } else {
                            mView.refreshRecyclerView(new ArrayList<ForumHotBean.InfoBean.ThreadListBean>());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onLoadError("社区热门推荐出现了问题", throwable);
                    }
                });
        mSubscription2 = ApiManager.getInstance().getNewsApi()
                .getForumHotData(params.startIndex, params.dataCount)
                .compose(this.<ForumHotBean>applySchedulers())
                .subscribe(new Action1<ForumHotBean>() {
                    @Override
                    public void call(ForumHotBean forumHotBean) {
                        LogUtils.d("forumHotBean", forumHotBean.toString());
                        mView.refreshScorrview(forumHotBean.getInfo().getFocusList());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onLoadError("社区Banner数据出现了异常", throwable);
                    }
                });
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
