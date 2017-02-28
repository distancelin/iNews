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

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by distancelin on 2017/2/16.
 */

public class NewsDetailAct extends AppCompatActivity implements NewsContract.NewsDetailView {

    private Toolbar mToolbar;
    private NewsContract.NewsDetailPresenter mPresenter;
    private ImageView mImageView;
    private String[] mUrls;
    private HtmlTextView mHtmlTextView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail_news);
        mPresenter = new NewsDetailPresenterImpl(this);
        initViews();
        ImageLoaderUtil.loadImage(getApplicationContext(), mUrls[1], mImageView);
        mPresenter.loadDetailNews(mUrls[0], getApplicationContext());
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.newsDetailToolbar);
        mImageView = (ImageView) findViewById(R.id.detailNewsImage);
//        mWebView = (WebView) findViewById(R.id.detailNewsContent);
        mHtmlTextView = (HtmlTextView) findViewById(R.id.content);
        mUrls = getIntent().getStringArrayExtra("urls");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collToolbarLayout);
        collapsingToolbarLayout.setTitle("新闻详情");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showNews(String detailNews) {
        //判断图片url是否为null
        if (mUrls[1] != null) {
            ImageLoaderUtil.loadImage(getApplicationContext(), mUrls[1], mImageView);
        } else {
            //为空显示JJ图片
            mImageView.setImageResource(R.drawable.jj);
        }
        mHtmlTextView.setHtml(detailNews, new HtmlHttpImageGetter(mHtmlTextView, null, true));
//        mWebView.getSettings().setLoadsImagesAutomatically(true);
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebViewClient(new NoAdWebViewClient(this, mUrls[0]));

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}