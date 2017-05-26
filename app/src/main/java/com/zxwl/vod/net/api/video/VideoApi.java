package com.zxwl.vod.net.api.video;

import com.zxwl.vod.bean.NewDataList;
import com.zxwl.vod.bean.VideoBean;
import com.zxwl.vod.net.api.Urls;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 请求Video的Api
 */
public interface VideoApi {


//    pageSize=5&pageNum=1

    /**
     * 查询列表
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GET(Urls.VIDEOACTION_QUERYLIST)
    Observable<NewDataList<VideoBean>> getVideoList(@Query("pageSize") int pageSize,
                                                    @Query("pageNum") int pageNum);

    /**
     * 查询单个视频
     *
     * @return
     */
//    @GET(Urls.VIDEOACTION_QUERY_ID)
//    Observable<HttpResponse<VideoBean>> getVideoFormID(@Query("id") int id,String data);
    @GET(Urls.VIDEOACTION_QUERY_ID)
    Observable<VideoBean> getVideoFormID(@Query("id") int id);


//    @GET("data/{tech}/{num}/{page}")
//    Observable<HttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

//    @GET("data/福利/{num}/{page}")
//    Observable<HttpResponse<List<GankItemBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

}
