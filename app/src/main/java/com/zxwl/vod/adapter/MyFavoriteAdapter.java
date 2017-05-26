package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zxwl.vod.R;
import com.zxwl.vod.utils.ImageLoader;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/12 16:49
 * ClassName: ${Class_Name}
 */
public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.MyFavoriteHolder> {
    private Context context;

    private int[] drawables = {
            R.drawable.test_home_6,
            R.drawable.test_home_7,
            R.drawable.test_home_1,
            R.drawable.test_home_4,
            R.drawable.test_home_8,
            R.drawable.test_home_9,
            R.drawable.test_home_5,
            R.drawable.test_home_2,
            R.drawable.test_home_3,
            R.drawable.test_home_10
    };

    public MyFavoriteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyFavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFavoriteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite,parent,false));
    }

    @Override
    public void onBindViewHolder(MyFavoriteHolder holder, int position) {
        ImageLoader.loadRadiusRoundedCorners(context,drawables[position],12,holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return drawables.length;
    }

    public static class MyFavoriteHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        public MyFavoriteHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
        }
    }
}
