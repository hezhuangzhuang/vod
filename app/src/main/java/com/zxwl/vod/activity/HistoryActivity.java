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
import com.zxwl.vod.adapter.HistoryAdapter;

public class HistoryActivity extends BaseActivity {

    private ImageView ivBackOperate;
    private TextView tvTopTitle;
    private TextView tvRightOperate;

    private RecyclerView rvList;
    private HistoryAdapter mAdapter;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,HistoryActivity.class));
    }

    @Override
    protected void initView() {
        ivBackOperate = (ImageView) findViewById(R.id.iv_back_operate);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        tvRightOperate = (TextView) findViewById(R.id.tv_right_operate);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void initData() {
        mAdapter= new HistoryAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(mAdapter);

        tvTopTitle.setText("HISTORY");
        tvRightOperate.setText("Empty");
        tvRightOperate.setVisibility(View.VISIBLE);
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
