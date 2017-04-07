package com.jason.inews.News.callback;

import com.jason.inews.Bean.NewsBean;

import java.util.List;

/**
 * Created by distancelin on 2017/4/4.
 */

public interface NewsLoadingCallback {
    void onSuccess(List<NewsBean.ResultBean.DataBean> dataBeanList);

    void onFailure();
}
