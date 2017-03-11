package com.burning.iplay.bean.db;

import org.litepal.crud.DataSupport;

/**
 * @author Kiven
 * @time 2017-3-7  22:44
 * Email f842728368@163.com
 * @desc 新闻浏览的记录
 */
public class NewsLog extends DataSupport {


    private String looked;

    public String getLooked() {
        return looked;
    }

    public void setLooked(String looked) {
        this.looked = looked;
    }
}
