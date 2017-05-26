package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zxwl.vod.R;
import com.zxwl.vod.fragment.SearchVideoFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    private TextView tvCancel;
    private EditText etContent;
    private SlidingTabLayout tabLayout;
    private ViewPager vpContent;

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = {"Video", "Attractions", "Hipster"};
    private MyPagerAdapter mAdapter;

    @Override
    protected void initView() {
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        etContent = (EditText) findViewById(R.id.et_content);
        tabLayout = (SlidingTabLayout) findViewById(R.id.tab_layout);
        vpContent = (ViewPager) findViewById(R.id.vp_content);
    }

    @Override
    protected void initData() {
        initViewpager();
    }

    @Override
    protected void setListener() {
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    private void initViewpager() {
        for (String title : titles) {
            fragmentList.add(SearchVideoFragment.newInstance());
        }

        tabLayout = (SlidingTabLayout) findViewById(R.id.tab_layout);
        vpContent = (ViewPager) findViewById(R.id.vp_content);

        vpContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tabLayout.setViewPager(vpContent, titles);

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpContent.setCurrentItem(0);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,SearchActivity.class));
    }

    /**
     * viewpager的适配器
     */
    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return null == fragmentList ? 0 : fragmentList.size();
        }
    }
}
