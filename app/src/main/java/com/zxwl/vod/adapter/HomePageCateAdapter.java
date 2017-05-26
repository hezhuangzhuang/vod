package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxwl.vod.R;
import com.zxwl.vod.utils.ImageLoader;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/11 13:57
 * ClassName: ${Class_Name}
 */
public class HomePageCateAdapter extends RecyclerView.Adapter<HomePageCateAdapter.CateHolder> {
    private Context context;

    private int[] drawables = {
            R.drawable.test_cate_1,
            R.drawable.test_cate_2,
            R.drawable.test_cate_3,
            R.drawable.test_cate_4,
            R.drawable.test_cate_5,
            R.drawable.test_cate_6,
    };


    private String[] titles = {
            "Smoke Restaurant",
            "Hawaiian BBQ",
            "Jollibee",
            "Smoke Restaurant",
            "Hawaiian BBQ",
            "Jollibee",
    };


    public HomePageCateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homepage_cate, parent, false));
    }

    @Override
    public void onBindViewHolder(CateHolder holder, final int position) {
        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 10, holder.ivCover);
        holder.tvName.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return drawables.length;
    }

    public static class CateHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvName;
        TextView tvDescribe;
        TextView tvType;
        TextView tvAddress;
        ImageView ivLove;

        public CateHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            ivLove = (ImageView) itemView.findViewById(R.id.iv_love);
        }
    }

    public interface onItemClick {
        void click(int position);
    }

    private onItemClick itemClick;

    public void setItemClick(onItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
