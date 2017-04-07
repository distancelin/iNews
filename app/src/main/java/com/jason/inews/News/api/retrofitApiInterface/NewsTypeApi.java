package com.jason.inews.News.api.retrofitApiInterface;

import com.jason.inews.Bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by distancelin on 2017/4/2.
 * 设置Cache-Control: max-age=0,意味着首次请求成功之后得到的response cache将在0秒之后过期，即马上过期，设置max-stale=value
 * 意味着在cache已经过期之后的value秒时间内仍然可以使用该cache，超过value时间之后将会删除cache，max-age不会删除cache，max-stale会删除cache
 */

public interface NewsTypeApi {

    @Headers("Cache-Control: max-age=0")
    @GET("index")
    /**
     * 这种格式相当于index?type=type&key=key
     */
    Observable<NewsBean> call(@Query("type") String type, @Query("key") String key);
}
