package com.zxwl.vod.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxwl.vod.R;
import com.zxwl.vod.adapter.TicketAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面第三个tab
 */
public class HomeThreeFragment extends BaseFragment {
    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;


    private RecyclerView rvList;
    private TicketAdapter mAdapter;
    private List<String> list = new ArrayList<>();

    public HomeThreeFragment() {}

    public static HomeThreeFragment newInstance() {
        HomeThreeFragment ticket = new HomeThreeFragment();
        return ticket;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_home_three, container, false);
    }

    @Override
    protected void findViews(View view) {
        rvList = (RecyclerView) view.findViewById(R.id.rv_top);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_layout);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.appBarLayout);
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    @Override
    protected void init() {
        mAdapter = new TicketAdapter(getActivity());
        rvList.setLayoutManager(new GridLayoutManager(rvList.getContext(), 6, GridLayoutManager.VERTICAL, false));
        rvList.setAdapter(mAdapter);

        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvList.setAdapter(mAdapter);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void lazyFetchData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        rvList.setLayoutManager(new GridLayoutManager(rvList.getContext(), 6, GridLayoutManager.VERTICAL, false));
        mAdapter = new TicketAdapter(getActivity());
        rvList.setAdapter(mAdapter);
    }


}
