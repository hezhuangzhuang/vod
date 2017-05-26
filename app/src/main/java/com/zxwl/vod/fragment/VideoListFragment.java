package com.zxwl.vod.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zxwl.vod.R;
import com.zxwl.vod.adapter.PullToRefreshAdapter;
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
 * 视频列表的fragment
 */
public class VideoListFragment extends BaseSuperFragment {
    //    private RecyclerView rvList;
//    private PullToRefreshAdapter mAdapter;
//    private List<VideoBean> list = new ArrayList<>();
//
//    private int DATA_SIZE = 4;
//    private long delayMillis = 1000;
//    private int currentIndex = 0;
//    private int ROW_SUM = 10;//数据所有的条数
//    private int PAGE_SIZE = 5;
//    private int PAGE_NUM = 0;
//
//
//    private TwinklingRefreshLayout refreshLayout;
//
//    public VideoListFragment() {
//    }
//
//    public static VideoListFragment newInstance(String param1, String param2) {
//        VideoListFragment fragment = new VideoListFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
//        return inflater.inflate(R.layout.fragment_list, container, false);
//    }
//
//    @Override
//    protected void findViews(View view) {
//        rvList = (RecyclerView) view.findViewById(R.id.rv_list);
//        refreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.refreshLayout);
//    }
//
//    @Override
//    protected void init() {
//        mAdapter = new PullToRefreshAdapter(getActivity(), list);
//        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvList.setAdapter(mAdapter);
//
//        //设置刷新的头部
//        ProgressLayout progressLayout = new ProgressLayout(getContext());
//        refreshLayout.setHeaderView(progressLayout);
//        //设置加载的底部
//        LoadingView loadingView = new LoadingView(getContext());
//        refreshLayout.setBottomView(loadingView);
//
//        //设置像SwipeRefreshLayout一样的悬浮刷新模式了
//        refreshLayout.setFloatRefresh(true);
//        //设置是否回弹
//        refreshLayout.setEnableOverScroll(false);
//        //设置头部高度
//        refreshLayout.setHeaderHeight(140);
//        //设置头部的最大高度
//        refreshLayout.setMaxHeadHeight(240);
//        //设置刷新的view
//        refreshLayout.setTargetView(rvList);
////        refreshLayout.setAutoLoadMore(true);
//
//        //设置点击事件
//        mAdapter.setOnItemClick(new PullToRefreshAdapter.onItemClick() {
//            @Override
//            public void itemClick(int position) {
//                switchPlayListener.switchPlay(Urls.baseUrl + list.get(position).url, list.get(position).name);
//            }
//        });
//
//        //获得列表数据
//        refreshLayout.startRefresh();
//    }
//
//    @Override
//    protected void addListeners() {
//        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
//            @Override
//            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
//                //刷新数据
//                getData(PAGE_SIZE, 1);
//            }
//
//            @Override
//            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
//                //判断当前条数是否大于数据总条数
//                //结束加载更多，需手动调用
//                getData(PAGE_SIZE, PAGE_NUM + 1);
//            }
//        });
//    }
//
//    @Override
//    protected void lazyFetchData() {
//
//    }
//
//    /**
//     * 获得列表数据
//     */
//    private void getData(int pageSize, final int pageNum) {
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
//                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(DataList response, int id) {
//                        //成功则代表请求到数据
//                        if (null != response.dataList) {
//                            //1为刷新，否则为加载更多
//                            if (1 == pageNum) {
//                                PAGE_NUM = 1;
//                                list.clear();
//                                list.addAll(response.dataList);
//                                mAdapter.notifyDataSetChanged();
//                                refreshLayout.finishRefreshing();
//                                //刷新的时候使加载更多可以使用
//                                refreshLayout.setEnableLoadmore(true);
//                            } else {
//                                PAGE_NUM++;
//                                list.addAll(response.dataList);
//                                mAdapter.notifyDataSetChanged();
//                                refreshLayout.finishLoadmore();
//                            }
//                            //如果当前条数大于或等于总条数则禁用加载更多
//                            if (list.size() >= response.rowSum) {
//                                refreshLayout.setEnableLoadmore(false);
//                            }
//                        }
//                    }
//                });
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof onSwitchPlayListener) {
//            switchPlayListener = (onSwitchPlayListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    public onSwitchPlayListener switchPlayListener;
//
//    public interface onSwitchPlayListener {
//        void switchPlay(String url, String title);
//
//    }
//
//    public void setOnSwitchPlayListener(onSwitchPlayListener switchPlayListener) {
//        this.switchPlayListener = switchPlayListener;
//    }

    private PullToRefreshAdapter mAdapter;
    private List<VideoBean> list = new ArrayList<>();

    private int DATA_SIZE = 4;
    private long delayMillis = 1000;
    private int currentIndex = 0;
    private int ROW_SUM = 10;//数据所有的条数
    private int PAGE_SIZE = 5;
    private int PAGE_NUM = 0;

    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView rvList;
    public VideoListFragment() {
    }

    public static VideoListFragment newInstance(String param1, String param2) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获得列表数据
     */
    private void getData(int pageSize, final int pageNum) {
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
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(DataList response, int id) {
                        //成功则代表请求到数据
                        if (null != response.dataList) {
                            //1为刷新，否则为加载更多
                            if (1 == pageNum) {
                                PAGE_NUM = 1;
                                list.clear();
                                list.addAll(response.dataList);
                                mAdapter.notifyDataSetChanged();
                                refreshLayout.finishRefreshing();
                                //刷新的时候设置加载更多可以使用
                                refreshLayout.setEnableLoadmore(true);
                            } else {
                                PAGE_NUM++;
                                list.addAll(response.dataList);
                                mAdapter.notifyDataSetChanged();
                                refreshLayout.finishLoadmore();
                            }
                            //如果当前条数大于或等于总条数则禁用加载更多
                            if (list.size() >= response.rowSum) {
                                refreshLayout.setEnableLoadmore(false);
                            }
                        }
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSwitchPlayListener) {
            switchPlayListener = (onSwitchPlayListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public onSwitchPlayListener switchPlayListener;

    public interface onSwitchPlayListener {
        void switchPlay(String url, String title);
    }

    public void setOnSwitchPlayListener(onSwitchPlayListener switchPlayListener) {
        this.switchPlayListener = switchPlayListener;
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
        mAdapter = new PullToRefreshAdapter(getActivity(), list);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(mAdapter);

        //初始化刷新控件
        initRefresh();

        //设置自动加载更多
//        refreshLayout.setAutoLoadMore(true);

        //设置点击事件
        mAdapter.setOnItemClick(new PullToRefreshAdapter.onItemClick() {
            @Override
            public void itemClick(int position) {
                switchPlayListener.switchPlay(Urls.baseUrl + list.get(position).url, list.get(position).name);
            }
        });

        //添加监听
        addListeners();

        //获得列表数据
        refreshLayout.startRefresh();
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        //设置刷新的头部
        ProgressLayout progressLayout = new ProgressLayout(getContext());
        refreshLayout.setHeaderView(progressLayout);
        //设置加载的底部
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);

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
                getData(PAGE_SIZE, 1);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                //判断当前条数是否大于数据总条数
                //结束加载更多，需手动调用
                getData(PAGE_SIZE, PAGE_NUM + 1);
            }
        });
    }
}
