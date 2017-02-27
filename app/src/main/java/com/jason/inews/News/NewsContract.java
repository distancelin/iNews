package com.jason.inews.News;

import android.content.Context;

import com.jason.inews.Bean.NewsBean;

import java.util.List;

/**
 * Created by 16276 on 2017/1/18.
 */

public interface NewsContract {
    interface NewsCategoriesPresenter {
        void loadNews(int tabID, Context context);

        void loadDetailNews(String newsDetailUrl, Context context);
    }

    interface NewsDetailPresenter {
        void loadDetailNews(String newsDetailUrl, Context context);
    }

    interface NewsCategoriesView {
        void showNews(List<NewsBean.ResultBean.DataBean> dataBeanList);
        void showError(String error);
    }

    interface NewsDetailView {
        void showNews(String detailNews);

        void showProgress();

        void dismissProgress();
    }
    interface onNewsLoadingListener {
        void onSuccess(List<NewsBean.ResultBean.DataBean> dataBeanList);

        void onFail();
    }

    interface onDetaiNewsLoadingListener {
        void onSuccess(String detailNews);
        void onFail();
    }
}
