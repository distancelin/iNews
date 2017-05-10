package com.jason.inews.News.callback;

import com.jason.inews.Bean.NewsBean;

/**
 * Created by distancelin on 2017/4/18.
 */

public interface NewsItemClickCallback {
    void onNewsItemClick(NewsBean.ResultBean.DataBean dataBean);
}
