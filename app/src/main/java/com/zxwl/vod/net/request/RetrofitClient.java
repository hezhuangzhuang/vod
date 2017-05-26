package com.zxwl.vod.net.request;

import com.zxwl.vod.net.factory.ExGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 *
 */
public class RetrofitClient {
    private static OkHttpClient okHttpClient;

    //baseUrl
    private static String baseUrl;

    private static Retrofit retrofit;

    /**
     * 设置baseUrl和okhttpclient
     *
     * @param baseUrl
     * @param okHttpClient
     */
    public RetrofitClient(String baseUrl, OkHttpClient okHttpClient) {
        this.baseUrl = baseUrl;
        this.okHttpClient = okHttpClient;
    }

    public RetrofitClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * 获得对应的Apiservice对象
     */
    public <T> T builder(Class<T> service) {
        if (baseUrl == null) {
            throw new RuntimeException("baseUrl is null!");
        }
        if (service == null) {
            throw new RuntimeException("api Service is null!");
        }

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(ExGsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

}
