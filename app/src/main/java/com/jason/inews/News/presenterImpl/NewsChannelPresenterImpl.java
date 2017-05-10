package com.jason.inews.News.presenterImpl;

import android.content.Context;
import android.content.SharedPreferences;

import com.jason.inews.APP;
import com.jason.inews.News.NewsContract;

/**
 * Created by distancelin on 2017/4/28.
 */

public class NewsChannelPresenterImpl implements NewsContract.NewsChannelPresenter {
    @Override
    public void updateSharedPreference(String newsTittle, boolean checked) {
        SharedPreferences preferences = APP.getApplication().getSharedPreferences("NewsChannelPrefer", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(newsTittle, checked);
        editor.apply();
    }
}
