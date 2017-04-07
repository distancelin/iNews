package com.jason.inews.News.model;

import android.content.Context;

import com.jason.inews.News.callback.DetailNewsLoadingCallback;
import com.jason.inews.News.callback.NewsLoadingCallback;

/**
 * Created by 16276 on 2017/1/18.
 */

public interface NewsModel {
    //获取新闻分类
    void getNews(String baseUrl, String newsType, String key, NewsLoadingCallback callback);

    //获取具体新闻内容
    void getDetailNews(String detailNewsUrl, Context context, DetailNewsLoadingCallback callback);
}
