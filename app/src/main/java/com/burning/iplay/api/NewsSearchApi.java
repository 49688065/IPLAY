package com.burning.iplay.api;

import com.burning.iplay.bean.news.NewsData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Kiven
 * @time 2017-3-6  20:30
 * Email f842728368@163.com
 * @desc 新闻的搜索界面的数据接口
 */
public interface NewsSearchApi {

    /**
     * 搜索API
     * http://play.163.com/search/api3/123/0/20
     */
    @GET("search/api3/{keyword}/{startIndex}/{itemCounts}")
    Observable<NewsData> getSearchResult(
            @Path("keyword") String keyword,
            @Path("startIndex") int startIndex,
            @Path("itemCounts") int itemCounts);
}
