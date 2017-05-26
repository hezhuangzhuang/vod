package com.zxwl.vod.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxwl.vod.R;
import com.zxwl.vod.activity.InstallActivity;
import com.zxwl.vod.activity.MyFavoriteActivity;


/**
 * 主页面第四个tab页面
 */
public class HomeFourFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvInformation;
    private TextView tvMyTicket;
    private TextView tvFunding;
    private TextView tvRoute;
    private TextView tvInstall;
    private TextView tvFeedback;
    private TextView tvCollection;

    public static HomeFourFragment newInstance() {
        HomeFourFragment mine = new HomeFourFragment();
        return mine;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected void findViews(View view) {
        tvInformation = (TextView) view.findViewById(R.id.tv_information);
        tvMyTicket = (TextView) view.findViewById(R.id.tv_my_ticket);
        tvFunding = (TextView) view.findViewById(R.id.tv_funding);
        tvRoute = (TextView) view.findViewById(R.id.tv_route);
        tvInstall = (TextView) view.findViewById(R.id.tv_install);
        tvFeedback = (TextView) view.findViewById(R.id.tv_feedback);
        tvCollection = (TextView) view.findViewById(R.id.tv_collection);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void addListeners() {
        tvInformation.setOnClickListener(this);
        tvMyTicket.setOnClickListener(this);
        tvFunding.setOnClickListener(this);
        tvRoute.setOnClickListener(this);
        tvInstall.setOnClickListener(this);
        tvFeedback.setOnClickListener(this);
        tvCollection.setOnClickListener(this);
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_install:
                InstallActivity.startActivity(getActivity());
                break;

            case R.id.tv_collection:
                MyFavoriteActivity.startActivity(getActivity());
                break;
        }


    }
}
