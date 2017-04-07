package com.jason.inews.News.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jason.inews.News.adapters.PagerAdapter;
import com.jason.inews.R;

/**
 * Created by 16276 on 2017/1/25.
 */
public class NewsTabAndPagerFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_and_pager, container, false);
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        ViewPager mPager = (ViewPager) view.findViewById(R.id.pager);
        ImageView mAdd = (ImageView) view.findViewById(R.id.add);
        mPager.setAdapter(new PagerAdapter(getChildFragmentManager(), getResources()));
        mTabLayout.setupWithViewPager(mPager);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AddTabsActivity.class));
            }
        });


        return view;
    }


}
