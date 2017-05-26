package com.zxwl.vod.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxwl.vod.R;
import com.zxwl.vod.adapter.HomeTravelShareAdapter;

/**
 *
 */
public class TravelChildFragment extends BaseFragment {
    RecyclerView rvList;
    private HomeTravelShareAdapter mAdapter;

    public TravelChildFragment() {
    }

    public static TravelChildFragment newInstance() {
        TravelChildFragment ticket = new TravelChildFragment();
        return ticket;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_travel_tarento_share, container, false);
    }

    @Override
    protected void findViews(View view) {
        rvList = (RecyclerView) view.findViewById(R.id.rv_list);
    }

    @Override
    protected void init() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        rvList.setLayoutManager(new GridLayoutManager(rvList.getContext(), 6, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new HomeTravelShareAdapter(getActivity());
        rvList.setAdapter(mAdapter);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void lazyFetchData() {

    }
}
