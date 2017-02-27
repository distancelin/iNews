package com.jason.inews.News.presenterImpl;

import android.content.Context;

import com.jason.inews.News.NewsContract;
import com.jason.inews.News.model.NewsModelImpl;
import com.jason.inews.News.model.iNewsModel;

/**
 * Created by distancelin on 2017/2/26.
 */

public class NewsDetailPresenterImpl implements NewsContract.NewsDetailPresenter, NewsContract.onDetaiNewsLoadingListener {
    private NewsContract.NewsDetailView view;
    private iNewsModel model;

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
