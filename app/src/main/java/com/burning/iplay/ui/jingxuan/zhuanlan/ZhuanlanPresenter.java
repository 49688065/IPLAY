package com.burning.iplay.ui.jingxuan.zhuanlan;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.jingxuan.Zhuanlan;
import com.burning.iplay.ui.refresh.RefreshPresenter;

import rx.functions.Action1;

/**
 * @author Kiven
 * @time 2017-2-27  23:42
 * Email f842728368@163.com
 * @desc 精选-专栏
 */
public class ZhuanlanPresenter extends RefreshPresenter<ZhuanlanFragment> {



    @Override
    public void requestData(RequestParamsBean params) {


        mSubscription = ApiManager.getInstance().getNewsApi()
                .getZhuanlanData()
                .compose(this.<Zhuanlan>applySchedulers())
                .subscribe(new Action1<Zhuanlan>() {
                    @Override
                    public void call(Zhuanlan zhuanlan) {

                        mView.onLoadSuccesed();

                        mView.refreshRecyclerView(zhuanlan.getInfo());

                    }
                }, mOnError);

    }
}
