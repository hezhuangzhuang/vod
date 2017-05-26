package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zxwl.vod.R;
import com.zxwl.vod.bean.VideoBean;
import com.zxwl.vod.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/7 22:17
 * ClassName: ${Class_Name}
 */

public class HomeVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    //HOT_PLAY
    public static final int TYPE_HOT_PLAY = 0xff02;
    //NEW_VIDEO
    public static final int TYPE_NEW_VIDEO = 0xff03;
    //TV_PLAY
    public static final int TYPE_TV_PLAY = 0xff04;

    //大封面
    public static final int TYPE_BIG_COVER = 0xff05;

    //一行显示2列的
    public static final int TYPE_TWO_COVER = 0xff06;

    //一行显示2列并且有margin的
    public static final int TYPE_TWO_MARGIN_COVER = 0xff08;

    //一行显示3列的
    public static final int TYPE_THREE_COVER = 0xff07;

    public final static String img_1 = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-06-17493825_1061197430652762_1457834104966873088_n.jpg";
    public final static String img_2 = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-04-17438270_1418311001574160_8728796670000627712_n.jpg";

    private int[] drawables = {
            0,
            R.drawable.test_home_1,
            R.drawable.test_home_2,
            R.drawable.test_home_3,
            R.drawable.test_home_4,
            0,
            R.drawable.test_home_5,
            R.drawable.test_home_6,
            R.drawable.test_home_7,
            0,
            R.drawable.test_home_8,
            R.drawable.test_home_9,
            R.drawable.test_home_10
    };

    private VideoClick videoClick;

    public void setVideoClick(VideoClick click) {
        this.videoClick = click;
    }

    public interface VideoClick {
        void click(int position);
    }

    private List<VideoBean> beanList = new ArrayList<>();

    public HomeVideoAdapter(Context context, List<VideoBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    public HomeVideoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HOT_PLAY:
                return new HotPlayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            case TYPE_NEW_VIDEO:
                return new HotPlayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            case TYPE_TV_PLAY:
                return new HotPlayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            case TYPE_BIG_COVER:
                return new CoverHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_one_aover, parent, false));

            case TYPE_TWO_COVER:
                return new CoverHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_two_aover, parent, false));

            case TYPE_TWO_MARGIN_COVER:
                return new CoverHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_two_aover, parent, false));

            case TYPE_THREE_COVER:
                return new CoverThreeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_three_aover, parent, false));

            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (0 == position || 5 == position || 9 == position) {
            if (holder instanceof HotPlayHolder) {
                bindHotPlayMore((HotPlayHolder) holder, position);
            }
        } else if (holder instanceof CoverHolder) {
            bindCover((CoverHolder) holder, position);
        } else if (holder instanceof CoverThreeHolder) {
            bindThreeCover((CoverThreeHolder) holder, position);
        }
//        else if (holder instanceof HotPlayHolder) {
//            bindHotPlayMore((HotPlayHolder) holder, position);
//        }
    }

    private void bindThreeCover(CoverThreeHolder holder, final int position) {
//        ImageLoader.loadRadiusRoundedCorners(context, Urls.baseUrl + beanList.get(position).thumbnailUrl, 10, holder.ivCover);

        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 10, holder.ivCover);

//        ImageLoader.load(context, beanList.get(position).thumbnailUrl, holder.ivCover);
        holder.tvTitle.setText(beanList.get(position).name);
        holder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoClick.click(position);
            }
        });
    }

    private void bindCover(CoverHolder holder, final int position) {
//        ImageLoader.load(context, beanList.get(position).thumbnailUrl, holder.ivCover);
//        ImageLoader.loadRadiusRoundedCorners(context, Urls.baseUrl + beanList.get(position).thumbnailUrl, 10, holder.ivCover);
        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 10, holder.ivCover);

        holder.tvTitle.setText(beanList.get(position).name);
        holder.tvDescribe.setText(beanList.get(position).intro);
        holder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoClick.click(position);
            }
        });
    }

    private void bindHotPlayMore(HotPlayHolder holder, final int position) {
        if (0 == position) {
            holder.viewTop.setVisibility(View.GONE);
        } else {
            holder.viewTop.setVisibility(View.VISIBLE);
        }
        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoClick.click(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 13;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HOT_PLAY;
        }
        //显示两行两列
        else if (position >= 1 && position <= 2) {
            return TYPE_TWO_COVER;
        }
        //显示两行两列
        else if (position >= 3 && position <= 4) {
            return TYPE_TWO_MARGIN_COVER;
        }//显示加载更多
        else if (position == 5) {
            return TYPE_NEW_VIDEO;
        }//显示一大二小
        else if (position == 6) {
            return TYPE_BIG_COVER;
        }//显示一大两小的两小
        else if (position >= 7 && position <= 8) {
            return TYPE_TWO_MARGIN_COVER;
        }//显示加载更多
        else if (position == 9) {
            return TYPE_TV_PLAY;
        }//显示一行三列
        else if (position >= 10 && position <= 12) {
            return TYPE_THREE_COVER;
        } else {
            return TYPE_THREE_COVER;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);

            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                //返回的数字代表显示多少行
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        //显示大封面
                        case TYPE_BIG_COVER:
                            //三个加载更多
                        case TYPE_HOT_PLAY:
                        case TYPE_NEW_VIDEO:
                        case TYPE_TV_PLAY:
                            return gridManager.getSpanCount();

                        //TYPE_TWO_COVER代表一行显示2个，因为外面设置的是显示6个，所以应该返回3
                        case TYPE_TWO_COVER:
                        case TYPE_TWO_MARGIN_COVER:
                            return 3;

                        //TYPE_THREE_COVER代表一行显示3个，因为外面设置的是显示6个，所以应返回2，代表显示2行
                        case TYPE_THREE_COVER:
                            return 2;
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
    public static class CoverHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle;
        TextView tvDescribe;

        public CoverHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
        }
    }

    /**
     * 底部一行三列的适配器
     */
    public static class CoverThreeHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle;

        public CoverThreeHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }


    public static class HotPlayHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlContent;
        View viewTop;

        public HotPlayHolder(View itemView) {
            super(itemView);
            rlContent = (RelativeLayout) itemView.findViewById(R.id.rl_content);
            viewTop = itemView.findViewById(R.id.view_top);
        }
    }

    public static class NewVideoHolder extends RecyclerView.ViewHolder {
        public NewVideoHolder(View itemView) {
            super(itemView);
        }
    }

    public static class TvPlayHolder extends RecyclerView.ViewHolder {
        public TvPlayHolder(View itemView) {
            super(itemView);
        }
    }

    public void setNewData(List<VideoBean> list) {
        beanList.clear();
        beanList.addAll(list);
        notifyDataSetChanged();
    }

}
