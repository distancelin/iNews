package com.jason.inews.News.presenterImpl;

import android.content.Context;

import com.jason.inews.Bean.NewsBean;
import com.jason.inews.News.NewsContract;
import com.jason.inews.News.api.NewsApi;
import com.jason.inews.News.callback.NewsLoadingCallback;
import com.jason.inews.News.model.NewsModelImpl;

import java.util.List;

/**
 * Created by 16276 on 2017/1/18.
 */

public class NewsCategoriesPresenterImpl implements NewsContract.NewsCategoriesPresenter, NewsLoadingCallback {
    private NewsContract.NewsCategoriesView mNewsView;
    private NewsModelImpl mNewsModel;

    public NewsCategoriesPresenterImpl(NewsContract.NewsCategoriesView newsView) {
        mNewsModel = new NewsModelImpl();
        mNewsView = newsView;
    }

    @Override
    public void loadNews(int tabID) {
//        switch (tabID) {
//            case 0:
//                mNewsModel.getNews(NewsApi.NEWS_API_URL,NewsApi.TYPE_TOU_TIAO ,NewsApi.API_KEY);
//                break;
//            case 1:
//                mNewsModel.getNews(NewsApi.NEWS_API_URL, NewsApi.TYPE_SHE_HUI ,NewsApi.API_KEY);
//                break;
//            case 2:
//                mNewsModel.getNews(NewsApi.NEWS_API_URL ,NewsApi.TYPE_TI_YU ,NewsApi.API_KEY);
//                break;
//            case 3:
//                mNewsModel.getNews(NewsApi.NEWS_API_URL, NewsApi.TYPE_SHI_SHANG ,NewsApi.API_KEY);
//                break;
//            case 4:
//                mNewsModel.getNews(NewsApi.NEWS_API_URL ,NewsApi.TYPE_YU_LE , NewsApi.API_KEY);
//        }
        String type = NewsApi.TYPE_TOU_TIAO;
        String key = NewsApi.API_KEY;
        switch (tabID) {
            case 0:
                type = NewsApi.TYPE_TOU_TIAO;
                break;
            case 1:
                type = NewsApi.TYPE_SHE_HUI;
                break;
            case 2:
                type = NewsApi.TYPE_TI_YU;
                break;
            case 3:
                type = NewsApi.TYPE_SHI_SHANG;
                break;
            case 4:
                type = NewsApi.TYPE_YU_LE;
                break;
        }
        mNewsModel.getNews(NewsApi.NEWS_API_URL, type, key, this);
    }


    @Override
    public void onSuccess(List<NewsBean.ResultBean.DataBean> dataBeanList) {
        mNewsView.showNews(dataBeanList);
    }

    @Override
    public void onFailure() {

    }
}
