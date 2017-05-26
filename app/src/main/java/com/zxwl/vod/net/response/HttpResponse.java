package com.zxwl.vod.net.response;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/10 08:54
 * ClassName: ${Class_Name}
 */

public class HttpResponse<T> {
    //响应码
    public String result;

    //响应消息
    public String message;

    //具体数据
    public T data;

//    public T dataList;

    public static final int SUCCEED_CODE = 0;



}
