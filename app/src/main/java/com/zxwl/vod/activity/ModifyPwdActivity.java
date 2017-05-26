package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxwl.vod.R;

public class ModifyPwdActivity extends BaseActivity {
    private ImageView ivBackOperate;
    private TextView tvTopTitle;

    @Override
    protected void initView() {
        ivBackOperate = (ImageView) findViewById(R.id.iv_back_operate);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);
    }

    @Override
    protected void initData() {
        tvTopTitle.setText("MODIFY PASSWORD");
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
        return R.layout.activity_modify_pwd;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ModifyPwdActivity.class));
    }
}
