package com.jason.com.inews.Today.model;

import com.google.gson.Gson;
import com.jason.com.inews.Bean.HistoryEventBean;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 16276 on 2016/12/15.
 */

public class modelImp implements iModel {
    @Override
    public void queryTodayInHistory(final String url, final onQueryListener listener) {
        //query today with okHttp
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response  response;
                String result="";
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url(url)
                        .build();
                try {
                    response=client.newCall(request).execute();
                    result= response.body().string();
                    if(result.contains("success")){
                        listener.onSuccess(result);
                        Gson gson=new Gson();
                        HistoryEventBean eventBean=gson.fromJson(result,HistoryEventBean.class);
                    }
                    else {
                        listener.onFail();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    }
