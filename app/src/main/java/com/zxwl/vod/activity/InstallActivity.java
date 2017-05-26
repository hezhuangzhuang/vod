package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxwl.vod.R;

public class InstallActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBackOperate;
    private TextView tvTopTitle;

    private TextView tvModifyPwd;
    private TextView tvPlayAndDownload;
    private TextView tvMessagePush;
    private TextView tvClearCache;
    private TextView tvLoginOff;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, InstallActivity.class));
    }

    @Override
    protected void initView() {
        ivBackOperate = (ImageView) findViewById(R.id.iv_back_operate);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);

        tvModifyPwd = (TextView) findViewById(R.id.tv_modify_pwd);
        tvPlayAndDownload = (TextView) findViewById(R.id.tv_play_and_download);
        tvMessagePush = (TextView) findViewById(R.id.tv_message_push);
        tvClearCache = (TextView) findViewById(R.id.tv_clear_cache);
        tvLoginOff = (TextView) findViewById(R.id.tv_login_off);
    }

    @Override
    protected void initData() {
        tvTopTitle.setText("Install");
    }

    @Override
    protected void setListener() {
        tvModifyPwd.setOnClickListener(this);
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
        return R.layout.activity_install;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_operate:
                finish();
                break;

            case R.id.tv_modify_pwd:
                ModifyPwdActivity.startActivity(InstallActivity.this);
                break;

        }
    }
}
