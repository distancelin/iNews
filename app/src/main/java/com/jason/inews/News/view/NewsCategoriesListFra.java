package com.jason.inews.News.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private boolean mIsVisibleToUser = false;
    private boolean mIsFirstLoading = true;

    //创建每一类新闻fragment的时候为其赋值新闻类型参数（tabId）
    public static NewsCategoriesListFra newInstance(int newsType) {
        Bundle args = new Bundle();
        args.putInt("newsType", newsType);
        NewsCategoriesListFra newsListFra = new NewsCategoriesListFra();
        newsListFra.setArguments(args);
        return newsListFra;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("H", "fragment " + getArguments().getInt("newsType") + " onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("H", "fragment " + getArguments().getInt("newsType") + " onStart");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        tabID = getArguments().getInt("newsType");
        if (isVisibleToUser) {
            mIsVisibleToUser = true;
            if (getContext() != null && mIsFirstLoading) {
                if (tabID == 0) {
                    presenter = new NewsCategoriesPresenterImpl(this);
                }
                presenter.loadNews(tabID, getContext());
                swipeRefreshLayout.setRefreshing(true);
                mIsFirstLoading = false;
            }
        } else {
            mIsVisibleToUser = false;
        }
        Log.i("H", "fragment " + getArguments().getInt("newsType") + " setUserVisibleHint " + isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("H", "fragment " + getArguments().getInt("newsType") + " onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("H", "fragment " + getArguments().getInt("newsType") + "  onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("H", "fragment " + tabID + "  onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("H", "fragment " + tabID + "  onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("H", "fragment " + tabID + "  onDetach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("H", "fragment " + getArguments().getInt("newsType") + " onCreateView");
        View view = inflater.inflate(R.layout.fra_news, container, false);
        presenter = new NewsCategoriesPresenterImpl(this);
        setHasOptionsMenu(true);
        initViews(view);
        swtUpSwipeToRefresh(swipeRefreshLayout, tabID);
        if (mIsVisibleToUser && mIsFirstLoading) {
            presenter.loadNews(tabID, getContext());
            swipeRefreshLayout.setRefreshing(true);
            mIsFirstLoading = false;
        }
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
