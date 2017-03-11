package com.burning.iplay.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.burning.iplay.bean.ImageDetail;
import com.burning.iplay.ui.imagedetail.IImageOnClickListener;
import com.burning.iplay.utils.ImageLoader;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author lam
 * @time 2017-03-08  15:01
 * @desc ${TODD}
 */
public class ImageDetailAdapter extends PagerAdapter {
    protected List<ImageDetail> mImageDetails;
    private IImageOnClickListener mListener;

    public void setImageOnClickListener(IImageOnClickListener listener ){
        mListener = listener;
    }
    @Override
    public int getCount() {
        return mImageDetails==null?0:mImageDetails.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    public void setData(List<ImageDetail> imageDetails) {
        mImageDetails=imageDetails;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        ImageDetail imageDetail = mImageDetails.get(position);
        ImageLoader.loadImage(container.getContext(),imageDetail.img,photoView);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if(mListener!=null) {
                    mListener.onClick();
                }
            }
        });
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
