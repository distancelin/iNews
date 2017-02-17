package com.jason.inews.News.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jason.inews.Bean.NewsBean;
import com.jason.inews.News.NewsContract;

import java.util.List;

/**
 * Created by 16276 on 2017/1/18.
 */

public class NewsModelImpl implements iNewsModel {
    @Override
    public void getNews(String url, Context context, final NewsContract.onNewsLoadingListener listener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsBean bean = gson.fromJson(response, NewsBean.class);
                List<NewsBean.ResultBean.DataBean> dataBeanList = bean.getResult().getData();
                listener.onSuccess(dataBeanList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
