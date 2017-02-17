package com.jason.inews.Today.presenter;

import com.jason.inews.Today.contract.TodayContract;
import com.jason.inews.Today.model.iModel;
import com.jason.inews.Today.model.modelImp;


/**
 * Created by 16276 on 2016/12/14.
 */

public class PresenterImp implements TodayContract.iPresenter {
    private TodayContract.iView mView;
    private iModel mModel;

    public PresenterImp(TodayContract.iView view) {
        this.mView = view;
        this.mModel = new modelImp();
    }

    @Override
    public void loadToday(String month, String day) {
        if (!isEmpty(month, day)) {
            String TODAYURL = "http://v.juhe.cn/todayOnhistory/queryEvent.php";
            String APPKEY = "664905c187ef805a534bb457bb5b3b9f";
            String QUERYURL = TODAYURL + "?date=" + month + "/" + day + "&key=" + APPKEY;
            mView.showLoading();
            mModel.queryTodayInHistory(QUERYURL, new iModel.onQueryListener() {
                @Override
                public void onSuccess(String result) {
                    mView.showResult(result);
                }

                @Override
                public void onFail() {

                }
            });
        } else {
            mView.showEmptyInputError();
        }
    }

    private boolean isEmpty(String month, String day) {
        return month.equals("") || day.equals("");
    }

    @Override
    public void start() {

    }
}
