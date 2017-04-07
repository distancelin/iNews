package com.jason.inews.News.api.retrofitApiInterface;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by distancelin on 2017/4/8.
 */

public interface NewsDetailApi {

    /**
     * @param detailNewsUrl 新闻详情Url,采用@Url注解会将baseUrl覆盖而采用该Url作为全路径
     * @return ResponseBody作为泛型，因为Retrofit不支持string类型的返回值，可以通过response.string()转换为string
     */
    @GET
    Observable<ResponseBody> getDetailNews(@Url String detailNewsUrl);
}
