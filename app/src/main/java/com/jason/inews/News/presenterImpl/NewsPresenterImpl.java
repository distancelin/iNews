package com.jason.inews.News.presenterImpl;

import android.content.Context;

import com.jason.inews.Bean.NewsBean;
import com.jason.inews.News.NewsContract;
import com.jason.inews.News.model.NewsModelImpl;

import java.util.List;

/**
 * Created by 16276 on 2017/1/18.
 */

public class NewsPresenterImpl implements NewsContract.iNewsPresenter, NewsContract.onNewsLoadingListener {
    private NewsContract.iNewsView mNewsView;
    private NewsModelImpl mNewsModel;

    public NewsPresenterImpl(NewsContract.iNewsView newsView) {
        mNewsModel = new NewsModelImpl();
        mNewsView = newsView;
    }

    @Override
    public void loadNews(int tabID, Context context) {
        switch (tabID) {
            case 0:
                mNewsModel.getNews("http://v.juhe.cn/toutiao/index?type=toutiao&key=b3e2de2ff0805690895bb4e4d03e8032", context, this);
                break;
            case 1:
                mNewsModel.getNews("http://v.juhe.cn/toutiao/index?type=shehui&key=b3e2de2ff0805690895bb4e4d03e8032", context, this);
                break;
            case 2:
                mNewsModel.getNews("http://v.juhe.cn/toutiao/index?type=tiyu&key=b3e2de2ff0805690895bb4e4d03e8032", context, this);
                break;
            case 3:
                mNewsModel.getNews("http://v.juhe.cn/toutiao/index?type=shishang&key=b3e2de2ff0805690895bb4e4d03e8032", context, this);
                break;
            case 4:
                mNewsModel.getNews("http://v.juhe.cn/toutiao/index?type=yule&key=b3e2de2ff0805690895bb4e4d03e8032", context, this);
        }
    }

    @Override
    public void onSuccess(List<NewsBean.ResultBean.DataBean> dataBeanList) {
        mNewsView.showNews(dataBeanList);
    }

    @Override
    public void onFail() {

    }
}
