package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxwl.vod.R;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/11 13:57
 * ClassName: ${Class_Name}
 */

public class ClassIfyAdapter extends RecyclerView.Adapter<ClassIfyAdapter.ClassIfyHolder> {

    private Context context;
    private int[] icons;
    private String[] titles;


    public ClassIfyAdapter(Context context, int[] icons, String[] titles) {
        this.context = context;
        this.icons = icons;
        this.titles = titles;
    }

    @Override
    public ClassIfyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassIfyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_ify, parent, false));
    }

    @Override
    public void onBindViewHolder(ClassIfyHolder holder, final int position) {
        holder.tvTitle.setText(titles[position]);
        holder.tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, icons[position], 0, 0);
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == icons ? 0 : icons.length;
    }

    public static class ClassIfyHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public ClassIfyHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_content);
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
