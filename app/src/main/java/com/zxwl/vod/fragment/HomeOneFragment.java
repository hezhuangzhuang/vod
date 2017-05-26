package com.zxwl.vod.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zxwl.vod.R;
import com.zxwl.vod.activity.ClassIfyActivity;
import com.zxwl.vod.activity.HistoryActivity;
import com.zxwl.vod.activity.SearchActivity;

import java.util.ArrayList;

import static com.zxwl.vod.R.id.iv_more;

/**
 * 主页面第一个tab页面
 */
public class HomeOneFragment extends BaseFragment {
    private SlidingTabLayout tabLayout;
    private ImageView ivMore;
    private ImageView ivHistoty;
    private ViewPager vpContent;

    private SearchView svContent;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "Hot", "Travel", "Movie"
            , "TV play", "Variety", "Manga"
    };

    private MyPagerAdapter mAdapter;

    public HomeOneFragment() {
    }

    public static HomeOneFragment newInstance() {
        HomeOneFragment fragment = new HomeOneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_tab_one, container, false);
    }

    @Override
    protected void findViews(View view) {
        ivMore = (ImageView) view.findViewById(iv_more);
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tv_layout);
        vpContent = (ViewPager) view.findViewById(R.id.vp);

        svContent = (SearchView) view.findViewById(R.id.sv_content);
        ivHistoty = (ImageView) view.findViewById(R.id.iv_histoty);
    }

    @Override
    protected void init() {
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        vpContent.setAdapter(mAdapter);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(new VideoFragment());
        }

        tabLayout.setViewPager(vpContent, mTitles, getActivity(), mFragments);
    }

    @Override
    protected void addListeners() {
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassIfyActivity.startActivity(getActivity());
            }
        });
        svContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.startActivity(getActivity());
            }
        });
        ivHistoty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryActivity.startActivity(getActivity());
            }
        });
    }

    @Override
    protected void lazyFetchData() {

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
