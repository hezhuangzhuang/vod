package com.zxwl.vod.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxwl.vod.R;
import com.zxwl.vod.adapter.DestinaTionScenicAdapter;

/**
 *
 */
public class TravelDestinaTionScenicFragment extends BaseScrollAbleFragment {
    RecyclerView recyclerView;
    private DestinaTionScenicAdapter mAdapter;

    public TravelDestinaTionScenicFragment() {
    }

    public static TravelDestinaTionScenicFragment newInstance() {
        TravelDestinaTionScenicFragment fragment = new TravelDestinaTionScenicFragment();
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
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new DestinaTionScenicAdapter(getActivity());
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
