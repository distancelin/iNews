package com.jason.inews.News.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jason.inews.APP;
import com.jason.inews.News.adapters.PagerAdapter;
import com.jason.inews.News.event.NewsChannelChangeEvent;
import com.jason.inews.News.rxbus.RxBus;
import com.jason.inews.News.views.activity.AddTabsActivity;
import com.jason.inews.R;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

/**
 * Created by 16276 on 2017/1/25.
 */
public class NewsTabAndPagerFragment extends Fragment {
    private ArrayList<String> mTabTittles;
    private ArrayList<NewsListFragment> mFragments;
    private PagerAdapter mAdapter;
    private ViewPager mPager;
    private TabLayout mTabLayout;
    private String mCurrentViewPagerName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeNewsChannelChangeEvent();
    }

    private void subscribeNewsChannelChangeEvent() {
        RxBus.getInstance().toObservable(NewsChannelChangeEvent.class).subscribe(new Consumer<NewsChannelChangeEvent>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull NewsChannelChangeEvent newsChannelChangeEvent) throws Exception {
                loadNewsChannels();
            }
        });
    }

    private void loadNewsChannels() {
        loadTabTittles();
        loadFragments();
//        mAdapter.notifyDataSetChanged();
        mPager.setAdapter(new PagerAdapter(getChildFragmentManager(), mTabTittles, mFragments));
        mTabLayout.setupWithViewPager(mPager);
        setPageChangeListener();
        mPager.setCurrentItem(getCurrentViewPagerPosition());
    }

    private void loadTabTittles() {
        mTabTittles.clear();
        for (String tittle : APP.getAllNewsType()
                ) {
            if (APP.getApplication().getSharedPreferences("NewsChannelPrefer", Context.MODE_PRIVATE).getBoolean(tittle, false)) {
                mTabTittles.add(tittle);
            }
        }
    }

    private void loadFragments() {
        mFragments.clear();
        for (String tittle : mTabTittles
                ) {
            NewsListFragment fragment = NewsListFragment.newInstance(APP.getAllNewsType().indexOf(tittle));
            Log.i("H", "loadFrag" + APP.getAllNewsType().indexOf(tittle));
            mFragments.add(fragment);
        }
    }

    private int getCurrentViewPagerPosition() {
        int position = 0;
        if (mCurrentViewPagerName != null) {
            for (int i = 0; i < mTabTittles.size(); i++) {
                if (mCurrentViewPagerName.equals(mTabTittles.get(i))) {
                    position = i;
                }
            }
        }
        return position;
    }

    private void setPageChangeListener() {
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentViewPagerName = mTabTittles.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_and_pager, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        ImageView mAdd = (ImageView) view.findViewById(R.id.add);
        mFragments = new ArrayList<>();
        mTabTittles = new ArrayList<>();
        mAdapter = new PagerAdapter(getChildFragmentManager(), mTabTittles, mFragments);
        initSharedPrefer();
//        mTabTittles = (ArrayList<String>) Arrays.asList(getResources().getStringArray(R.array.DefaultTabTittles));
        mPager.setAdapter(mAdapter);
        setPageChangeListener();
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AddTabsActivity.class));
                getActivity().overridePendingTransition(R.anim.right_in, android.R.anim.fade_out);
            }
        });
        return view;
    }

    private void initSharedPrefer() {
        if (!APP.getApplication().getSharedPreferences("NewsChannelPrefer", Context.MODE_PRIVATE).getBoolean("first-in", false)) {
            mTabTittles.add("头条");
            mTabTittles.add("社会");
            mFragments.add(NewsListFragment.newInstance(0));
            mFragments.add(NewsListFragment.newInstance(1));
            APP.getApplication().getSharedPreferences("NewsChannelPrefer", Context.MODE_PRIVATE).edit().putBoolean("first-in", true).apply();
        } else {
            loadTabTittles();
            loadFragments();
        }
    }

}
