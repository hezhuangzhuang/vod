package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxwl.vod.R;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/8 14:32
 * ClassName: ${Class_Name}
 */

public class HomeTravelForumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    //显示加载更多
    private static final int TYPE_FORUM_TOP_MORE = 0xff01;

    //顶部样式
    private static final int TYPE_FORUM_TOP = 0xff02;

    //底部样式
    private static final int TYPE_TRAVEL_BOTTOM = 0xff03;

    private static final int TYPE_TRAVEL_BUTTOM_MORE = 0xff04;


    public HomeTravelForumAdapter(Context context) {
        this.context = context;
    }

    public class TvPlayHolder extends RecyclerView.ViewHolder {
        public TvPlayHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //显示加载更多
        if (0 == position) {
            return TYPE_FORUM_TOP_MORE;
        }//显示顶部样式
        else if (position >= 1 && position <= 4) {
            return TYPE_FORUM_TOP;
        }//显示底部样式
        else if (5 == position) {
            return TYPE_TRAVEL_BUTTOM_MORE;
        }//显示底部样式
        else if (position >= 6) {
            return TYPE_TRAVEL_BOTTOM;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TRAVEL_BUTTOM_MORE:
            case TYPE_FORUM_TOP_MORE:
                return new ForumMoreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            case TYPE_FORUM_TOP:
                return new ForumTopHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarento_forum_top, parent, false));

            case TYPE_TRAVEL_BOTTOM:
                return new TraveButtomHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarento_forum_buttom, parent, false));

            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TraveButtomHolder) {//
            bindTraveButtom((TraveButtomHolder) holder, position);
        }//加载更多
        else if (holder instanceof ForumMoreHolder) {
            bindMore((ForumMoreHolder) holder, position);
        }
    }

    /**
     * 绑定加载更多的样式
     * @param holder
     * @param position
     */
    private void bindMore(ForumMoreHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_FORUM_TOP_MORE:
                holder.viewTop.setVisibility(View.GONE);
                holder.tvMore.setVisibility(View.VISIBLE);
                break;

            case TYPE_TRAVEL_BUTTOM_MORE:
                holder.viewTop.setVisibility(View.VISIBLE);
                holder.tvMore.setVisibility(View.GONE);
                break;
        }
    }

    private void bindTraveButtom(TraveButtomHolder holder, int position) {
        //获得里面的文本
        CharSequence oldString = holder.tvTitle.getText().toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(oldString);
        //设置要替换的文本
        spannableStringBuilder.setSpan(new ImageSpan(context, R.mipmap.icon_question_mark), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tvTitle.setText(spannableStringBuilder);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ForumMoreHolder extends RecyclerView.ViewHolder {
        View viewTop;
        TextView tvMore;

        public ForumMoreHolder(View itemView) {
            super(itemView);
            viewTop = itemView.findViewById(R.id.view_top);
            tvMore  = (TextView) itemView.findViewById(R.id.tv_more);
        }

    }

    public class ForumTopHolder extends RecyclerView.ViewHolder {
        public ForumTopHolder(View itemView) {
            super(itemView);
        }
    }

    public class TraveButtomHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public TraveButtomHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}

