package com.burning.iplay.ui.comment;

import android.support.annotation.NonNull;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.base.BaseFragmentPresenter;
import com.burning.iplay.bean.RequestParamsBean;
import com.burning.iplay.bean.comment.Comment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author Kiven
 * @time 2017-3-7  9:40
 * Email f842728368@163.com
 * @desc 评论的P层
 */
public class CommentPresenter extends BaseFragmentPresenter<CommentContract.View>
        implements CommentContract.Presenter<CommentContract.View> {


    private Subscription mNewSubscription;
    private int mHotCommentCount;
    //private Observable<List<List<Comment>>> mHotComment;
    private RequestParamsBean mParams;
    private List<List<Comment>> mCommentDatas;


    @Override
    public void attachView(CommentContract.View view) {
        super.attachView(view);
    }

    /**
     * 将返回的Json数据转成List
     */
    private Func1<ResponseBody, List<List<Comment>>> json2ListData = new Func1<ResponseBody, List<List<Comment>>>() {
        @Override
        public List<List<Comment>> call(ResponseBody response) {
            ArrayList<List<Comment>> result = parseRequestData(response);
            return result;
        }
    };

    @Override
    public void requestData(RequestParamsBean params) {
        mParams = params;

        /*mHotComment = ApiManager.getInstance().getNewsApi()
                .getHotComment(params.id, params.startIndex, params.dataCount)
                .map(json2ListData)
                .doOnNext(new Action1<List<List<Comment>>>() {
                    @Override
                    public void call(List<List<Comment>> lists) {
                        LogUtils.i("CommentPresenter  热门" + lists.toString());
                        LogUtils.i("CommentPresenter 热门" + lists.size());
                        mHotCommentCount = lists.size();
                    }
                });*/
/*mSubscription = ApiManager.getInstance().getNewsApi()
                .getHotComment(params.id, params.startIndex, params.dataCount)
                .compose(this.<ResponseBody>applySchedulers())
                .map(json2ListData)
                .subscribe(new Action1<List<List<Comment>>>() {
                    @Override
                    public void call(List<List<Comment>> lists) {

                        mView.onLoadSuccesed();
                        mView.showHotComments(lists);
                    }
                }, mOnError);*/
    }


    @Override
    public void requestNewData(final RequestParamsBean newParams) {


        /*Observable<List<List<Comment>>> newComment = ApiManager.getInstance().getNewsApi()
                .getNewComment(newParams.id, newParams.startIndex, newParams.dataCount)
                .map(json2ListData)
                .doOnNext(new Action1<List<List<Comment>>>() {
                    @Override
                    public void call(List<List<Comment>> lists) {
                        LogUtils.i("CommentPresenter  最新" + lists.toString());
                        LogUtils.i("CommentPresenter 最新" + lists.size());
                    }
                });

        Observable.concat(mHotComment, newComment)
                .first()
                .compose(this.<List<List<Comment>>>applySchedulers())
                .subscribe(new Action1<List<List<Comment>>>() {
                    @Override
                    public void call(List<List<Comment>> lists) {
                        LogUtils.i("CommentPresenter  热门 + 最新" + lists.toString());
                        mView.onLoadSuccesed();
                        mView.showComments(lists, mHotCommentCount);
                    }
                }, mOnError);*/


        ApiManager.getInstance().getNewsApi()
                .getNewComment(newParams.id, newParams.startIndex, newParams.dataCount)

                // 在获取新评论之前之前先执行获取热门消息,doOnSubscribe()
                .doOnSubscribe(new Action0() {
                    //默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；
                    // 而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
                    @Override
                    public void call() {
                        // 如果有数据就不请求热门新闻了
                        if (mCommentDatas == null || mCommentDatas.size() == 0) {
                            ApiManager.getInstance().getNewsApi()
                                    .getHotComment(mParams.id, mParams.startIndex, mParams.dataCount)
                                    .map(json2ListData)
                                    .subscribe(new Action1<List<List<Comment>>>() {
                                        @Override
                                        public void call(List<List<Comment>> lists) {

                                            mHotCommentCount = lists.size();
                                            mCommentDatas = lists;
                                            //LogUtils.i("CommentPresenter mCommentDatas" + mCommentDatas.toString());
                                            //mView.onLoadSuccesed();
                                            //mView.showHotComments(lists);
                                        }
                                    }, mOnError);
                        }
                    }
                })
                .compose(this.<ResponseBody>applySchedulers())
                .map(json2ListData)
                .subscribe(new Action1<List<List<Comment>>>() {
                    @Override
                    public void call(List<List<Comment>> lists) {
                        //LogUtils.i("CommentPresenter lists" + lists.toString());
                        mCommentDatas.addAll(lists);
                        //LogUtils.i("CommentPresenter mCommentDatas" + mCommentDatas.toString());
                        mView.onLoadSuccesed();
                        if (newParams.startIndex == 0) {
                            mView.showComments(mCommentDatas, mHotCommentCount);
                        }else{
                            mView.addNewComments(lists);
                        }
                    }
                }, mOnError);





       /* mNewSubscription = ApiManager.getInstance().getNewsApi()
                .getNewComment(newParams.id, newParams.startIndex, newParams.dataCount)
                .compose(this.<ResponseBody>applySchedulers())
                .map(json2ListData)
                .subscribe(new Action1<List<List<Comment>>>() {
                    @Override
                    public void call(List<List<Comment>> lists) {
                        mView.onLoadSuccesed();
                        mView.showNewComments(lists);
                    }
                }, mOnError);*/
    }

    /**
     * 使用原生Json解析数据
     */
    @NonNull
    private ArrayList<List<Comment>> parseRequestData(ResponseBody response) {
        ArrayList<List<Comment>> result = new ArrayList<>();
        try {
            String resultData = response.string();
            // 使用原生的Json解析数据
            JSONObject data = new JSONObject(resultData);

            JSONObject info = data.optJSONObject("info");
            JSONArray commentIds = info.optJSONArray("commentIds");
            JSONObject comments = info.optJSONObject("comments");

            // 获得所有的评论ID数据，外层的List
            for (int i = 0; i < commentIds.length(); i++) {

                String id = commentIds.optString(i);
                String[] ids = id.split(",");

                List<String> idList = Arrays.asList(ids);
                // 对每个ids集合遍历，把comment放进去
                List<Comment> comm = new ArrayList<Comment>();
                for (String s : idList) {
                    String commentStr = comments.optString(s);
                    Comment commentItem = new Gson().fromJson(commentStr, Comment.class);
                    comm.add(commentItem);
                }
                result.add(comm);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void detachView() {
        super.detachView();
        if (mNewSubscription != null) {
            mNewSubscription.unsubscribe();
            mNewSubscription = null;
        }

    }
}
