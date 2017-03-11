package com.burning.iplay.ui.comment;

import com.burning.iplay.base.BaseFragmentContract;
import com.burning.iplay.bean.comment.Comment;
import com.burning.iplay.bean.RequestParamsBean;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-3-7  9:38
 * Email f842728368@163.com
 * @desc 评论的契约接口
 */
public interface CommentContract {

    interface View extends BaseFragmentContract.View {

        /**
         * 第一次请求的时候获得热门+最新评论数据，并得到热门数据的size
         *
         * @param lists
         * @param hotCommentCount
         */
        void showComments(List<List<Comment>> lists, int hotCommentCount);

        /**
         * 加载更多时，向最新评论添加数据
         *
         * @param lists
         */
        void addNewComments(List<List<Comment>> lists);
    }

    interface Presenter<T extends View> extends BaseFragmentContract.Presenter<T> {
        /**
         * 这个是对热门评论的网络请求
         */
        @Override
        void requestData(RequestParamsBean params);

        /**
         * 这个是对最新评论的网络请求
         */
        void requestNewData(RequestParamsBean newCommentParams);
    }
}
