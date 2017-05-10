package com.jason.inews.News.presenterImpl;

import android.content.Context;

import com.jason.inews.Bean.NewsBean;
import com.jason.inews.News.NewsContract;
import com.jason.inews.News.api.NewsApi;
import com.jason.inews.News.callback.NewsLoadingCallback;
import com.jason.inews.News.model.NewsModelImpl;

import java.util.List;

import static com.jason.inews.News.api.NewsApi.CAI_JING;
import static com.jason.inews.News.api.NewsApi.GUO_JI;
import static com.jason.inews.News.api.NewsApi.GUO_NEI;
import static com.jason.inews.News.api.NewsApi.JUN_SHI;
import static com.jason.inews.News.api.NewsApi.KE_JI;
import static com.jason.inews.News.api.NewsApi.SHE_HUI;
import static com.jason.inews.News.api.NewsApi.SHI_SHANG;
import static com.jason.inews.News.api.NewsApi.TI_yu;
import static com.jason.inews.News.api.NewsApi.TOU_TIAO;
import static com.jason.inews.News.api.NewsApi.TYPE_CAI_JING;
import static com.jason.inews.News.api.NewsApi.TYPE_GUO_JI;
import static com.jason.inews.News.api.NewsApi.TYPE_JUN_SHI;
import static com.jason.inews.News.api.NewsApi.TYPE_KE_JI;
import static com.jason.inews.News.api.NewsApi.YU_LE;

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
        String type = NewsApi.TYPE_TOU_TIAO;
        String key = NewsApi.API_KEY;
        switch (tabID) {
            case TOU_TIAO:
                type = NewsApi.TYPE_TOU_TIAO;
                break;
            case SHE_HUI:
                type = NewsApi.TYPE_SHE_HUI;
                break;
            case TI_yu:
                type = NewsApi.TYPE_TI_YU;
                break;
            case SHI_SHANG:
                type = NewsApi.TYPE_SHI_SHANG;
                break;
            case YU_LE:
                type = NewsApi.TYPE_YU_LE;
                break;
            case GUO_NEI:
                type = NewsApi.TYPE_GUO_NEI;
                break;
            case GUO_JI:
                type = TYPE_GUO_JI;
                break;
            case JUN_SHI:
                type = TYPE_JUN_SHI;
                break;
            case KE_JI:
                type = TYPE_KE_JI;
                break;
            case CAI_JING:
                type = TYPE_CAI_JING;
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
