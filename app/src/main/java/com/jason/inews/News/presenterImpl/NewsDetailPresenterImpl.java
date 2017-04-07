package com.jason.inews.News.presenterImpl;

import android.content.Context;

import com.jason.inews.News.NewsContract;
import com.jason.inews.News.callback.DetailNewsLoadingCallback;
import com.jason.inews.News.model.NewsModelImpl;
import com.jason.inews.News.model.NewsModel;

/**
 * Created by distancelin on 2017/2/26.
 */

public class NewsDetailPresenterImpl implements NewsContract.NewsDetailPresenter, DetailNewsLoadingCallback {
    private NewsContract.NewsDetailView view;
    private NewsModel model;


    public NewsDetailPresenterImpl(NewsContract.NewsDetailView view) {
        this.view = view;
        this.model = new NewsModelImpl();
    }

    public void loadDetailNews(String newsDetailUrl, Context context) {
        view.showProgress();
        model.getDetailNews(newsDetailUrl, context, this);
    }

    @Override
    public void onSuccess(String detailNews) {
        view.dismissProgress();
        view.showNews(detailNews);
    }

    @Override
    public void onFail() {

    }
}
