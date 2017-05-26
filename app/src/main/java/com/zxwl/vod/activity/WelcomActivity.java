package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.zxwl.vod.R;


/**
 * Created by 余龙 on 2017/4/1.
 */

public class WelcomActivity extends AppCompatActivity {

    static final long DELAY_TIME = 2000;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, WelcomActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        StatusBarUtil.setTranslucent(this, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean hsaLogin = false;
                if (hsaLogin) {
                    MainActivity.startActivity(WelcomActivity.this);
                } else {
                    LoginActivity.startActivity(WelcomActivity.this);
                }
                finish();
            }
        }, DELAY_TIME);
    }
}
