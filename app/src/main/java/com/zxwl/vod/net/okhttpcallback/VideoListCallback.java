package com.zxwl.vod.net.okhttpcallback;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;
import com.zxwl.vod.bean.DataList;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class VideoListCallback extends Callback<DataList> {

    @Override
    public DataList parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        DataList videoBeanList = new Gson().fromJson(string, DataList.class);
        return videoBeanList;
    }

}
