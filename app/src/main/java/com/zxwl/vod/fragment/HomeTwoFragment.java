package com.zxwl.vod.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxwl.vod.R;
import com.zxwl.vod.activity.SearchActivity;
import com.zxwl.vod.adapter.FragmentViewPagerAdapter;

import java.util.ArrayList;

/**
 * 主界面第二个tab页面
 */
public class HomeTwoFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvHomePage;
    private TextView tvDestination;
    private TextView tvTarento;

    private ImageView ivSearch;
    private View line;
    private ViewPager vpContent;

    private int bmpW;// 动画图片宽度
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号

    private FragmentViewPagerAdapter mAdapter;

    private String[] titles = {
            "HOME PAGE",
            "DESTINATION",
            "TARENTO"
    };

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public HomeTwoFragment() {
    }

    public static HomeTwoFragment newInstance() {
        HomeTwoFragment fragment = new HomeTwoFragment();
        return fragment;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_two, container, false);
    }

    @Override
    protected void findViews(View view) {
        tvHomePage = (TextView) view.findViewById(R.id.tv_home_page);
        tvDestination = (TextView) view.findViewById(R.id.tv_destination);
        tvTarento = (TextView) view.findViewById(R.id.tv_tarento);
        ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        line = (View) view.findViewById(R.id.line);
        vpContent = (ViewPager) view.findViewById(R.id.vp_content);
    }

    @Override
    protected void init() {
        //初始化底部横线的位置
        InitImageView();
        fragments.add(TravelHomepageFragment.newInstance());
        fragments.add(TravelDestinaTionFragment.newInstance());
        fragments.add(TravelTarentoFragment.newInstance());

        mAdapter = new FragmentViewPagerAdapter(getFragmentManager(), vpContent, fragments);
        vpContent.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        vpContent.setCurrentItem(0);
        vpContent.setOffscreenPageLimit(3);
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                Animation animation = new TranslateAnimation(bmpW * currIndex, bmpW * arg0, 0, 0);//显然这个比较简洁，只有一行代码。
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(300);
                line.startAnimation(animation);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void InitImageView() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //得到选项卡的宽度
        int screenW = dm.widthPixels - dip2px(getContext(), 80);
        offset = bmpW = (screenW / 3);

        ViewGroup.LayoutParams layoutParams = line.getLayoutParams();
        layoutParams.width = bmpW;
        line.setLayoutParams(layoutParams);

    }

    @Override
    protected void addListeners() {
        ivSearch.setOnClickListener(this);
        tvHomePage.setOnClickListener(this);
        tvDestination.setOnClickListener(this);
        tvTarento.setOnClickListener(this);
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                SearchActivity.startActivity(getActivity());
                break;

            case R.id.tv_home_page:
                vpContent.setCurrentItem(0);
                break;

            case R.id.tv_destination:
                vpContent.setCurrentItem(1);
                break;

            case R.id.tv_tarento:
                vpContent.setCurrentItem(2);
                break;


        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }

    public int dip2px(Context context, int dipValue) {
        if (context != null) {
            float reSize = context.getResources().getDisplayMetrics().density;
            return (int) ((dipValue * reSize) + 0.5);
        }
        return dipValue;
    }
}
