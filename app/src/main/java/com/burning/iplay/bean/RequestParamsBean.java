package com.burning.iplay.bean;

import com.burning.iplay.config.Constants;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-15  16:05
 * Email f842728368@163.com
 * @desc
 */

public class RequestParamsBean {

    /**
     *
     */
    public String id;

    /**
     * 网络请求时的开始索引
     */
    public int startIndex;

    /**
     * 一次网络请求的数据的数量
     */
    public int dataCount = Constants.REQUEST_COUNTS;

    /**
     * 资讯--关注要发送多个topicid
     */
    public List<String> ids;


    /**
     * 搜索界面需要的关键字
     */
    public String keyWord;


    @Override
    public String toString() {
        return "RequestParamsBean{" +
                "id='" + id + '\'' +
                ", startIndex=" + startIndex +
                ", dataCount=" + dataCount +
                ", ids=" + ids +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
