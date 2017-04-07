package com.jason.inews.News.api.retrofitManager;

import android.util.Log;

import com.jason.inews.APP;
import com.jason.inews.News.api.NewsApi;
import com.jason.inews.Utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by distancelin on 2017/4/5.
 */

public class RetrofitManager {
    //设置缓存超时时间为2天
    private static final int MAX_STALE_DAYS = 2;

    /**
     * @param classType retrofit请求接口对应的class对象
     * @return retrofit.create()方法的返回值
     */
    public static <T> T getInstance(Class<T> classType, String baseUrl, boolean needGsonConvert) {
        int cacheSize = 20 * 1024 * 1024;
        Cache cache = new Cache(new File(APP.getApplication().getCacheDir(), "okhttpCache"), cacheSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(mNETWORK_INTERCEPTOR)
                .addNetworkInterceptor(mNETWORK_INTERCEPTOR)
                .addInterceptor(mHttpLoggingInterceptor)
                .build();
        //retrofit配合rxjava
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                //adapter用于在retrofit接口中返回rxjava的observable对象
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        if (needGsonConvert) {
            builder.addConverterFactory(GsonConverterFactory.create());
        }
        Retrofit retrofit = builder.build();
        T t = retrofit.create(classType);
        return t;
    }

    //该拦截器用于打印response header和request header，方便调试用
    private static final Interceptor mHttpLoggingInterceptor = new Interceptor() {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();

            long t1 = System.nanoTime();
            Log.i("H", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            okhttp3.Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.i("H", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    };

    private static final Interceptor mNETWORK_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();
//            //网络不可用则强制访问cache
            if (!NetUtil.isNetworkAvaliable()) {
                final CacheControl CACHE_60_SECONDS = new CacheControl.Builder().maxStale(MAX_STALE_DAYS, TimeUnit.DAYS).onlyIfCached().build();
                request = request.newBuilder()
                        .cacheControl(CACHE_60_SECONDS)
                        .build();
                Log.i("H", "no network");
            }
            okhttp3.Response originalResponse = chain.proceed(request);
            //网络可用
            if (NetUtil.isNetworkAvaliable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }

//            } else {
//                return originalResponse.newBuilder()
//                        //max-stale对response设置无效，这里用于下次请求设置缓存的生命期
//                        .header("Cache-Control", "public, no-cache, max-stale="+MAX_STALE_TIME)
//                        .removeHeader("Pragma")
//                        .build();
//            }
            return originalResponse;
        }
    };
}
