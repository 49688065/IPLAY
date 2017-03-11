package com.burning.iplay.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Kiven
 * @time 2017-2-13  1:01
 * Email f842728368@163.com
 * @desc 对Retrofit网络请求的总的管理
 */
public class ApiManager {

    // private static NewsApi sMessageApi;
    // private static DetailApi sDetailApi;
    // private static OkHttpClient okHttpClient = new OkHttpClient();
    // private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    // //private static Converter.Factory fastJsonConverterFactory = FastJsonConverterFactory.create();
    // private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


    private static ApiManager instance;
    private NewsApi mNewsApi;
    private DetailApi mDetailApi;
    private NewsSearchApi mNewsSearchApi;
    private final Retrofit mRetrofit;
    private final OkHttpClient mOkHttpClient;

    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }


    private ApiManager() {

        /**
         * 设置请求超时时间为5秒
         */
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS).build();

        //.addConverterFactory(fastJsonConverterFactory)
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl("https://i.play.163.com/")
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(fastJsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 获得资讯相关的API
     */
    public NewsApi getNewsApi() {
        if (mNewsApi == null) {
            mNewsApi = mRetrofit.create(NewsApi.class);
        }

        return mNewsApi;
    }

    /**
     * 获得详情相关的Api
     */
    public DetailApi getDetailApi() {
        if (mDetailApi == null) {
            mDetailApi = mRetrofit.create(DetailApi.class);
        }
        return mDetailApi;
    }

    /**
     * 获得新闻的搜索结果，这个搜索的结果的BaseUrl跟其他的不一样
     */
    public NewsSearchApi getNewsSearchApi() {
        if (mNewsSearchApi == null) {
            mNewsSearchApi = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl("http://play.163.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addConverterFactory(fastJsonConverterFactory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(NewsSearchApi.class);
        }
        return mNewsSearchApi;
    }

    /**
     * 获得唯一的OkHttp对象
     */
    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

}
