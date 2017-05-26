package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxwl.vod.R;
import com.zxwl.vod.utils.ImageLoader;
import com.zxwl.vod.views.CustomSelfProportionImageView;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/12 16:18
 * ClassName: ${Class_Name}
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    private int[] drawables = {
            R.drawable.test_search_1,
            R.drawable.test_search_2,
            R.drawable.test_search_3,
            R.drawable.test_search_4,
            R.drawable.test_search_5,
            R.drawable.test_search_6,
            R.drawable.test_search_7,
    };


    private String[] titles = {
            "Balicasag Island",
            "The Shawshank Redemption",
            "A dog's purpose",
            "Balicasag Island",
            "Malcapuya Island",
            "Cayangan Lake",
            "National Geographic Society"
    };

    private String[] describes = {
            "National Geographic Society",
            "You came here to save your soul",
            "National Geographic Society",
            "National Geographic Society",
            "National Geographic Society",
            "Maldives-Heaven on Earth",
            "National Geographic Society"
    };


    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.tvTitle.setText(titles[position]);
        holder.tvDescribe.setText(describes[position]);
        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 10, holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return drawables.length;
    }

    public static class SearchHolder extends RecyclerView.ViewHolder {
        CustomSelfProportionImageView ivCover;
        TextView tvTitle;
        TextView tvDescribe;

        public SearchHolder(View convertView) {
            super(convertView);
            ivCover = (CustomSelfProportionImageView) convertView.findViewById(R.id.iv_cover);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvDescribe = (TextView) convertView.findViewById(R.id.tv_describe);
        }
    }

}
