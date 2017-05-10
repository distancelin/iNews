package com.jason.inews.News.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.inews.Bean.NewsBean;
import com.jason.inews.News.callback.NewsItemClickCallback;
import com.jason.inews.News.views.activity.NewsDetailActivity;
import com.jason.inews.R;
import com.jason.inews.Utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by distancelin on 2017/2/16.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {
    private List<NewsBean.ResultBean.DataBean> mDataBeans;
    private Fragment mFragment;
    private NewsItemClickCallback mNewsClickListener;
    public void setmDataBeans(List<NewsBean.ResultBean.DataBean> dataBeans) {
        if (this.mDataBeans != null) {
            this.mDataBeans.clear();
            this.mDataBeans.addAll(dataBeans);
        }
        this.mDataBeans = dataBeans;
        notifyDataSetChanged();
    }

    public void setNewsClickListener(NewsItemClickCallback mNewsClickListener) {
        this.mNewsClickListener = mNewsClickListener;
    }

    public NewsRecyclerViewAdapter(List<NewsBean.ResultBean.DataBean> dataBeanList, Fragment fragment) {
        this.mDataBeans = dataBeanList;
        this.mFragment = fragment;
    }

    @Override
    public NewsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == 1) {
            View newsView = inflater.inflate(R.layout.footer, parent, false);
            return new ViewHolder(newsView, null);
        }
        View newsView = inflater.inflate(R.layout.news_item, parent, false);
        return new ViewHolder(newsView, mDataBeans);
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.ViewHolder holder, int position) {
        if (position != 30) {
            NewsBean.ResultBean.DataBean dataBean = mDataBeans.get(position);
            holder.title.setText(dataBean.getTitle());
            holder.time.setText(dataBean.getDate());
            ImageLoaderUtil.loadImage(mFragment, dataBean.getThumbnail_pic_s(), holder.picture);
        }

    }

    @Override
    public int getItemCount() {
        if (mDataBeans == null) {
            return 0;
        }
        return mDataBeans.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 30) {
            return 1;
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;
        private TextView title;
        private TextView time;

        ViewHolder(View itemView, final List<NewsBean.ResultBean.DataBean> dataBean) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.news_picture);
            title = (TextView) itemView.findViewById(R.id.news_title);
            time = (TextView) itemView.findViewById(R.id.news_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNewsClickListener.onNewsItemClick(dataBean.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
