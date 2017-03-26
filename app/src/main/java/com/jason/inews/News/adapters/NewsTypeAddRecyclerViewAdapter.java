package com.jason.inews.News.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.jason.inews.R;

/**
 * Created by distancelin on 2017/3/4.
 */

public class NewsTypeAddRecyclerViewAdapter extends RecyclerView.Adapter<NewsTypeAddRecyclerViewAdapter.ViewHolder> {
    private String[] mAllNewsTypes;

    public NewsTypeAddRecyclerViewAdapter(Resources mResources) {
        this.mAllNewsTypes = mResources.getStringArray(R.array.allNewsTypes);
    }

    @Override
    public NewsTypeAddRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.add_news_type_item, parent, false);
        return new ViewHolder(view, mAllNewsTypes);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNewsType.setText(mAllNewsTypes[position]);
    }

    @Override
    public int getItemCount() {
        //列表的item数量为所有新闻数量
        return mAllNewsTypes.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNewsType;
        private Switch mSwitchNewsType;

        ViewHolder(View itemView, final String[] newsTypes) {
            super(itemView);
            mNewsType = (TextView) itemView.findViewById(R.id.tvAddNewsType);
            mSwitchNewsType = (Switch) itemView.findViewById(R.id.switchNews);
            mSwitchNewsType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.i("H", newsTypes[getAdapterPosition()] + "checked=" + isChecked);
                }
            });
        }
    }
}
