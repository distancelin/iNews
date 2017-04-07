package com.jason.inews.News.adapters;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jason.inews.News.views.NewsListFragment;
import com.jason.inews.R;

/**
 * Created by distancelin on 2017/2/16.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    Resources mResources;

    public PagerAdapter(FragmentManager fm, Resources resources) {
        super(fm);
        this.mResources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsListFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return mResources.getStringArray(R.array.myTabs).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] title = mResources.getStringArray(R.array.myTabs);
        return title[position];
    }
}
