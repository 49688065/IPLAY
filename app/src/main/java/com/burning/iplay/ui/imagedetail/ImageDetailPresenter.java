package com.burning.iplay.ui.imagedetail;

import android.util.Log;

import com.burning.iplay.api.ApiManager;
import com.burning.iplay.base.BaseFragmentPresenter;
import com.burning.iplay.bean.ImageDetail;
import com.burning.iplay.bean.RequestParamsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author lam
 * @time 2017-03-08  9:48
 * @desc ${TODD}
 */
public class ImageDetailPresenter extends BaseFragmentPresenter<IImageDetailContract.View>
        implements IImageDetailContract.Presenter<IImageDetailContract.View>{


    @Override
    public void requestData(RequestParamsBean params) {
        Log.i("tag", "ImageDetailPresenter requestData()");
        mSubscription = ApiManager.getInstance().getNewsApi()
                .getImageDetail(params.id)
                .compose(this.<ResponseBody>applySchedulers())
                .map(new Func1<ResponseBody, List<ImageDetail>>() {
                    //func1里面作用是把请求返回的json转换成List<ImageDetail>
                    @Override
                    public List<ImageDetail> call(ResponseBody responseBody) {
                        try {
                           return json2ImageList(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return new ArrayList<ImageDetail>();//这个是空的,用来不报空指针异常
                    }
                }).subscribe(new Action1<List<ImageDetail>>() {
                    @Override
                    public void call(List<ImageDetail> imageDetails) {
                        Log.i("tag", "ImageDetailPresenter call()imageDetails+"+imageDetails);
                        mView.onLoadSuccesed();
                        mView.showImageAndInfo(imageDetails);
                    }
                },mOnError);
    }

    /**
     * 把请求返回的json字符串转换成页面的图片数据集合的方法
     * @param string
     * @return
     */
    private List<ImageDetail> json2ImageList(String string) {
        Gson gson=new Gson();
        return gson.fromJson(string, new TypeToken<List<ImageDetail>>(){}.getType());
    }
    /**
     *  .compose(this.<ResponseBody>applySchedulers())
     .map(json2ListData)
     .subscribe(new Action1<List<List<Comment>>>() {
    @Override
    public void call(List<List<Comment>> lists) {

    mView.onLoadSuccesed();
    mView.showHotComments(lists);
    }
    }, mOnError);
     */
}
