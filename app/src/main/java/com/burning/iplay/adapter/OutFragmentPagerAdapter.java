package com.burning.iplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.burning.iplay.base.BaseFragment;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-16  21:09
 * Email f842728368@163.com
 * @desc 外部Fragment的adapter
 */
public class OutFragmentPagerAdapter extends FragmentPagerAdapter {


    private List<BaseFragment> mFragments;
    private List<String> mTitles;

    public OutFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public void setData(List<BaseFragment> data) {
        mFragments = data;
        notifyDataSetChanged();
    }

    public void setTitles(List<String> titles) {
        mTitles = titles;
    }


    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}

