package com.jason.inews.News;

import android.content.Context;

import com.jason.inews.Bean.NewsBean;

import java.util.List;

/**
 * Created by 16276 on 2017/1/18.
 */

public interface NewsContract {
    interface iNewsPresenter {
        void loadNews(int tabID, Context context);
    }

    interface iNewsView {
        void showNews(List<NewsBean.ResultBean.DataBean> dataBeanList);

        void showError(String error);
    }

    interface onNewsLoadingListener {
        void onSuccess(List<NewsBean.ResultBean.DataBean> dataBeanList);

        void onFail();
    }
}
