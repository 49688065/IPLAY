package com.burning.iplay.ui.detail;

import com.burning.iplay.base.BasePresenter;
import com.burning.iplay.base.BaseView;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.detail.Image;
import com.burning.iplay.bean.detail.Video;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-15  11:20
 * Email f842728368@163.com
 * @desc 详情界面的P层与V层的合约
 */
public interface IDetailContract {

    interface View extends BaseView {

        @Override
        void initPresenter();

        /**
         * 正在加载的时调用
         */
        void showLoading();

        /**
         * 数据错误时调用
         */
        void showError();

        /**
         * 数据正确时调用，去掉Loading界面
         */
        void showContent();

        /**
         * 让WebView显示正确的数据
         *
         * @param htmlStr WebView应该显示的数据
         */
        void updateWebView(String htmlStr);

        /**
         * 下方评论数显示正确数据
         *
         * @param replayCount 评论数量
         */
        void updateReplayCount(String replayCount);

        /**
         * 显示图片详情界面
         *
         * @param imageList 所有的图片集合
         * @param index     图片的当前索引
         */
        void showImagedDetail(List<Image> imageList, int index);

        /**
         * 播放视频
         *
         * @param video 视频的详细信息
         */
        void showVideo(Video video);
    }

    interface Presenter<T extends View> extends BasePresenter<T> {

        /**
         * 订阅，即发送网络请求
         *
         * @param requestBean
         */
        void subscribe(RequestParamsBean requestBean);

        @Override
        void attachView(T view);

        @Override
        void start();

        @Override
        void detachView();
    }
}
