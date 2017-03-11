package com.burning.iplay.ui.comment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.burning.iplay.R;
import com.burning.iplay.adapter.CommentAdapter;
import com.burning.iplay.base.BaseFragment;
import com.burning.iplay.bean.comment.Comment;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.config.Constants;
import com.burning.iplay.utils.LogUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author Kiven
 * @time 2017-3-7  9:38
 * Email f842728368@163.com
 * @desc 评论的Fragment，负责显示界面
 */
public class CommentFragment extends BaseFragment<CommentContract.Presenter>
        implements CommentContract.View {

    @BindView(R.id.comment_back_btn)
    ImageButton mCommentBackBtn;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    String mDocId;
    private RequestParamsBean mNewCommentParams;
    private CommentAdapter mAdapter;

    public void setDocId(String docId) {
        mDocId = docId;
    }

    @Override
    protected CommentContract.Presenter createPresenter() {
        return new CommentPresenter();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.frag_comment;
    }

    @Override
    protected void initParams() {
        mParams.startIndex = 0;
        mParams.id = mDocId;
        mParams.dataCount = Constants.HOT_COMMENT_COUNT;
    }

    @Override
    protected void initialize() {

        mPresenter.start();

        initNewCommentParams();
        initUI();
        initListener();

    }


    @Override
    protected void requestPermissionSucceed() {
        // 请求热门评论
        mPresenter.requestData(mParams);
        // 请求最新评论的数据
        mPresenter.requestNewData(mNewCommentParams);
    }


    private void initListener() {
        // 左上角的返回按钮的监听
        mCommentBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1)) {// 表示不能滚动，也就是到了最底部
                    LogUtils.i("RefreshFragment onScrolled() loadMore");
                    mNewCommentParams.startIndex += mNewCommentParams.dataCount;
                    mPresenter.requestNewData(mNewCommentParams);
                }
            }
        });


    }

    private void initUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommentAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化最新评论的网络请求的参数
     */
    private void initNewCommentParams() {
        mNewCommentParams = new RequestParamsBean();
        mNewCommentParams.id = mDocId;
    }




    @Override
    public void showComments(List<List<Comment>> lists, int hotCommentCount) {
        mAdapter.setData(lists,hotCommentCount);
    }

    @Override
    public void addNewComments(List<List<Comment>> lists) {
        mAdapter.addDatas(lists);
    }
}
