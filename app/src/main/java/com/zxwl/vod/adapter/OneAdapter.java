package com.zxwl.vod.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zxwl.vod.R;
import com.zxwl.vod.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longhy on 2016/5/3.
 */
public class OneAdapter extends RecyclerView.Adapter<OneAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    public OneAdapter(Context context){
     this.context = context;
    }

    public void onReference(List<String> list1){
       if(list1 != null){
           list.clear();
           list.addAll(list1);
           notifyDataSetChanged();
       }
    }

    public void addOnReference(List<String> list1){
        if(list1 != null){
            list.addAll(list1);
            notifyDataSetChanged();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader.load(context,list.get(position),holder.iv_adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_adapter;
        public ViewHolder(View view) {
            super(view);
            iv_adapter = (ImageView) view.findViewById(R.id.iv_adapter);
        }
    }
}
