package com.burning.iplay.ui.forum.kaien;

import android.util.Log;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.detail.IplayCommunity;
import com.burning.iplay.ui.refresh.RefreshPresenter;

import rx.functions.Action1;

/**
 * @author lam
 * @time 2017-03-06  15:32
 * @desc  社区-爱玩社区的p层
 */

public class KaienPresenter extends RefreshPresenter<KaienFragment> {
    @Override
    public void requestData(RequestParamsBean params) {
        Log.i("tag", "KaienPresenter requestData()");
        mSubscription = ApiManager.getInstance().getNewsApi()
                .getKaienData()
                .compose(this.<IplayCommunity>applySchedulers())
                .subscribe(new Action1<IplayCommunity>() {
                    @Override
                    public void call(IplayCommunity iplayCommunity) {
                        Log.i("tag", "KaienPresenter call()"+iplayCommunity.getInfo().getDiscuzList());
                        mView.onLoadSuccesed();
                        mView.refreshRecyclerView(iplayCommunity.getInfo().getDiscuzList());

                    }
                },mOnError);
    }
}
