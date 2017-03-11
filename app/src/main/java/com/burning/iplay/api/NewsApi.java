package com.burning.iplay.api;

import com.burning.iplay.bean.jingxuan.FocuseData;
import com.burning.iplay.bean.forum.ForumHotBean;
import com.burning.iplay.bean.news.HotSearchData;
import com.burning.iplay.bean.news.NewsData;
import com.burning.iplay.bean.jingxuan.RecommendBanner;
import com.burning.iplay.bean.SplashData;
import com.burning.iplay.bean.jingxuan.Zhuanlan;
import com.burning.iplay.bean.detail.IplayCommunity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Kiven
 * @time 2017-2-13  0:25
 * Email f842728368@163.com
 * @desc 资讯Api
 */
public interface NewsApi {

    /**
     * https://i.play.163.com/news/article/list/T1461396257593/0/20?todayFirstRequest=false
     * 资讯中头条，网游，手游，主机，电竞，还有关注中的某个游戏专区的接口
     *
     * @param topicId
     * @param startId
     * @param itemCounts
     * @param todayFirstRequest
     * @return
     */
    @GET("news/article/list/{topicId}/{startId}/{itemCounts}")
    Observable<NewsData> getNews(
            @Path("topicId") String topicId,
            @Path("startId") int startId,
            @Path("itemCounts") int itemCounts,
            @Query("todayFirstRequest") boolean todayFirstRequest);


    /**
     * 资讯中关注界面的接口
     * https://i.play.163.com/news/article/attention_article_list/0/20?topicIds=T1461396524087,IT1478747682065
     *
     * @param startId
     * @param itemCounts
     * @param topicIds
     * @return
     */
    @GET("news/article/attention_article_list/{startId}/{itemCounts}")
    Observable<FocuseData> getFocuseData(
            @Path("startId") int startId,
            @Path("itemCounts") int itemCounts,
            @Query("topicIds") List<String> topicIds);

    /**
     * 精选--推荐中新闻列表的接口
     * https://i.play.163.com/news/config/config_recommend_article/list
     */
    @GET("news/config/config_recommend_article/list")
    Observable<NewsData> getRecommendData();


    /**
     * 精选--推荐中新闻列表的接口
     * https://i.play.163.com/news/config/config_focus_img/list/
     */
    @GET("news/config/config_focus_img/list/")
    Observable<RecommendBanner> getRecommendBannerData();

    /**
     * 精选--专栏的数据
     * https://i.play.163.com/news/new_topic/list?topicType=2
     */
    @GET("news/new_topic/list?topicType=2")
    Observable<Zhuanlan> getZhuanlanData();


    /**
     * 搜索，热搜词语
     * https://i.play.163.com/news/config/config_search/list/
     */
    @GET("news/config/config_search/list/")
    Observable<HotSearchData> getHotSearchData();


    /**
     * 社区 热门推荐数据
     * https://i.play.163.com/news/discuz/hot_recommend/1/20
     * 从1开始
     */
    @GET("news/discuz/hot_recommend/{startId}/{itemCounts}")
    Observable<ForumHotBean> getForumHotData(
            @Path("startId") int startId,
            @Path("itemCounts") int itemCounts
    );

    /**
     * 社区--爱玩社区的数据
     * https://i.play.163.com/news/discuz/discuz_model_v2/list/center/0
     */
    @GET("news/discuz/discuz_model_v2/list/center/0")
    Observable<IplayCommunity> getIplayCommunityData();
    

    /**
     * 评论-热门
     * https://i.play.163.com/news/v2/hottie/CES418S600314QVB/0/10/11/1/1?format=true
     */
    @GET("news/v2/hottie/{docId}/{startIndex}/{itemCounts}/11/1/1?format=true")
    Observable<ResponseBody> getHotComment(
            @Path("docId") String docId,
            @Path("startIndex") int startId,
            @Path("itemCounts") int itemCounts);


    /**
     * 评论-最新
     * https://i.play.163.com/news/v2/newtie/CES418S600314QVB/0/20/6/2/2?format=true
     */
    @GET("news/v2/newtie/{docId}/{startIndex}/{itemCounts}/6/2/2?format=true")
    Observable<ResponseBody> getNewComment(
            @Path("docId") String docId,
            @Path("startIndex") int startId,
            @Path("itemCounts") int itemCounts);

    /**
     * 社区--凯恩之角的数据
     * https://i.play.163.com/news/discuz/discuz_model_v2/list/center/1
     */
    @GET("news/discuz/discuz_model_v2/list/center/1")
    Observable<IplayCommunity> getKaienData();


    /**
     * app启动的图片的接口
     * https://i.play.163.com/news/initLogo/v3/android_normal
     * @return
     */
    @GET("/news/initLogo/v3/android_normal")
    Observable<SplashData> getSphlashData();

    /**
     * 三张图片的图片详情接口
     * https://i.play.163.com/news/photoset/getPhotoListBySetid/57908
     */
    @GET("news/photoset/getPhotoListBySetid/{setid}")
    Observable<ResponseBody> getImageDetail(
            @Path("setid") String setid);

}
