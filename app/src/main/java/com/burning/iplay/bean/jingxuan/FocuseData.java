package com.burning.iplay.bean.jingxuan;

import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.news.TopTopicListBean;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-13  0:32
 * Email f842728368@163.com
 * @desc 资讯界面的网络请求返回的Bean
 */
public class FocuseData {

    private int code;
    private InfoBean info;
    private boolean writeNull;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public boolean isWriteNull() {
        return writeNull;
    }

    public void setWriteNull(boolean writeNull) {
        this.writeNull = writeNull;
    }

    public static class InfoBean {

        private boolean systemRecommendTopic;
        private List<ArticleListBean> articleList;
        private List<TopTopicListBean> topTopicList;

        public boolean isSystemRecommendTopic() {
            return systemRecommendTopic;
        }

        public void setSystemRecommendTopic(boolean systemRecommendTopic) {
            this.systemRecommendTopic = systemRecommendTopic;
        }

        public List<ArticleListBean> getArticleList() {
            return articleList;
        }

        public void setArticleList(List<ArticleListBean> articleList) {
            this.articleList = articleList;
        }

        public List<TopTopicListBean> getTopTopicList() {
            return topTopicList;
        }

        public void setTopTopicList(List<TopTopicListBean> topTopicList) {
            this.topTopicList = topTopicList;
        }


    }
}
