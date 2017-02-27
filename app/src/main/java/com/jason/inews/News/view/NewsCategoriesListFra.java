package com.jason.inews.News.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jason.inews.Bean.NewsBean;
import com.jason.inews.News.NewsContract;
import com.jason.inews.News.adapters.RecyclerViewAdapter;
import com.jason.inews.News.presenterImpl.NewsCategoriesPresenterImpl;
import com.jason.inews.R;


import java.util.List;

/**
 * Created by 16276 on 2017/1/26.
 */

public class NewsCategoriesListFra extends Fragment implements NewsContract.NewsCategoriesView {
    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsContract.NewsCategoriesPresenter presenter;
    private RecyclerViewAdapter recyclerAdapter;
    private int tabID;

    //创建每一类新闻fragment的时候为其赋值新闻类型参数（tabId）
    public static NewsCategoriesListFra newInstance(int newsType) {
        Bundle args = new Bundle();
        args.putInt("newsType", newsType);
        NewsCategoriesListFra newsListFra = new NewsCategoriesListFra();
        newsListFra.setArguments(args);
        return newsListFra;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_news, container, false);
        initViews(view);
        presenter = new NewsCategoriesPresenterImpl(this);
        tabID = getArguments().getInt("newsType");
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadNews(tabID, getContext());
        swtUpSwipeToRefresh(swipeRefreshLayout, tabID);
        return view;
    }

    private void initViews(View view) {
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerAdapter = new RecyclerViewAdapter(null, this);
        rv.setAdapter(recyclerAdapter);
        rv.setHasFixedSize(true);
    }

    private void swtUpSwipeToRefresh(SwipeRefreshLayout swipeRefreshLayout, final int tabId) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews(tabId, getContext());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_overflow_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                presenter.loadNews(tabID, getContext());
                swipeRefreshLayout.setRefreshing(true);
                break;
        }
        return true;
    }

    @Override
    public void showNews(final List<NewsBean.ResultBean.DataBean> dataBeanList) {
        recyclerAdapter.setDataBeanList(dataBeanList);
        swipeRefreshLayout.setRefreshing(false);
//        Toast.makeText(getContext(),"新闻更新完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
