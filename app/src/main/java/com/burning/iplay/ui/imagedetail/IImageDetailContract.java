package com.burning.iplay.ui.imagedetail;

import com.burning.iplay.base.BaseFragmentContract;
import com.burning.iplay.bean.ImageDetail;

import java.util.List;

/**
 * @author lam
 * @time 2017-03-08  0:06
 * @desc 图片详情界面的P层与V层的合约
 */
public interface IImageDetailContract {

    interface View extends BaseFragmentContract.View{

        void showImageAndInfo(List<ImageDetail> imageDetails);
    }

    interface Presenter<T extends View> extends BaseFragmentContract.Presenter<T>{

    }
}
