package com.zxwl.vod;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;
import com.zxwl.vod.net.api.Urls;
import com.zxwl.vod.net.config.NetWorkConfiguration;
import com.zxwl.vod.net.http.HttpUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/10 10:29
 * ClassName: ${Class_Name}
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        CrashReport.initCrashReport(getApplicationContext(), "6846a0a73b", true);
//        initOkHttpUtils();

        initHttpUtils();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(6000L, TimeUnit.MILLISECONDS)
                .readTimeout(6000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("Vod"))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }


    private void initOkHttpUtils() {
        /**
         *  网络配置
         */
        NetWorkConfiguration configuration = new NetWorkConfiguration(this)
                .baseUrl(Urls.baseUrl)
                .isCache(false)
                .isDiskCache(false)
                .isMemoryCache(false);
        HttpUtils.setConFiguration(configuration);

    }
}
