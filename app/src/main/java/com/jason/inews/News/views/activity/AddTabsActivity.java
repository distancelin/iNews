package com.jason.inews.News.views.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jason.inews.APP;
import com.jason.inews.News.NewsContract;
import com.jason.inews.News.adapters.NewsTypeAddRecyclerViewAdapter;
import com.jason.inews.News.callback.SwitchStateChangeCallback;
import com.jason.inews.News.event.NewsChannelChangeEvent;
import com.jason.inews.News.presenterImpl.NewsChannelPresenterImpl;
import com.jason.inews.News.rxbus.RxBus;
import com.jason.inews.R;

import java.util.ArrayList;

public class AddTabsActivity extends AppCompatActivity implements SwitchStateChangeCallback {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private NewsContract.NewsChannelPresenter mPresenter;
    private ArrayList<Boolean> mCheckedTypes;
    private String[] mAllNewsTypeTittles;
    private boolean mChannelChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tabs);
        mPresenter = new NewsChannelPresenterImpl();
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvNewsType);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAllNewsTypeTittles = getResources().getStringArray(R.array.allNewsTypes);
        mCheckedTypes = new ArrayList<>();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("新闻分类展示");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadNewsChannels();
        mRecyclerView.setAdapter(new NewsTypeAddRecyclerViewAdapter(mAllNewsTypeTittles, mCheckedTypes));
        setOnNewsTypeChangeListener(mRecyclerView);
    }

    /**
     * 从sharedPreferences里加载每种新闻类型的选中情况
     */
    private void loadNewsChannels() {
        for (String tittle : mAllNewsTypeTittles) {
            mCheckedTypes.add(APP.getApplication().getSharedPreferences("NewsChannelPrefer", MODE_PRIVATE)
                    .getBoolean(tittle, false));
        }
    }

    private void setOnNewsTypeChangeListener(RecyclerView mRecyclerView) {
        NewsTypeAddRecyclerViewAdapter adapter = (NewsTypeAddRecyclerViewAdapter) mRecyclerView.getAdapter();
        adapter.setSwitchStateChangeCallback(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSwitchChecked(String newsTittle) {
        mPresenter.updateSharedPreference(newsTittle, true);
        mChannelChanged = true;
    }

    @Override
    public void onSwitchDischecked(String newsTittle) {
        mPresenter.updateSharedPreference(newsTittle, false);
        mChannelChanged = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChannelChanged)
            RxBus.getInstance().post(new NewsChannelChangeEvent());
    }
}
