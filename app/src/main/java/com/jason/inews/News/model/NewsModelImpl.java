package com.jason.inews.News.model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jason.inews.Bean.NewsBean;
import com.jason.inews.News.api.NewsApi;
import com.jason.inews.News.api.retrofitApiInterface.NewsDetailApi;
import com.jason.inews.News.api.retrofitApiInterface.NewsTypeApi;
import com.jason.inews.News.api.retrofitManager.RetrofitService;
import com.jason.inews.News.callback.DetailNewsLoadingCallback;
import com.jason.inews.News.callback.NewsLoadingCallback;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 16276 on 2017/1/18.
 */

public class NewsModelImpl implements NewsModel {
//     public void getNews(String url, Context context, final NewsContract.onNewsLoadingListener listener) {
//    //使用volley进行网络请求
//         RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                NewsBean bean = gson.fromJson(response, NewsBean.class);
//                List<NewsBean.ResultBean.DataBean> dataBeanList = bean.getResult().getData();
//                listener.onSuccess(dataBeanList);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(stringRequest);*/
    //使用retrofit进行网络请求


    // }

    @Override
    public void getNews(String baseUrl, String newsType, String key, final NewsLoadingCallback callback) {
        //原生retrofit
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
//        NewsTypeApi newsTypeApi=retrofit.create(NewsTypeApi.class);
//        final Call<NewsBean> newsBean=newsTypeApi.call(newsType,key);
//        newsBean.enqueue(new Callback<NewsBean>() {
//            @Override
//            public void onResponse(Call<NewsBean> call, retrofit2.Response<NewsBean> response) {
//                NewsBean bean=response.body();
//                List<NewsBean.ResultBean.DataBean> dataBeanList=bean.getResult().getData();
//                for (NewsBean.ResultBean.DataBean data:dataBeanList
//                     ) {
//                    Log.i("H",data.getTitle());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsBean> call, Throwable t) {
//
//            }
//        });
        NewsTypeApi newsTypeApi = RetrofitService.getInstance(NewsTypeApi.class, NewsApi.NEWS_API_URL, true);
        Observable<NewsBean> call = newsTypeApi.call(newsType, key);
        //观察者的代码将会在mainThread执行
        call.subscribeOn(Schedulers.io())
                //将返回的newsBean转换为list<dataBean>类型
                .map(new Function<NewsBean, List<NewsBean.ResultBean.DataBean>>() {
                    @Override
                    public List<NewsBean.ResultBean.DataBean> apply(@NonNull NewsBean newsBean) throws Exception {
                        return newsBean.getResult().getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsBean.ResultBean.DataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<NewsBean.ResultBean.DataBean> datas) {
                        callback.onSuccess(datas);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getDetailNews(String detailNewsUrl, Context context, final DetailNewsLoadingCallback listener) {
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        CharsetStringRequest stringRequest = new CharsetStringRequest(Request.Method.GET, detailNewsUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //删除重复的标题
//                String[] result = response.split("<title>(.*)</title>\\r\\n");
//                String Response = result[0] + result[1];
//                String[] finalResponses = Response.split("<h1 class=\"title\">(.*)</h1>\\r\\n");
//                String finalResponse = finalResponses[0] + finalResponses[1];
//                listener.onSuccess(finalResponse);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onFail();
//            }
//        });
//        requestQueue.add(stringRequest);
        NewsDetailApi newsDetailApi = RetrofitService.getInstance(NewsDetailApi.class, detailNewsUrl + "/", false);
        Observable<ResponseBody> detailNewsCall = newsDetailApi.getDetailNews(detailNewsUrl);
        detailNewsCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String s = responseBody.string();
                            String[] result = s.split("<title>(.*)</title>\\r\\n");
                            String Response = result[0] + result[1];
                            String[] finalResponses = Response.split("<h1 class=\"title\">(.*)</h1>\\r\\n");
                            String finalResponse = finalResponses[0] + finalResponses[1];
                            listener.onSuccess(finalResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
