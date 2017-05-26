package com.zxwl.vod.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zxwl.vod.R;
import com.zxwl.vod.views.scrollable.ScrollableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 第二个tab的第二层fragment_destination
 */
public class TravelDestinaTionFragment extends BaseFragment {
    private ImageView ivList;
    private SlidingTabLayout tabLayout;
    private ViewPager vpContent;
    private ScrollableLayout scrollableLayout;

    private MyPagerAdapter mAdapter;

    private String[] titles = {
            "Scenic spot",
            "Cate",
            "Play",
            "Shopping"
    };

    private ArrayList<BaseScrollAbleFragment> fragments = new ArrayList<>();

    public TravelDestinaTionFragment() {
    }

    public static TravelDestinaTionFragment newInstance() {
        TravelDestinaTionFragment fragment = new TravelDestinaTionFragment();
        return fragment;
    }

    @Override
    protected View inflateContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_travel_tarento, container, false);
    }

    @Override
    protected void findViews(View view) {
        ivList = (ImageView) view.findViewById(R.id.iv_list);
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tab_layout);
        vpContent = (ViewPager) view.findViewById(R.id.vp_content);
        scrollableLayout = (ScrollableLayout) view.findViewById(R.id.scrollable_layout);
    }

    @Override
    protected void init() {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabLayout.getLayoutParams();
        layoutParams.leftMargin = 12;
        tabLayout.setLayoutParams(layoutParams);

        for (int i = 0; i < titles.length; i++) {
            fragments.add(TravelDestinaTionScenicFragment.newInstance());
        }
        final MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager(), fragments);
        vpContent.setAdapter(adapter);
        vpContent.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        vpContent.setOffscreenPageLimit(titles.length);
        vpContent.setCurrentItem(0);
        tabLayout.setViewPager(vpContent);
        scrollableLayout.getHelper().setCurrentScrollableContainer(fragments.get(0));
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                scrollableLayout.getHelper().setCurrentScrollableContainer(fragments.get(arg0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void lazyFetchData() {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private List<BaseScrollAbleFragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm, List<BaseScrollAbleFragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
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

}
