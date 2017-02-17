package com.jason.inews.Today.contract;

import com.jason.inews.BaseView;
import com.jason.inews.BasePresenter;

/**
 * Created by 16276 on 2016/12/14.
 */

public interface TodayContract {
    interface iView extends BaseView<iPresenter> {
        void showEmptyInputError();

        void showLoading();

        String getMonth();

        String getDay();

        void initViews();

        void dismissLoading();

        void showResult(String response);
    }

    interface iPresenter extends BasePresenter {
        void loadToday(String month, String day);
    }
}
