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
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    private Context context;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    private int[] drawables = {
            R.drawable.test_home_1,
            R.drawable.test_home_2,
            R.drawable.test_home_3,
            R.drawable.test_home_4,
            R.drawable.test_home_5,
            R.drawable.test_home_6,
            R.drawable.test_home_7,
            R.drawable.test_home_8,
            R.drawable.test_home_9,
            R.drawable.test_home_10
    };


    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 12, holder.ivCover);

    }

    @Override
    public int getItemCount() {
        return drawables.length;
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;

        public HistoryHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
        }
    }
}
