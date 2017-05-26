package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxwl.vod.R;

import java.util.List;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/9 14:34
 * ClassName: ${Class_Name}
 */

public class TicketTopAdapter extends RecyclerView.Adapter<TicketTopAdapter.TicketTopHolder> {

    private Context context;
    private List<String> mList;
    private LayoutInflater inflater;

    public TicketTopAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TicketTopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TicketTopHolder(inflater.inflate(R.layout.item_ticket_top, null));
    }

    @Override
    public void onBindViewHolder(TicketTopHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class TicketTopHolder extends RecyclerView.ViewHolder {

        public TicketTopHolder(View itemView) {
            super(itemView);
        }
    }
}