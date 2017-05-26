package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * data:2017/4/8 14:32
 * ClassName: ${Class_Name}
 */

public class HomeTravelShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    //POPULAR
    public static final int TYPE_POPULAR_MORE = 0xff02;
    //NEW_VIDEO
    public static final int TYPE_NEW_VIDEO_MORE = 0xff03;
    //TV_PLAY
    public static final int TYPE_TV_PLAY_MORE = 0xff04;

    //一行显示2列的
    public static final int TYPE_TWO_COVER = 0xff06;


    public static final String img_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491813001031&di=59deb7e8e717bceb711a04f2ff6a8062&imgtype=0&src=http%3A%2F%2Ffile30.mafengwo.net%2FM00%2F58%2FE7%2FwKgBpVU7RAOAYkgyAAqTq06DZG833.jpeg";
    private static final String head_url = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-30-17265582_1877445642507654_3057988544061505536_n.jpg";

    private int[] drawables = {
            0,
            R.drawable.test_share_1,
            R.drawable.test_share_2,
            0,
            R.drawable.test_share_3,
            R.drawable.test_share_4,
            R.drawable.test_share_5,
            R.drawable.test_share_6
    };


    private String[] titles = {
            "",
            "Henry",
            "Henderson",
            "",
            "Hayes",
            "Hawkins",
            "Harvey",
            "Hart"
    };


    public HomeTravelShareAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_POPULAR_MORE:
                return new HotPlayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            case TYPE_NEW_VIDEO_MORE:
                return new HotPlayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            case TYPE_TV_PLAY_MORE:
                return new HotPlayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            case TYPE_TWO_COVER:
                return new CoverHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel_share, parent, false));

            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CoverHolder) {
            bindCover((CoverHolder) holder, position);
        } else {
            bindHotPlayMore((HotPlayHolder) holder, position);
        }
    }

    private void bindCover(CoverHolder holder, int position) {
        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 10, holder.ivCover);
        ImageLoader.loadCircle(context, head_url, holder.ivHead);
        holder.tvName.setText(titles[position]);

    }

    private void bindHotPlayMore(HotPlayHolder holder, int position) {
        holder.tvHot.setText("Popular recommend");
        if (0 == position) {
            holder.viewTop.setVisibility(View.GONE);
        } else {
            holder.viewTop.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return drawables.length;
    }

    @Override
    public int getItemViewType(int position) {
        //显示加载更多
        if (position == 0) {
            return TYPE_POPULAR_MORE;
        }
        //显示一行两列
        else if (position >= 1 && position <= 2) {
            return TYPE_TWO_COVER;
        }
        //显示加载更多
        if (position == 3) {
            return TYPE_POPULAR_MORE;
        }
        //显示两行两列
        else if (position >= 4 && position <= 7) {
            return TYPE_TWO_COVER;
        } else {
            return TYPE_TWO_COVER;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);

            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                //返回的数字代表用多少行来显示
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        //三个加载更多
                        case TYPE_POPULAR_MORE:
                        case TYPE_NEW_VIDEO_MORE:
                            //返回6代表用6行来显示，也就是一行显示一个
                        case TYPE_TV_PLAY_MORE:
                            return gridManager.getSpanCount();

                        //TYPE_TWO_COVER代表一行显示2个，因为外面设置的是显示6个，所以应该返回3
                        case TYPE_TWO_COVER:
                            return 3;
                        default:
                            return 2;
                    }
                }
            });

        }


    }

    /**
     * 封面的Cover
     */
    public class CoverHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        ImageView ivHead;
        TextView tvName;

        public CoverHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            ivHead = (ImageView) itemView.findViewById(R.id.iv_head);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    //TYPE_POPULAR_MORE
    //TYPE_NEW_VIDEO_MORE
    //TYPE_TV_PLAY_MORE

    public class HotPlayHolder extends RecyclerView.ViewHolder {
        View viewTop;
        TextView tvHot;

        public HotPlayHolder(View itemView) {
            super(itemView);
            viewTop = itemView.findViewById(R.id.view_top);
            tvHot = (TextView) itemView.findViewById(R.id.tv_hot);
        }
    }

    public class NewVideoHolder extends RecyclerView.ViewHolder {
        public NewVideoHolder(View itemView) {
            super(itemView);
        }
    }

    public class TvPlayHolder extends RecyclerView.ViewHolder {
        public TvPlayHolder(View itemView) {
            super(itemView);
        }
    }
}