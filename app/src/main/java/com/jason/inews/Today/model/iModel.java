package com.jason.inews.Today.model;

/**
 * Created by 16276 on 2016/12/15.
 */

public interface iModel {
    void queryTodayInHistory(String url, onQueryListener listener);

    interface onQueryListener {
        void onSuccess(String result);

        void onFail();
    }
}
