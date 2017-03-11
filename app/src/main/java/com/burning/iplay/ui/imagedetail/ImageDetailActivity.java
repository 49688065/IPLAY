package com.burning.iplay.ui.imagedetail;

import android.os.Build;
import android.util.Log;

import com.burning.iplay.R;
import com.burning.iplay.base.BaseActivity;


public class ImageDetailActivity extends BaseActivity {

    public static final String PHOTO_SET_ID = "setid";//加载Activity传进来的图片集合id
    private ImageDetailFragment mImageDetailFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected void initUI() {
        Log.i("tag", "ImageDetailActivity initUI()");
        if (Build.VERSION.SDK_INT > 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
        String setid = getIntent().getStringExtra(PHOTO_SET_ID);//传进来的图片集合id
        mImageDetailFragment = new ImageDetailFragment();
         mImageDetailFragment.setPhotoId(setid);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_image_detail,
                mImageDetailFragment).commit();

    }

    @Override
    protected void requestPermissionSucceed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mImageDetailFragment!=null) {
            mImageDetailFragment=null;
        }
    }
}
