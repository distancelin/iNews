package com.jason.inews.News.model;

import android.content.Context;

import com.jason.inews.News.NewsContract;

/**
 * Created by 16276 on 2017/1/18.
 */

public interface iNewsModel {
    //获取新闻分类
    void getNews(String url, Context context, NewsContract.onNewsLoadingListener listener);

    //获取具体新闻内容
    void getDetailNews(String detailNewsUrl, Context context, NewsContract.onDetaiNewsLoadingListener listener);
}
