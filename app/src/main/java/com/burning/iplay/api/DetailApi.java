package com.burning.iplay.api;


import com.burning.iplay.bean.detail.DetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Kiven
 * @time 2017-2-15  14:53
 * Email f842728368@163.com
 * @desc 详情界面用到的API
 */
public interface DetailApi {


    /**
     * 资讯精选界面Fragment的item用到的API
     * <p>
     * https://i.play.163.com/news/appDetail/CEJITSOP00314J6L
     */
    @GET("news/appDetail/{docId}")
    Observable<DetailBean> getData(
            @Path("docId") String topicID);
}
