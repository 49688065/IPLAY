package com.burning.iplay.ui.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.burning.iplay.R;
import com.burning.iplay.adapter.BaseRecyclerViewAdapter;
import com.burning.iplay.adapter.HotSearchAdapter;
import com.burning.iplay.adapter.newsitem.NewsItemAdapter;
import com.burning.iplay.bean.news.ArticleListBean;
import com.burning.iplay.bean.news.HotSearchData;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.ui.refresh.RefreshFragment;
import com.burning.iplay.utils.ActivityUtil;
import com.burning.iplay.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Kiven
 * @time 2017-3-6  16:08
 * Email f842728368@163.com
 * @desc 搜索Fragment
 */
public class SearchFragment
        extends RefreshFragment<SearchPresenter, NewsItemAdapter, ArticleListBean>
        implements SearchContract.View {


    @BindView(R.id.container_hot_search)
    LinearLayout mContainerHotSearch;
    @BindView(R.id.hot_search_gl)
    RecyclerView mHotSearchRl;
    @BindView(R.id.search_et)
    EditText mSearchEt;
    @BindView(R.id.search_btn_tv)
    TextView mSearchBtnTv;
    @BindView(R.id.close_btn_iv)
    ImageView mCloseBtnIv;
    @BindView(R.id.search_back_btn)
    ImageButton mBackBtnIv;

    private HotSearchAdapter mHotSearchAdapter;
    private RequestParamsBean mSearchRequestParams;

    @Override
    protected NewsItemAdapter getAdapter() {
        return new NewsItemAdapter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_search;
    }

    @Override
    protected void initParams() {
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initUI() {
        super.initUI();
        initHotSearch();
    }

    private void initHotSearch() {
        mHotSearchRl.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mHotSearchAdapter = new HotSearchAdapter();
        mHotSearchRl.setAdapter(mHotSearchAdapter);
    }

    /**
     * 完全覆写下拉刷新，上拉加载的方法
     */
    @Override
    protected void initListener() {


        // 监听输入内容
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 控制右侧的删除按钮的显示，并改变删除按钮文本的颜色，设置是否可点击，改变
                if (s.toString().length() > 0) {
                    mSearchBtnTv.setTextColor(getResources().getColor(R.color.colorRed));
                    mCloseBtnIv.setVisibility(View.VISIBLE);
                } else {
                    mSearchBtnTv.setTextColor(getResources().getColor(R.color.colorGrey));
                    mCloseBtnIv.setVisibility(View.GONE);
                }
            }
        });

        // 点击热搜词语的监听
        mHotSearchAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int index) {

                String keyword = mHotSearchAdapter.getData(index).getKeyword();
                mSearchEt.setText(keyword);
                mSearchEt.setSelection(keyword.length());
                requestSearchData(keyword);
            }
        });


        // 这个是下拉刷新的监听事件
        mSwipeLayout.setEnabled(false);


        // 上拉加载更多的实现
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1)) {// 表示不能滚动，也就是到了最底部
                    //LogUtils.i("SearchFragment onScrolled()");
                    mSearchRequestParams.startIndex += mSearchRequestParams.dataCount;
                    mPresenter.requestSearchData(mSearchRequestParams);
                }
            }
        });

        // item的点击事件
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int index) {
                String docid = mAdapter.getData(index).getDocid();
                // 跳转到详情界面
                ActivityUtil.startDetailActivity(getActivity(), docid);
            }
        });
    }


    @OnClick({R.id.search_back_btn, R.id.close_btn_iv, R.id.search_btn_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_back_btn:// 按返回按钮退出搜索界面
                getActivity().onBackPressed();
                break;
            case R.id.close_btn_iv: // 删除输入框里面的内容
                mSearchEt.getText().clear();
                break;
            case R.id.search_btn_tv: // 发送搜索请求
                checkKeyWord();
                break;
        }
    }


    /**
     * 通过Presenter发送搜索的网络请求，每点击一次就会发送一次网络请求
     */
    private void checkKeyWord() {
        String keyword = mSearchEt.getText().toString().trim();
        if (TextUtils.isEmpty(keyword)) {
            ToastUtil.showToast(mSearchBtnTv, getResources().getString(R.string.search_warn));
            return;
        }

        requestSearchData(keyword);
    }

    /**
     * 发送搜索的网络请求
     *
     * @param keyword 搜索的关键字
     */
    private void requestSearchData(String keyword) {
        mSearchRequestParams = new RequestParamsBean();
        mSearchRequestParams.keyWord = keyword;
        mPresenter.requestSearchData(mSearchRequestParams);
    }


    @Override
    public void showHotSearch(List<HotSearchData.InfoBean> hotDatas) {
        //LogUtils.i("SearchFragment showHotSearch()" + hotDatas);
        mContainerHotSearch.setVisibility(View.VISIBLE);
        mHotSearchAdapter.setData(hotDatas);
    }

    @Override
    public void updateSearchUI(List<ArticleListBean> resultDatas) {
        // 第一步，就是让热搜词语Gone掉
        mContainerHotSearch.setVisibility(View.GONE);
        //LogUtils.i("SearchFragment updateSearchUI()");
        if (mSearchRequestParams.startIndex == 0) {
            refreshRecyclerView(resultDatas);
        } else {
            addRecyclerViewData(resultDatas);
        }
    }


    @Override
    public void onLoading() {
        super.onLoading();
        // 再让参数的startindex置为零
    }
}
