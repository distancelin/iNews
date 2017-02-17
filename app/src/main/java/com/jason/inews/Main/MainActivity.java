package com.jason.inews.Main;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jason.inews.News.view.NewsMainFra;
import com.jason.inews.R;
import com.jason.inews.Today.view.TodayFra;
import com.jason.inews.Utils.FragmentUtil;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        //绑定toggle与drawer
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        //设置drawer的item点击事件监听器
        setupWithNavigationView(mNavigationView);
        FragmentUtil.addFragment(getSupportFragmentManager(), new NewsMainFra());
        getSupportActionBar().setTitle(R.string.news);
    }

    private void setupWithNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news:
                        FragmentUtil.switchFragment(getSupportFragmentManager(), new NewsMainFra());
                        getSupportActionBar().setTitle(R.string.news);
                        break;
                    case R.id.today:
                        FragmentUtil.switchFragment(getSupportFragmentManager(), new TodayFra());
                        getSupportActionBar().setTitle(R.string.today);
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onItemClick() {

    }
}

interface OnItemClickListener {
    void onItemClick();
}
