package com.jason.com.inews.News.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.com.inews.Bean.NewsBean;
import com.jason.com.inews.News.view.NewsDetailAct;
import com.jason.com.inews.R;
import com.jason.com.inews.Utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by distancelin on 2017/2/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<NewsBean.ResultBean.DataBean> dataBeanList;
    Fragment fragment;

    public void setDataBeanList(List<NewsBean.ResultBean.DataBean> dataBeanList) {
        if (this.dataBeanList!=null){
            this.dataBeanList.clear();
            this.dataBeanList.addAll(dataBeanList);
        }
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(List<NewsBean.ResultBean.DataBean> dataBeanList, Fragment fragment) {
        this.dataBeanList = dataBeanList;
        this.fragment = fragment;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsView = inflater.inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(newsView,fragment,dataBeanList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        NewsBean.ResultBean.DataBean dataBean = dataBeanList.get(position);
        holder.title.setText(dataBean.getTitle());
        holder.time.setText(dataBean.getDate());
        ImageLoaderUtil.loadImage(fragment, dataBean.getThumbnail_pic_s(), holder.picture);
    }

    @Override
    public int getItemCount() {
        if (dataBeanList == null) {
           return 0;
        }
        return dataBeanList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView title;
        TextView time;

        public ViewHolder(View itemView, final Fragment fragment, final List<NewsBean.ResultBean.DataBean> dataBeen) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.news_picture);
            title = (TextView) itemView.findViewById(R.id.news_title);
            time = (TextView) itemView.findViewById(R.id.news_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(fragment.getActivity(), NewsDetailAct.class);
                    intent.putExtra("newDetailUrl",dataBeen.get(ViewHolder.this.getPosition()).getUrl());
                    fragment.getActivity().startActivity(intent);
                }
            });
        }
    }
}
