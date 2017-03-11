package com.burning.iplay.ui.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.detail.Image;
import com.burning.iplay.bean.detail.Video;
import com.burning.iplay.ui.comment.CommentActivity;
import com.burning.iplay.ui.video.VideoActivity;
import com.burning.iplay.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DetailActivity extends AppCompatActivity implements IDetailContract.View {

    public static final String DOCID = "docId";

    @BindView(R.id.detail_back_btn)
    ImageButton mDetailBackBtn;
    @BindView(R.id.loading_iv)
    ImageView mLoadingIv;
    @BindView(R.id.detail_webView)
    WebView mDetailWebView;
    @BindView(R.id.detail_comment_rl)
    RelativeLayout mDetailCommentRl;
    @BindView(R.id.detail_replay_rl)
    RelativeLayout mDetailReplayRl;
    @BindView(R.id.detail_save_btn)
    ImageButton mDetailSaveBtn;
    @BindView(R.id.detail_share_btn)
    ImageButton mDetailShareBtn;
    @BindView(R.id.loading_rl)
    RelativeLayout mLoadingRl;
    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;
    @BindView(R.id.replay_count_tv)
    TextView mReplayCountTv;

    private View mView;
    private Unbinder mUnBinder;
    protected IDetailContract.Presenter mPresenter;
    private AnimationDrawable mLoadAnimation;
    private String mDocId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化布局文件
        initLayoutUI();
        RequestParamsBean requestBean = new RequestParamsBean();
        mDocId = getIntent().getStringExtra(DOCID);
        requestBean.id = mDocId;
        // 初始化Presenter
        initPresenter();

        // 发送网络请求
        mPresenter.subscribe(requestBean);

        // 初始化WebView的属性
        initWebView();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initLayoutUI() {
        // 设置布局
        mView = getLayoutInflater().inflate(getLayoutResId(), null);
        setContentView(mView);

        // 绑定控件
        mUnBinder = ButterKnife.bind(this);

    }


    /**
     * 子类必须实现该方法并返回当前Activity的布局ID
     *
     * @return 布局资源ID
     */
    protected int getLayoutResId() {
        return R.layout.activity_detail;
    }

    public void initPresenter() {
        if (mPresenter == null) {
            mPresenter = new DetailPresenter(this);
        }
    }


    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        mDetailWebView.getSettings().setJavaScriptEnabled(true);


        mDetailWebView.addJavascriptInterface(mPresenter, "web");

        mDetailWebView.setWebViewClient(new WebViewClient() {
            //返回值为true	表示网页点击新的链接操作让我们自己处理
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    /**
     * 加载数据时调用的方法
     */
    @Override
    public void showLoading() {
        mLoadingRl.setVisibility(View.VISIBLE);
        mLayoutContent.setVisibility(View.GONE);
    }

    /**
     * 数据错误时调用的方法
     */
    @Override
    public void showError() {

    }


    /**
     * 有数据时调用的方法
     */
    @Override
    public void showContent() {
        if (mLoadAnimation != null) {
            mLoadAnimation.stop();
        }
        mLoadingRl.setVisibility(View.GONE);
        mLayoutContent.setVisibility(View.VISIBLE);

    }

    /**
     * 更新WebView的界面
     *
     * @param htmlStr WebView应该加载的String
     */
    @Override
    public void updateWebView(String htmlStr) {
        mDetailWebView.loadDataWithBaseURL(null, htmlStr, "text/html", "utf-8", null);
    }

    @Override
    public void updateReplayCount(String replayCount) {
        mReplayCountTv.setText(replayCount);
    }

    @Override
    public void showImagedDetail(List<Image> imageList, int index) {
        // TODO 展示图片详情
        ToastUtil.showToast(mView, "你点击了第" + index + "张图片");
    }

    @Override
    public void showVideo(Video video) {
        Intent intent = new Intent(DetailActivity.this, VideoActivity.class);
        intent.putExtra(VideoActivity.VIDEO, video);
        startActivity(intent);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mLoadAnimation = (AnimationDrawable) mLoadingIv.getDrawable();
        mLoadAnimation.start();
    }

    /**
     * 点击左上角的返回按钮应该执行的操作
     */
    @OnClick(R.id.detail_back_btn)
    public void backPress() {
        onBackPressed();
    }

    /**
     * 开启评论界面
     */
    @OnClick(R.id.detail_replay_rl)
    public void startCommentActivity() {
        Intent intent = new Intent(DetailActivity.this, CommentActivity.class);
        intent.putExtra(CommentActivity.COMMENT_DOC_ID, mDocId);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        // TODO 输入框输入评论时，先关闭评论输入框
        //if (mEtHasFocus) {
        //    // 如果有焦点，取消焦点，并关闭软键盘
        //    mWvArticleDetail.requestFocus();
        //    InputUtil.closeSoftInput(ArticleDetailActivity.this);
        //    return;
        //}
        super.onBackPressed();
    }


}
