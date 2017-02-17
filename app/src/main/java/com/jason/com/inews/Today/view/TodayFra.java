package com.jason.com.inews.Today.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.com.inews.R;

/**
 * Created by 16276 on 2017/1/28.
 */

public class TodayFra extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fra_today,container,false);
        Toolbar toolbar= (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("今天");
        return view;
    }
}
