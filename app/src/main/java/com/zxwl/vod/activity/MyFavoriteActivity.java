package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxwl.vod.R;
import com.zxwl.vod.adapter.MyFavoriteAdapter;

public class MyFavoriteActivity extends BaseActivity {
    private ImageView ivBackOperate;
    private TextView tvTopTitle;

    private RecyclerView rvList;
    private MyFavoriteAdapter mAdapter;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,MyFavoriteActivity.class));
    }

    @Override
    protected void initView() {
        ivBackOperate = (ImageView) findViewById(R.id.iv_back_operate);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void initData() {
        mAdapter= new MyFavoriteAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(mAdapter);

        tvTopTitle.setText("MY FAVORITE");
    }

    @Override
    protected void setListener() {
        ivBackOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_favorite;
    }

}
