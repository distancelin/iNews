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
import com.jason.inews.News.views.NewsDetailActivity;
import com.jason.inews.R;
import com.jason.inews.Utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by distancelin on 2017/2/16.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {
    private List<NewsBean.ResultBean.DataBean> mDataBeans;
    private Fragment mFragment;

    public void setmDataBeans(List<NewsBean.ResultBean.DataBean> dataBeans) {
        if (this.mDataBeans != null) {
            this.mDataBeans.clear();
            this.mDataBeans.addAll(dataBeans);
        }
        this.mDataBeans = dataBeans;
        notifyDataSetChanged();
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
            return new ViewHolder(newsView, null, null);
        }
        View newsView = inflater.inflate(R.layout.news_item, parent, false);
        return new ViewHolder(newsView, mFragment, mDataBeans);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;
        private TextView title;
        private TextView time;

        ViewHolder(View itemView, final Fragment fragment, final List<NewsBean.ResultBean.DataBean> dataBeen) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.news_picture);
            title = (TextView) itemView.findViewById(R.id.news_title);
            time = (TextView) itemView.findViewById(R.id.news_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fragment.getActivity(), NewsDetailActivity.class);
                    String[] urls = new String[3];
                    //获取新闻详情url
                    urls[0] = dataBeen.get(ViewHolder.this.getAdapterPosition()).getUrl();
                    //获取新闻图片url
                    urls[1] = dataBeen.get(ViewHolder.this.getAdapterPosition()).getThumbnail_pic_s02();
                    //获取新闻title
                    urls[2] = dataBeen.get(ViewHolder.this.getAdapterPosition()).getTitle();
                    intent.putExtra("urls", urls);
                    fragment.getActivity().startActivity(intent);
                }
            });
        }
    }
}
