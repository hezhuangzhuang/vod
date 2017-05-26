package com.zxwl.vod.net.transformer;

import android.text.TextUtils;

import com.zxwl.vod.net.exception.ExceptionHandle;
import com.zxwl.vod.net.exception.ServerException;
import com.zxwl.vod.net.response.HttpResponse;

import rx.Observable;
import rx.functions.Func1;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/10 08:59
 * ClassName: ${Class_Name}
 */

public class ErrorTransformer<T> implements Observable.Transformer<HttpResponse<T>, T> {
    @Override
    public Observable<T> call(Observable<HttpResponse<T>> httpResponseObservable) {
        return httpResponseObservable.map(new Func1<HttpResponse<T>, T>() {
            @Override
            public T call(HttpResponse<T> response) {
                //如果不为空则代表请求失败
                if(!TextUtils.isEmpty(response.result)){
                    throw new ServerException(response.message, 0x111);
                }

//                //如果不等于成功码
//                if (HttpResponse.SUCCEED_CODE != response.error) {
//                    //如果服务端有错误信息，那么抛出异常，让下面的方法去捕获异常做统一处理
//                    throw new ServerException(response.message, response.error);
//                }
                //服务器请求数据成功,返回里面的实体
                return response.data;
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {
                throwable.printStackTrace();
                return Observable.error(ExceptionHandle.handleException(throwable));
            }
        });
    }

    public static <T> ErrorTransformer<T> create(){
        return new ErrorTransformer<>();
    }

    private static volatile ErrorTransformer instance = null;

    private ErrorTransformer(){}

    /**
     * 双重锁校验单例
     */
    public static <T> ErrorTransformer<T> getInstance(){
        if (null==instance) {
            synchronized (ErrorTransformer.class){
                if(null==instance){
                    instance = new ErrorTransformer();
                }
            }
        }
        return instance;
    }

}
