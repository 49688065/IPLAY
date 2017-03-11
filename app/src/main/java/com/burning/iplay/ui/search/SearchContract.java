package com.burning.iplay.ui.search;

import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.news.HotSearchData;
import com.burning.iplay.bean.RequestParamsBean;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-3-6  16:14
 * Email f842728368@163.com
 * @desc 搜索界面的契约接口
 */
public interface SearchContract {

    interface View {
        /**
         * 更新界面，搜索之后的数据
         */
        void updateSearchUI(List<ArticleListBean> resultDatas);

        /**
         * 得到热搜词语之后，更新界面
         */
        void showHotSearch(List<HotSearchData.InfoBean> hotDatas);
    }

    interface Presetter {

        /**
         * 发起搜索请求
         */
        void requestSearchData(RequestParamsBean params);
    }
}
