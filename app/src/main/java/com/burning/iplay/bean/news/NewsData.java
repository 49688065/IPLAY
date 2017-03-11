package com.burning.iplay.bean.news;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-13  11:32
 * Email f842728368@163.com
 * @desc 资讯界面除关注外的其它Fragment做网络请求时获得的数据格式
 */
public class NewsData {

    private int code;
    private boolean writeNull;
    private List<ArticleListBean> info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isWriteNull() {
        return writeNull;
    }

    public void setWriteNull(boolean writeNull) {
        this.writeNull = writeNull;
    }

    public List<ArticleListBean> getInfo() {
        return info;
    }

    public void setInfo(List<ArticleListBean> info) {
        this.info = info;
    }


}
