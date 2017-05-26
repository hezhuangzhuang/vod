package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zxwl.vod.R;
import com.zxwl.vod.net.api.Urls;

import java.util.regex.Pattern;

/**
 * 设置BaseUrl
 */
public class SetBaseUrlActivity extends BaseActivity {
    private TextView tvTopTitle;
    private EditText etContent;
    private TextView tvOk;

    private String baseUrl;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SetBaseUrlActivity.class));
    }

    @Override
    protected void initView() {
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        etContent = (EditText) findViewById(R.id.et_content);
        tvOk = (TextView) findViewById(R.id.tv_ok);
    }

    @Override
    protected void initData() {
        tvTopTitle.setText("设置BaseUrl");
    }

    @Override
    protected void setListener() {
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否含有输入URL
                baseUrl = etContent.getText().toString();
                if (TextUtils.isEmpty(baseUrl)) {
                    return;
                }

                Pattern pattern1 = Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
                //判断是否是网址的正则
                Pattern pattern2 = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
                if(pattern1.matcher(baseUrl).matches()){
                    Urls.baseUrl = baseUrl;
                    WelcomActivity.startActivity(SetBaseUrlActivity.this);
                    return;
                }
                Toast.makeText(SetBaseUrlActivity.this, "您输入的不是网址，请从新输入", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_base_url;
    }

}
