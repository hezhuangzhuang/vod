package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxwl.vod.adapter.ClassIfyAdapter;
import com.zxwl.vod.R;
import com.zxwl.vod.bean.ClassIfyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择种类
 */
public class ClassIfyActivity extends BaseActivity {
    private ImageView ivBackOperate;
    private TextView tvTopTitle;

    private List<ClassIfyBean> list = new ArrayList<>();
    private ClassIfyAdapter mAdapter;
    private RecyclerView rvList;

    private int[] icons = {
            R.mipmap.icon_recommend,
            R.mipmap.icon_hot,
            R.mipmap.icon_travel,
            R.mipmap.icon_movie,
            R.mipmap.icon_tv_play,
            R.mipmap.icon_variety,
            R.mipmap.icon_manga,
            R.mipmap.icon_information,
            R.mipmap.icon_amusement,
            R.mipmap.icon_amuse,
            R.mipmap.icon_children,
            R.mipmap.icon_subscribe,
    };

    private String[] titles = {
            "Recommend",
            "Hot",
            "Travel",
            "Movie",
            "TV play",
            "Variety",
            "Manga",
            "Information",
            "Amusement",
            "Amuse",
            "Children",
            "Subscribe",
    };

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ClassIfyActivity.class));
    }

    @Override
    protected void initView() {
        ivBackOperate = (ImageView) findViewById(R.id.iv_back_operate);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void initData() {
        tvTopTitle.setText("CLASSIFY");
        setData();
    }

    private void setData() {
        mAdapter = new ClassIfyAdapter(this, icons,titles);
        rvList.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        rvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        ivBackOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter.setItemClick(new ClassIfyAdapter.onItemClick() {
            @Override
            public void click(int position) {
                TravelActivity.startActivity(ClassIfyActivity.this);
            }
        });
    }

    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.activity_class_ify;
    }

}
