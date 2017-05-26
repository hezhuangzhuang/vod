package com.zxwl.vod.bean;

import java.io.Serializable;


//视频实体类
public class VideoBean implements Serializable {
    private static final long serialVersionUID = 2915617478020450322L;


//    public int id;//ID
//    public String name;//名称
//    public String intro;//简介
//    public Date editTime;//剪辑编辑时间？上传时间？
//    public int categoryId;//分类ID（字典表）
//    public String categoryName;//分类名称
//    public int isLive;//是否直播？
//    public String url;//地址（系统处理后的目录与文件名？）
//    public String oriUrl;//源地址（上传时的目录与文件名？）
//    public String thumbnailUrl;//缩略图地址
//    public int videoStateId;//状态ID(字典表)
//    public int playTimes;//播放次数
//    public int avgScore;//平均评分
//    public String remark;//备注(冗余字段)

//    public String id;//ID
//    public String name;//名称
//    public String intro;//简介
////    public Date editTime;//剪辑编辑时间？上传时间？
//    public String categoryId;//分类ID（字典表）
//    public String categoryName;//分类名称
//    public String isLive;//是否直播？
//    public String url;//地址（系统处理后的目录与文件名？）
//    public String oriUrl;//源地址（上传时的目录与文件名？）
//    public String thumbnailUrl;//缩略图地址
//    public String videoStateId;//状态ID(字典表)
//    public String playTimes;//播放次数
//    public String avgScore;//平均评分
//    public String remark;//备注(冗余字段)

    public String id;//ID
    public String name;//名称
    public String intro;//简介
    public String editTime;//剪辑编辑时间？上传时间？
    public String categoryId;//分类ID（字典表）
    public String isLive;//是否直播？
    public String url;//地址（系统处理后的目录与文件名？）
    public String oriUrl;//源地址（上传时的目录与文件名？）
    public String thumbnailUrl;//缩略图地址
    public String videoStateId;//状态ID(字典表)
    public String playTimes;//播放次数
    public String avgScore;//平均评分
    public String remark;//备注(冗余字段)
    public String vip;//是否VIP
    public String hot;//是否热门

//    @Override
//    public String toString() {
//        return "VideoBean{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", intro='" + intro + '\'' +
////                ", editTime=" + editTime +
//                ", categoryId='" + categoryId + '\'' +
//                ", categoryName='" + categoryName + '\'' +
//                ", isLive='" + isLive + '\'' +
//                ", url='" + url + '\'' +
//                ", oriUrl='" + oriUrl + '\'' +
//                ", thumbnailUrl='" + thumbnailUrl + '\'' +
//                ", videoStateId='" + videoStateId + '\'' +
//                ", playTimes='" + playTimes + '\'' +
//                ", avgScore='" + avgScore + '\'' +
//                ", remark='" + remark + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", editTime='" + editTime + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", isLive='" + isLive + '\'' +
                ", url='" + url + '\'' +
                ", oriUrl='" + oriUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", videoStateId='" + videoStateId + '\'' +
                ", playTimes='" + playTimes + '\'' +
                ", avgScore='" + avgScore + '\'' +
                ", remark='" + remark + '\'' +
                ", vip='" + vip + '\'' +
                ", hot='" + hot + '\'' +
                '}';
    }
}
