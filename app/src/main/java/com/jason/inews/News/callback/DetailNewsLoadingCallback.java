package com.jason.inews.News.callback;

/**
 * Created by distancelin on 2017/4/4.
 */

public interface DetailNewsLoadingCallback {
    void onSuccess(String detailNews);

    void onFail();
}
