package com.zxwl.vod.fragment;


import android.os.Bundle;
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
public class TravelTarentoShareFragment extends BaseScrollAbleFragment {
    RecyclerView recyclerView;
    private HomeTravelShareAdapter mAdapter;

    public TravelTarentoShareFragment() {
    }

    public static TravelTarentoShareFragment newInstance() {
        TravelTarentoShareFragment fragment = new TravelTarentoShareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_travel_tarento_share, container, false);
    }

    @Override
    protected void findViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
    }

    @Override
    protected void init() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new HomeTravelShareAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public View getScrollableView() {
        return recyclerView;
    }
}
