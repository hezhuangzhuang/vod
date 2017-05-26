package com.zxwl.vod.bean;

import java.util.List;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/14 13:03
 * ClassName: ${Class_Name}
 */

public class NewDataList<T> {
    public List<T> dataList;

    public int pageNum;

    public int pageSize;

    public int pageSum;

    public int rowSum;

    public String message;

    public String result;
}
