package com.jason.inews.News.views.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.inews.News.NewsContract;
import com.jason.inews.News.presenterImpl.NewsDetailPresenterImpl;
import com.jason.inews.R;
import com.jason.inews.Utils.ImageLoaderUtil;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by distancelin on 2017/2/16.
 */

public class NewsDetailActivity extends AppCompatActivity implements NewsContract.NewsDetailView {

    private NewsContract.NewsDetailPresenter mPresenter;
    private ImageView mImageView;
    private String[] mUrls;
    private HtmlTextView mHtmlTextView;
    private TextView mFooter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        mPresenter = new NewsDetailPresenterImpl(this);
        initViews();
        ImageLoaderUtil.loadImage(getApplicationContext(), mUrls[1], mImageView);
        mPresenter.loadDetailNews(mUrls[0], getApplicationContext());
    }

    private void initViews() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.newsDetailToolbar);
        mImageView = (ImageView) findViewById(R.id.detailNewsImage);
//        mWebView = (WebView) findViewById(R.id.detailNewsContent);
        mHtmlTextView = (HtmlTextView) findViewById(R.id.content);
        mFooter = (TextView) findViewById(R.id.footer);
        mUrls = getIntent().getStringArrayExtra("urls");
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collToolbarLayout);
        mCollapsingToolbarLayout.setTitle("新闻详情");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFooter.setVisibility(View.GONE);
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
        mFooter.setVisibility(View.VISIBLE);
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