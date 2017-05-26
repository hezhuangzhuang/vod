package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zxwl.vod.R;
import com.zxwl.vod.bean.VideoBean;
import com.zxwl.vod.net.api.Urls;
import com.zxwl.vod.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class PullToRefreshAdapter extends RecyclerView.Adapter<PullToRefreshAdapter.ViewListHolder> {

    private Context context;
    private List<VideoBean> list = new ArrayList<>();


    public PullToRefreshAdapter(Context context, List<VideoBean> list) {
        this.context = context;
        this.list = list;
    }

    //     helper.setText(R.id.tv_name, item.name);//名称
//        helper.setText(R.id.tv_describe, item.intro);
//        helper.setText(R.id.tv_number, String.valueOf(item.playTimes));
//        helper.setText(R.id.tv_type, item.categoryName);
//
//        helper.addOnClickListener(R.id.rl_item_video).addOnClickListener(R.id.iv_cover);
//
//
    @Override
    public PullToRefreshAdapter.ViewListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public int getItemCount() {
        return null != list ? list.size() : 0;
    }

    @Override
    public void onBindViewHolder(ViewListHolder holder, final int position) {
        VideoBean bean = list.get(position);

        holder.tvName.setText(bean.name);
        holder.tvDescribe.setText(bean.intro);
        holder.tvNumber.setText(String.valueOf(bean.playTimes));
//        holder.tvType.setText(String.valueOf(bean.categoryName));

        ImageLoader.loadRadiusRoundedCorners(context, Urls.baseUrl + bean.thumbnailUrl, 10, holder.ivCover);
        holder.rlVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemClick(position);
            }
        });
    }

    public class ViewListHolder extends RecyclerView.ViewHolder {
        private ImageView ivCover;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvType;
        private TextView tvNumber;
        private RelativeLayout rlVideo;

        public ViewListHolder(View itemView) {
            super(itemView);
            rlVideo = (RelativeLayout) itemView.findViewById(R.id.rl_item_video);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
        }
    }

    public interface onItemClick {
        public void itemClick(int position);
    }

    private onItemClick onItemClick;

    public void setOnItemClick(onItemClick itemClick) {
        this.onItemClick = itemClick;
    }
}
