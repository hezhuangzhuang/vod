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
public class DestinaTionScenicAdapter extends RecyclerView.Adapter<DestinaTionScenicAdapter.CateHolder> {

    private Context context;

    private int[] drawables = {
            R.drawable.test_scenic_1,
            R.drawable.test_scenic_2,
            R.drawable.test_scenic_3,
            R.drawable.test_scenic_4,
            R.drawable.test_scenic_5,
            R.drawable.test_scenic_6,
            R.drawable.test_scenic_1,
            R.drawable.test_scenic_2
    };


    private String[] titles = {
            "Maldives-Heaven on Earth",
            "El Nido-Kalanggaman Island",
            "Bolog island-Panglao Island",
            "North Island-Barracuda Lake",
            "Maldives-Heaven on Earth",
            "Maldives-Heaven on Earth",
            "El Nido-Kalanggaman Island",
            "Bolog island-Panglao Island"
    };

    public DestinaTionScenicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination_scenic, parent, false));
    }

    @Override
    public void onBindViewHolder(CateHolder holder, final int position) {
        holder.tvName.setText(titles[position]);
        ImageLoader.loadRadiusRoundedCorners(context,drawables[position],10,holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public static class CateHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvName;
        TextView tvDescribe;
        TextView tvType;
        TextView tvNumber;

        public CateHolder(View itemView) {
            super(itemView);

            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
        }
    }

    public interface onItemClick{
        void click(int position);
    }
    private onItemClick itemClick;

    public void setItemClick(onItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
