package com.zxwl.vod.adapter;

import com.zxwl.vod.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 蓝色互动. All rights reserved.
 * author：hw
 * data:2017/4/6 17:50
 * ClassName: ${Class_Name}
 * public int id;//ID
 * public String name;//名称
 * public String intro;//简介
 * public Date editTime;//剪辑编辑时间？上传时间？
 * public int categoryId;//分类ID（字典表）
 * public int isLive;//是否直播？
 * public String url;//地址（系统处理后的目录与文件名？）
 * public String oriUrl;//源地址（上传时的目录与文件名？）
 * public String thumbnailUrl;//缩略图地址
 * public int videoStateId;//状态ID(字典表)
 * public int playTimes;//播放次数
 * public int avgScore;//平均评分
 * public String remark;//备注(冗余字段)
 */

public class DataServer {

    public static List<VideoBean> getSampleData(int i) {
        VideoBean videoBean = null;
        List<VideoBean> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            videoBean = new VideoBean();
            videoBean.url = "http://flv2.bn.netease.com/tvmrepo/2016/5/N/3/EBMTJBGN3/SD/EBMTJBGN3-mobile.mp4";
            videoBean.thumbnailUrl = "https://imgsa.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=ca5abb5b7bf0f736ccf344536b3cd87c/29381f30e924b899c83ff41c6d061d950a7bf697.jpg";
            videoBean.name = "这是名字" + j;
            videoBean.intro = "这是简介" + j;
//            videoBean.categoryName = "这是类型" + j;
            videoBean.playTimes = j+"";
            list.add(videoBean);
        }
        return list;
    }
}
