package com.jason.inews.News.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.jason.inews.News.NewsContract;
import com.jason.inews.News.presenterImpl.NewsDetailPresenterImpl;
import com.jason.inews.R;
import com.jason.inews.Utils.ImageLoaderUtil;

/**
 * Created by distancelin on 2017/2/16.
 */

public class NewsDetailAct extends AppCompatActivity implements NewsContract.NewsDetailView {

    private Toolbar mToolbar;
    private NewsContract.NewsDetailPresenter mPresenter;
    private ImageView mImageView;
    private WebView mWebView;
    private String[] urls;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail_news);
        mPresenter = new NewsDetailPresenterImpl(this);
        initViews();
        ImageLoaderUtil.loadImage(getApplicationContext(), urls[1], mImageView);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new NoAdWebViewClient(this, urls[0]));
        mWebView.loadUrl(urls[0]);
//        mPresenter.loadDetailNews(urls[0], getApplicationContext());
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.newsDetailToolbar);
        mImageView = (ImageView) findViewById(R.id.detailNewsImage);
        mWebView = (WebView) findViewById(R.id.detailNewsContent);
        urls = getIntent().getStringArrayExtra("urls");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collToolbarLayout);
        collapsingToolbarLayout.setTitle("新闻详情");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showNews(String detailNews) {
        ImageLoaderUtil.loadImage(getApplicationContext(), urls[1], mImageView);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new NoAdWebViewClient(this, urls[0]));

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}

class NoAdWebViewClient extends WebViewClient {
    private String homeurl;
    private Context context;

    public NoAdWebViewClient(Context context, String homeurl) {
        this.context = context;
        this.homeurl = homeurl;
    }


    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        url = url.toLowerCase();
        if (!url.contains(homeurl)) {
            if (!ADFilterTool.hasAd(context, url)) {
                return super.shouldInterceptRequest(view, url);
            } else {
                return new WebResourceResponse(null, null, null);
            }
        } else {
            return super.shouldInterceptRequest(view, url);
        }


    }
}

class ADFilterTool {
    public static boolean hasAd(Context context, String url) {
        Resources res = context.getResources();
        String[] adUrls = res.getStringArray(R.array.adBlockUrl);
        for (String adUrl : adUrls) {
            if (url.contains(adUrl)) {
                return true;
            }
        }
        return false;
    }
}