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
 */
public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //上面的样式
    public static final int TYPE_TOP = 0xff01;
    //下面的样式
    public static final int TYPE_BUTTOM = 0xff02;

    //显示更多的样式
    public static final int TYPE_TOP_MORE = 0xff03;
    //显示更多的样式
    public static final int TYPE_BUTTOM_MORE = 0xff04;

    private Context context;

    private String Img_url_1 = "https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/lvpics/pic/item/a08b87d6277f9e2fabd542f61e30e924b999f368.jpg";
    private String Img_url_2 = "https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/lvpics/pic/item/eac4b74543a98226fbfbd4408b82b9014a90eb9b.jpg";

    private int[] drawables = {
            0,
            R.drawable.test_ticket_1,
            R.drawable.test_ticket_2,
            R.drawable.test_ticket_3,
            R.drawable.test_ticket_4,
            R.drawable.test_ticket_5,
            R.drawable.test_ticket_6,
            0,
            R.drawable.test_ticket_7,
            R.drawable.test_ticket_8,
            R.drawable.test_ticket_9,
    };


    private String[] titles = {
            "",
            "Maynila",
            "Palawan",
            "Negros",
            "Maynila",
            "Palawan",
            "Negros",
            "",
            "Maynila-Palawan  Latest deals",
            "Pandanon Island-Maynila Latest deals",
            "White Island-Palawan  Latest deals"
    };

    public TicketAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TOP:
                return new TicketAdapter.TicketTopHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_top, parent, false));

            case TYPE_BUTTOM:
                return new TicketAdapter.TicketButtomHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_buttom, parent, false));

            case TYPE_TOP_MORE:
            case TYPE_BUTTOM_MORE:
                return new TicketAdapter.TicketMoreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_play_more, parent, false));

            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TicketTopHolder) {//上面样式
            bindTopCover((TicketTopHolder) holder, position);
        } else if (holder instanceof TicketAdapter.TicketButtomHolder) {//底部样式
            bindButtomCover((TicketButtomHolder) holder, position);
        } else if (holder instanceof TicketAdapter.TicketMoreHolder) {//显示更多
            bindHotPlayMore((TicketMoreHolder) holder, position);
        }
    }

    /**
     * 绑定上面样式
     *
     * @param holder
     * @param position
     */
    private void bindTopCover(TicketTopHolder holder, int position) {
        holder.tvTitlte.setText(titles[position]);

        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 10, holder.ivCover);
//        ImageLoader.loadRadiusRoundedCorners(context, Img_url_1, 10, holder.ivCover);
    }

    /**
     * 绑定下面样式
     *
     * @param holder
     * @param position
     */
    private void bindButtomCover(TicketButtomHolder holder, int position) {
        holder.tvTitlte.setText(titles[position]);
        ImageLoader.loadRadiusRoundedCorners(context, drawables[position], 10, holder.ivCover);
//        ImageLoader.loadRadiusRoundedCorners(context, Img_url_2, 10, holder.ivCover);
    }

    private void bindHotPlayMore(TicketMoreHolder holder, int position) {
        if (0 == position) {
            holder.tvHot.setText("Ticket reservation");
            holder.viewTop.setVisibility(View.GONE);
        } else {
            holder.tvHot.setText("Discount ticket");
            holder.viewTop.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //显示加载更多
        if (0 == position) {
            return TYPE_TOP_MORE;
        }//显示2行3列
        else if (position >= 1 && position <= 6) {
            return TYPE_TOP;
        }//显示加载更多
        else if (position == 7) {
            return TYPE_BUTTOM_MORE;
        } else {
            return TYPE_BUTTOM;
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
                        //加载更多
                        case TYPE_TOP_MORE:
                        case TYPE_BUTTOM_MORE:
                        case TYPE_BUTTOM:
                            return gridManager.getSpanCount();

                        //TYPE_TWO_COVER代表一行显示2个，因为外面设置的是显示6个，所以应该返回3
                        case TYPE_TOP:
                            return 2;

                        default:
                            return gridManager.getSpanCount();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 11;
    }

    /**
     * 头部样式
     */
    public static class TicketTopHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitlte;
        public TicketTopHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvTitlte = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    /**
     * 底部样式
     */
    public static class TicketButtomHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitlte;

        public TicketButtomHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvTitlte = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    /**
     * 显示更多样式
     */
    public static class TicketMoreHolder extends RecyclerView.ViewHolder {
        View viewTop;
        private TextView tvHot;

        public TicketMoreHolder(View itemView) {
            super(itemView);
            viewTop = itemView.findViewById(R.id.view_top);
            tvHot = (TextView) itemView.findViewById(R.id.tv_hot);
        }
    }

}
