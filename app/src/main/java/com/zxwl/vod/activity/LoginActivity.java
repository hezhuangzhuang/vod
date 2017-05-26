package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.zxwl.vod.R;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPwd;
    private TextView tvLogin;
    private TextView tvRegister;
    private TextView tvForgetPassword;
    private TextView tvLookAround;

    private ImageView ivWelcome;
    @Override
    protected void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        tvLookAround = (TextView) findViewById(R.id.tv_look_around);

        ivWelcome = (ImageView) findViewById(R.id.iv_welcome);
    }

    @Override
    protected void initData() {
        StatusBarUtil.setTranslucentForImageView(LoginActivity.this, 0,null);
    }

    @Override
    protected void setListener() {
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            //登录
            case R.id.tv_login:
                MainActivity.startActivity(LoginActivity.this);
                finish();
                break;

            //注册
            case R.id.tv_register:

                break;

            default:
                break;
        }
    }

}
