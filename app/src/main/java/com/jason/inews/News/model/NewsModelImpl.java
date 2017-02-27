package com.jason.inews.News.model;

import android.content.Context;
import android.util.Log;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public void getDetailNews(String detailNewsUrl, Context context, final NewsContract.onDetaiNewsLoadingListener listener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, detailNewsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Pattern titlePattern=Pattern.compile("<h1 class=\"title\">(.*)</h1>");
//                Pattern contentPattern=Pattern.compile("<p class=\"section txt\">(.*)</p>");
//                Matcher titleMatcher=titlePattern.matcher(response);
//                Matcher contentMatcher=contentPattern.matcher(response);
//                if(titleMatcher.find()){
//                    Log.i("H",titleMatcher.group(1));
//                }
//                while(contentMatcher.find()){
//                    Log.i("H",contentMatcher.group(1));
//                }
//                StringBuilder result=new StringBuilder();
//                while (true) {
//                    Log.i("H",response);
//                    Pattern pattern = Pattern.compile("<script(.*)</script>");
//                    Matcher matcher = pattern.matcher(response);
//                    if (matcher.find()) {
//                        String[] temp = response.split("<script(.*)</script>");
//                        result.append(temp[0]);
//                        response = temp[1];
//                    }
//                    else {
//                        break;
//                    }
//                }
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFail();
            }
        });
        requestQueue.add(stringRequest);
    }
}
