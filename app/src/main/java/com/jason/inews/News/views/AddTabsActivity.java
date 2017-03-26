package com.jason.inews.News.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jason.inews.News.adapters.NewsTypeAddRecyclerViewAdapter;
import com.jason.inews.R;

public class AddTabsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tabs);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvNewsType);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("新闻分类展示");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView.setAdapter(new NewsTypeAddRecyclerViewAdapter(getResources()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
