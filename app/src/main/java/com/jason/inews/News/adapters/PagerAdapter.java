package com.jason.inews.News.adapters;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jason.inews.News.view.NewsCategoriesListFra;
import com.jason.inews.R;

/**
 * Created by distancelin on 2017/2/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    Resources mResources;

    public PagerAdapter(FragmentManager fm, Resources resources) {
        super(fm);
        this.mResources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsCategoriesListFra.newInstance(position);

    }

    @Override
    public int getCount() {
        return mResources.getStringArray(R.array.tabs).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] title = mResources.getStringArray(R.array.tabs);
        return title[position];
    }
}
