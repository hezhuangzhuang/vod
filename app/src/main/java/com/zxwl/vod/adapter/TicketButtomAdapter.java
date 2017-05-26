package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.zxwl.vod.R;

import java.util.List;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/9 14:34
 * ClassName: ${Class_Name}
 */

public class TicketButtomAdapter extends RecyclerView.Adapter<TicketButtomAdapter.TicketButtomHolder> {
    private Context context;
    private List<String> mList;
    private LayoutInflater inflater;

    public TicketButtomAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TicketButtomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 这里inflater.inflate()方法一定要写三个参数的！不然不能实现item子项宽度match_parent！
         */
        View view = inflater.inflate(R.layout.item_ticket_buttom, parent, false);
        TicketButtomHolder holder = new TicketButtomHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TicketButtomHolder holder, int position) {
//        holder.content.setText(mList.get(position));
        holder.rlContent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class TicketButtomHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlContent;

        public TicketButtomHolder(View itemView) {
            super(itemView);
            rlContent = (RelativeLayout) itemView.findViewById(R.id.rl_content);
        }
    }
}
