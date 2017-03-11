package com.burning.iplay.ui.news.item;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.base.BaseFragmentPresenter;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.db.NewsLog;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.news.NewsData;
import com.burning.iplay.ui.refresh.RefreshContract;
import com.burning.iplay.ui.refresh.RefreshFragment;
import com.burning.iplay.utils.SPUtil;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Kiven
 * @time 2017-2-16  22:12
 * Email f842728368@163.com
 * @desc 头条Fragment的P层
 */
public class NewsItemPresenter<T extends RefreshFragment>
        extends BaseFragmentPresenter<T>
        implements RefreshContract.Presenter<T> {


    @Override
    public void requestData(final RequestParamsBean params) {
        mSubscription = ApiManager.getInstance().getNewsApi()
                .getNews(params.id, params.startIndex, params.dataCount, false)
                /*.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {// 这里是从SP中取数据

                        String jsonData = SPUtil.getString(params.id);
                        if (TextUtils.isEmpty(jsonData)) {
                            return;
                        } else {

                            ArticleListBean[] beanLists = new Gson().fromJson(jsonData, ArticleListBean[].class);

                            LogUtils.i("NewsItemPresenter call()" + "从本地获得数据");
                            //thisView.onLoadSuccesed();
                            //thisView.refreshRecyclerView(Arrays.asList(beanLists));
                        }
                    }
                })*/
                //.subscribeOn(Schedulers.io()) //指定从本地获得数据时在子线程进行
                //                .flatMap(new Func1<ArticleListBean, Observable<List<ArticleListBean>>>() {
                //                    @Override
                //                    public Observable<List<ArticleListBean>> call(ArticleListBean articleListBean) {
                //
                //                        List<NewsLog> allLooked = DataSupport.findAll(NewsLog.class);
                //                        List<String> ids = new ArrayList<String>();
                //                        for (NewsLog newsLog : allLooked) {
                //                            ids.add(newsLog.getLooked());
                //                        }
                //
                //                        if (ids.contains(articleListBean.getDocid())) {
                //                            articleListBean.setLooked(true);
                //                        } else {
                //                            articleListBean.setLooked(false);
                //                        }
                //
                //                        return Observable.just(articleListBean).toList();
                //                    }
                //                })
                .flatMap(new Func1<NewsData, Observable<List<ArticleListBean>>>() {
                    @Override
                    public Observable<List<ArticleListBean>> call(NewsData newsData) {
                        // TODO  从数据库获取数据，并检查新闻是否被浏览过，

                        List<ArticleListBean> result = newsData.getInfo();
                        List<NewsLog> allLooked = DataSupport.findAll(NewsLog.class);
                        List<String> ids = new ArrayList<>();
                        for (NewsLog newsLog : allLooked) {
                            ids.add(newsLog.getLooked());
                        }
                        for (ArticleListBean bean : result) {
                            if (ids.contains(bean.getDocid())) {
                                bean.setLooked(true);
                            } else {
                                bean.setLooked(false);
                            }
                        }
                        return Observable.just(result);
                    }
                })
                //                .flatMap(new Func1<NewsData, Observable<ArticleListBean>>() {
                //                    @Override
                //                    public Observable<ArticleListBean> call(NewsData newsData) {
                //                        LogUtils.i("NewsItemPresenter flatMap" + newsData.getInfo().toString());
                //                        return Observable.from(newsData.getInfo());
                //                    }
                //                })
                //                .flatMap(new Func1<ArticleListBean, Observable<List<ArticleListBean>>>() {
                //                    @Override
                //                    public Observable<List<ArticleListBean>> call(final ArticleListBean articleListBean) {
                //                        Observable.create(new Observable.OnSubscribe<List<String>>() {
                //
                //                            @Override
                //                            public void call(Subscriber<? super List<String>> subscriber) {
                //                                List<NewsLog> allLooked = DataSupport.findAll(NewsLog.class);
                //                                List<String> ids = new ArrayList<String>();
                //                                for (NewsLog newsLog : allLooked) {
                //                                    ids.add(newsLog.getLooked());
                //                                }
                //                                subscriber.onNext(ids);
                //                            }
                //                        }).subscribeOn(Schedulers.io())// 指定订阅，即被观察者发送消息在子线程
                //                                .flatMap(new Func1<List<String>, Observable<Boolean>>() {
                //                                    @Override
                //                                    public Observable<Boolean> call(List<String> ids) {
                //                                        return Observable.just(ids.contains(articleListBean.getDocid()));
                //                                    }
                //                                })
                //                                .subscribe(new Action1<Boolean>() {
                //                                    @Override
                //                                    public void call(Boolean flag) {
                //                                        articleListBean.setLooked(false);
                //                                    }
                //                                });
                //
                //                        return Observable.just(articleListBean).toList();
                //                    }
                //                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ArticleListBean>>() {
                    @Override
                    public void call(List<ArticleListBean> articleList) {
                        mView.onLoadSuccesed();
                        //LogUtils.d("NewsItemPresenter flatEnd()" + articleList.toString());
                        mView.refreshRecyclerView(articleList);
                        if (params.startIndex == 0) {
                            saveData(articleList, params.id);
                        }
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onLoadError("加载失败。。", throwable);
                    }
                });                // TODO  如何对没有网络请求，但有本地数据时的错误进行处理

    }

    /**
     * 存储数据
     *
     * @param info
     * @param key  存到本地时使用的Key
     */
    private void saveData(final List<ArticleListBean> info, final String key) {
        new Thread() {
            @Override
            public void run() {
                String dataJson = new Gson().toJson(info);
                SPUtil.putString(key, dataJson);
                super.run();
            }
        }.start();
    }

}
