package com.zxwl.vod.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxwl.vod.R;
import com.zxwl.vod.adapter.SearchAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchVideoFragment extends BaseFragment {
    private RecyclerView rvList;
    private SearchAdapter mAdapter;

    public SearchVideoFragment() {
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
        mAdapter = new SearchAdapter(getActivity());
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(mAdapter);
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void lazyFetchData() {

    }

    public static SearchVideoFragment newInstance() {
        SearchVideoFragment fragment = new SearchVideoFragment();
        return fragment;

    }
}
