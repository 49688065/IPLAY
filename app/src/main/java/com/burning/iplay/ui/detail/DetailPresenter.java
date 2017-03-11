package com.burning.iplay.ui.detail;

import android.webkit.JavascriptInterface;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.db.NewsLog;
import com.burning.iplay.bean.detail.Article;
import com.burning.iplay.bean.detail.DetailBean;
import com.burning.iplay.bean.detail.Image;
import com.burning.iplay.bean.detail.Video;
import com.burning.iplay.utils.LogUtils;
import com.burning.iplay.utils.TimeUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Kiven
 * @time 2017-2-15  11:53
 * Email f842728368@163.com
 * @desc 详情界面的P层实现类，并且M层也在此类中
 */
public class DetailPresenter implements IDetailContract.Presenter<DetailActivity> {


    // View
    private final IDetailContract.View mView;
    private Subscription mSubscription;
    private List<Image> mImageList;
    private List<Video> mVideoList;

    public DetailPresenter(IDetailContract.View view) {
        mView = view;
    }

    @Override
    public void subscribe(final RequestParamsBean requestBean) {

        mView.showLoading();
        //  对数据处理后对View操作
        mSubscription = ApiManager.getInstance().getDetailApi().getData(requestBean.id)
                .observeOn(Schedulers.io()) //指定doOnNext执行线程是新线程
                .doOnNext(new Action1<DetailBean>() {// 保存数据
                    @Override
                    public void call(DetailBean detailBean) {

                        // 保存数据到数据库
                        NewsLog newsLog = new NewsLog();
                        newsLog.setLooked(requestBean.id);
                        newsLog.save();
                        LogUtils.i("DetailPresenter call() ID： " + requestBean.id);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DetailBean>() {
                    @Override
                    public void call(DetailBean detailBean) {
                        //  对数据处理后对View操作
                        mView.showContent();
                        // String htmlStr = detailBean.getInfo().getArticle().getBody();
                        String htmlStr = getHtmlStr(detailBean.getInfo().getArticle());
                        mView.updateWebView(htmlStr);
                        mView.updateReplayCount(String.valueOf(detailBean.getInfo().getArticle().getReplyCount()));

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mView.showError();
                    }
                });
    }

    @Override
    public void attachView(DetailActivity view) {

    }

    @Override
    public void start() {

    }

    @Override
    public void detachView() {
        mSubscription.unsubscribe();
    }


    /**
     * 解析bean，放入字符串中，再调用loadDataWithBaseUrl方法。
     * 这里的主要难点是html的格式的设置，和JS与Java的交互
     */
    private String getHtmlStr(Article articleBean) {
        //对body做处理
        String body = articleBean.getBody();

        //处理标题
        String titleHTML = "<p style = \"margin:25px 0px 0px 0px\"><span style='font-size:30px;'><strong>" + articleBean.getTitle() + "</strong></span></p>";// 标题
        String timeStr = TimeUtils.getFriendlyTimeSpanByNow(articleBean.getPtime()); //将时间转换成 X秒/X小时前 的格式
        // 处理作者信息
        titleHTML = titleHTML + "<p><span style='color:#EA2000;font-size:14px;'>" + articleBean.getPenName() +
                "</span>";
        // 处理时间信息
        titleHTML = titleHTML + "<span style='color:#999999;font-size:14px;'>" + "&nbsp&nbsp" + timeStr + "</span></p>";//作者与时间提示
        titleHTML = titleHTML + "<div style=\"border-top:1px dotted #999999;margin:20px 0px\"></div>";//加条虚线

        //设置正文的字体
        body = "<div style='line-height:180%;'><span style='font-size:18px;'>" + body + "</span></div>";

        //处理图片
        mImageList = articleBean.getImg();
        if (mImageList != null && mImageList.size() != 0) {
            for (int i = 0; i < mImageList.size(); i++) {
                Image image = mImageList.get(i);
                //替换图片，并给img标签加上点击事件，这里点击事件调用的是JS里的函数
                body = body.replace(image.getRef(),
                        "<img src='" + image.getSrc() + "' onclick=\"showImg(" + i + ")\"/>"
                                + "<p><font style='font-style:italic;font-color:#999999;font-size:14px;'>" + image.getAlt() + "</font></p>");
            }
        }

        // TODO 处理视频  加上播放按钮
/*        mVideoList = articleBean.getVideo();
        if (mVideoList != null && mVideoList.size() != 0) {
            for (int i = 0; i < mVideoList.size(); i++) {
                Video video = mVideoList.get(i);
                body = body.replace(video.getRef(),
                        "<img src='" + video.getCover() + "' onclick=\"showVideo(" + i + ")\"/>"
                                + "<p><font style='font-style:italic;font-color:#999999;font-size:14px;'>" + video.getAlt() + "</font></p>");

            }
        }*/
        // TODO 处理视频  加上播放按钮
        mVideoList = articleBean.getVideo();
        if (mVideoList != null && mVideoList.size() != 0) {
            for (int i = 0; i < mVideoList.size(); i++) {
                Video video = mVideoList.get(i);
                LogUtils.i("DetailPresenter getHtmlStr()" + mVideoList);
                body = body.replace(video.getRef(),
                        "<div onclick=\"showVideo(" + i + ")\" style='text-align: center;position: relative;width: 100%;display: inline-block;' ><img src='" +
                                video.getCover() + "'><img src='file:///android_asset/play.png' style='width: 60px;height: 60px;position: absolute;left: 50%;top: 50%;margin-left: -30px;margin-top: -30px;'>" +
                                "</div>"
                                + "<p><font style='font-style:italic;font-color:#999999;font-size:14px;'>" + video.getAlt() + "</font></p>");

            }
        }
        //把标题给加上
        body = titleHTML + body;

        // 设置JS函数调用，点击调用show(index)， 然后show(index)调用Java代码
        //String script = "<script type='text/javascript'>function show(index){window.news.showImagesDetail(index);}</script>";
        String script = "<script>" +
                "function showImg(i){window.web.showImagesDetail(i);}" +
                "function showVideo(i){window.web.showVideo(i);}" +
                "</script>";
        //最后给图片标签统一设置样式，避免图片显示宽度过大
        body = "<html><head><style>img{width:100%}</style>" + script + "</head>" + body + "</html>";
        return body;
        // Log.e("xmg", "loadData: " + body);
        //mWvArticleDetail.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
        //mTvReplyCount.setText("" + detailBean.getReplyCount());
    }

    /**
     * 打开图片详情界面，要传递两个参数，一个是所有的图片，另一个是当前图片的索引
     */
    @JavascriptInterface
    public void showImagesDetail(int index) {
        //Log.i("TAG", "showImagesDetail  index   " + index);
        //Intent intent = new Intent(ArticleDetailActivity.this, ImagesDetailActivity.class);
        //intent.putExtra(IMAGES_LIST, mImgeBeans);
        //Log.i("TAG", "mImgeBeans: " + mImgeBeans);
        //intent.putExtra(IMAGE_INDEX, index);
        //startActivity(intent);
        mView.showImagedDetail(mImageList, index);

    }

    /**
     * 播放视频
     */
    @JavascriptInterface
    public void showVideo(int index) {

        mView.showVideo(mVideoList.get(index));

    }


}
