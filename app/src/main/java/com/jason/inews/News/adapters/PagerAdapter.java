package com.jason.inews.News.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.jason.inews.News.views.fragment.NewsListFragment;

import java.util.ArrayList;

/**
 * Created by distancelin on 2017/2/16.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mTabTittles;
    private ArrayList<NewsListFragment> mFragments;

    public PagerAdapter(FragmentManager fm, ArrayList<String> tittles, ArrayList<NewsListFragment> fragments) {
        super(fm);
        this.mTabTittles = tittles;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);

    }

    @Override
    public int getCount() {
        return mTabTittles.size();
    }

    public void setTabTittles(ArrayList<String> mTabTittles) {
        this.mTabTittles = mTabTittles;
    }

    public void setFragments(ArrayList<NewsListFragment> mFragments) {
        this.mFragments = mFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTittles.get(position);
    }
}
