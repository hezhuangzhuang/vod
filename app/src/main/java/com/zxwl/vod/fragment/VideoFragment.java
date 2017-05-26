package com.zxwl.vod.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zxwl.vod.R;
import com.zxwl.vod.activity.HotPlayActivity;
import com.zxwl.vod.activity.VideoPlayActivity;
import com.zxwl.vod.adapter.HomeVideoAdapter;
import com.zxwl.vod.bean.DataList;
import com.zxwl.vod.bean.VideoBean;
import com.zxwl.vod.net.api.Urls;
import com.zxwl.vod.net.okhttpcallback.JsonGenericsSerializator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * hot fragment
 */
public class VideoFragment extends BaseSuperFragment {
//    RecyclerView recyclerView;
//    private View rootView;
//    private HomeVideoAdapter mAdapter;
//    private int pageSize = 10;
//    private int pageNum = 1;
//
//    List<VideoBean> list = new ArrayList<>();
//
//    private DialogHelper mDialogHelper;
//
//    public VideoFragment() {
//    }
//
//    public static VideoFragment newInstance() {
//        VideoFragment fragment = new VideoFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//        }
//    }
//
//    @Override
//    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
//        return inflater.inflate(R.layout.fragment_video, container, false);
//    }
//
//    @Override
//    protected void findViews(View view) {
//        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
//    }
//
//    @Override
//    protected void init() {
//        mDialogHelper = new DialogHelper(getActivity());
//
//        mAdapter = new HomeVideoAdapter(getActivity(), list);
//        mAdapter.setVideoClick(new HomeVideoAdapter.VideoClick() {
//            @Override
//            public void click(int position) {
//                if (0 == position || 5 == position || 9 == position) {
//                    HotPlayActivity.startActivity(getActivity());
//                    return;
//                }
//                //跳转至视频播放界面
////                VideoPlayActivity.startActivity(getActivity());
//                Intent intent = new Intent(getActivity(),VideoPlayActivity.class);
//                intent.putExtra(VideoPlayActivity.PARAM_URL,Urls.baseUrl+list.get(position).url);
//                intent.putExtra(VideoPlayActivity.PARAM_TITLE,list.get(position).name);
//                startActivity(intent);
//            }
//        });
////        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));
////        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//
//
//        getDataList();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getDataList();
//    }
//
//    @Override
//    protected void addListeners() {
//
//    }
//
//
//    @Override
//    protected void lazyFetchData() {
//
//    }
//
//
//    /**
//     * 获得列表数据
//     */
//    private void getDataList() {
//        mDialogHelper.startProgressDialog();
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("pageSize", String.valueOf(pageSize));
//        params.put("pageNum", String.valueOf(pageNum));
//
//        String url = Urls.baseUrl + Urls.VIDEOACTION_QUERYLIST;
//        OkHttpUtils
//                .post()//
//                .url(url)//
//                .params(params)
//                .build()//
//                .execute(new GenericsCallback<DataList>(new JsonGenericsSerializator()) {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        mDialogHelper.stopProgressDialog();
//
//                        Toast.makeText(getActivity(), "e=" + e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(DataList response, int id) {
//
//                        if (null != response.dataList) {
//                            mDialogHelper.stopProgressDialog();
//
//                            List<VideoBean> newList = response.dataList;
//                            newList.add(0, null);
//                            newList.add(5, null);
//                            newList.add(9, null);
//                            list.clear();
//                            list.addAll(newList);
//
//                            recyclerView.setAdapter(mAdapter);
//                        }
//                    }
//                });
//    }


    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView rvList;

    private View rootView;
    private HomeVideoAdapter mAdapter;
    private int pageSize = 10;
    private int pageNum = 1;

    List<VideoBean> list = new ArrayList<>();

    public VideoFragment() {
    }

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        rvList = (RecyclerView) rootView.findViewById(R.id.rv_list);
        refreshLayout = (TwinklingRefreshLayout) rootView.findViewById(R.id.refreshLayout);
        return rootView;
    }

    @Override
    protected void initData() {
        mAdapter = new HomeVideoAdapter(getActivity(), list);
        mAdapter.setVideoClick(new HomeVideoAdapter.VideoClick() {
            @Override
            public void click(int position) {
                if (0 == position || 5 == position || 9 == position) {
                    HotPlayActivity.startActivity(getActivity());
                    return;
                }
                //跳转至视频播放界面
                Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                intent.putExtra(VideoPlayActivity.PARAM_URL, Urls.baseUrl + list.get(position).url);
                intent.putExtra(VideoPlayActivity.PARAM_TITLE, list.get(position).name);
                startActivity(intent);
            }
        });
        rvList.setLayoutManager(new GridLayoutManager(rvList.getContext(), 6, GridLayoutManager.VERTICAL, false));

        //设置刷新的view
        initRefresh();

        //开始刷新
        refreshLayout.startRefresh();
    }

    /**
     * 获得列表数据
     */
    private void getDataList() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", String.valueOf(pageSize));
        params.put("pageNum", String.valueOf(pageNum));

        String url = Urls.baseUrl + Urls.VIDEOACTION_QUERYLIST;
        OkHttpUtils
                .post()//
                .url(url)//
                .params(params)
                .build()//
                .execute(new GenericsCallback<DataList>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "e=" + e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(DataList response, int id) {
                        if (null != response.dataList) {
                            List<VideoBean> newList = response.dataList;
                            newList.add(0, null);
                            newList.add(5, null);
                            newList.add(9, null);
                            list.clear();
                            list.addAll(newList);

                            rvList.setAdapter(mAdapter);
                        }
                    }
                });
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        //设置刷新的头部
        ProgressLayout progressLayout = new ProgressLayout(getContext());
        refreshLayout.setHeaderView(progressLayout);

        //设置像SwipeRefreshLayout一样的悬浮刷新模式了
        refreshLayout.setFloatRefresh(true);
        //设置是否回弹
        refreshLayout.setEnableOverScroll(false);
        //设置头部高度
        refreshLayout.setHeaderHeight(140);
        //设置头部的最大高度
        refreshLayout.setMaxHeadHeight(240);
        //设置刷新的view
        refreshLayout.setTargetView(rvList);
    }

    private void addListeners() {
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                //刷新数据
                getDataList();
            }
        });
    }

}
