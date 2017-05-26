package com.zxwl.vod.net.factory;

import com.google.gson.Gson;
import com.zxwl.vod.net.response.HttpResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/12 上午10:26
 **/
public class ExGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
//    private final Gson gson;
//    private final Type type;
//
//    ExGsonResponseBodyConverter(Gson gson, Type type) {
//        this.gson = gson;
//        this.type = type;
//    }
//
//    @Override
//    public T convert(ResponseBody responseBody) throws IOException {
//        String value = responseBody.string();
//        HttpResponse httpResponse = new HttpResponse();
//        try {
//            JSONObject response = new JSONObject(value);
//            if(response.has("result")){
//                String error = response.getString("result");
//                //不等于0代表异常
//                if (TextUtils.isEmpty(error)) {
//                    httpResponse.result = error;
//                    httpResponse.message = response.getString("message");
//                    return (T) gson.fromJson(value, httpResponse.getClass());
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return gson.fromJson(value, type);
//    }

    public ExGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    private final Gson gson;
    private final Type type;
//    private final TypeAdapter<T> adapter;

//    public ExGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
//        this.gson = gson;
//        this.adapter = adapter;
//    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            //获得返回的json数据
            String body = value.string();
            JSONObject json = new JSONObject(body);
            //创建HttpResponse
            HttpResponse httpResponse = new HttpResponse();
            //查看是否包含result字段，包含代表错误
            if (json.has("result")) {
                String error = json.getString("result");
                //不等于0代表异常
                httpResponse.result = error;
                httpResponse.message = json.getString("message");
                return (T) gson.fromJson(body, httpResponse.getClass());
            } else {
                return gson.fromJson(body,type);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}