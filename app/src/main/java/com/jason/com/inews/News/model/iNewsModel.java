package com.jason.com.inews.News.model;

import android.content.Context;

import com.jason.com.inews.News.NewsContract;

/**
 * Created by 16276 on 2017/1/18.
 */

public interface iNewsModel {
    void getNews(String url, Context context,NewsContract.onNewsLoadingListener listener);
}
