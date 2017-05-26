package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zxwl.vod.R;
import com.zxwl.vod.fragment.VideoFragment;

import java.util.ArrayList;

public class TravelActivity extends BaseActivity {
    private ImageView ivBackOperate;
    private TextView tvTopTitle;
    private SlidingTabLayout tabLayout;
    private ViewPager vpContent;
    private MyPagerAdapter mAdapter;

    private String[] titles = {
            "Video",
            "At most",
            "Hot",
            "Everybody"
    };

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TravelActivity.class));
    }

    @Override
    protected void initView() {
        ivBackOperate = (ImageView) findViewById(R.id.iv_back_operate);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        tabLayout = (SlidingTabLayout) findViewById(R.id.tab_layout);
        vpContent = (ViewPager) findViewById(R.id.vp_content);
    }

    @Override
    protected void initData() {
        tvTopTitle.setText("Travel");

        for (int i = 0; i < titles.length; i++) {
            fragmentList.add(VideoFragment.newInstance());
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        tabLayout.setViewPager(vpContent, titles, this, fragmentList);
    }

    @Override
    protected void setListener() {
        ivBackOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Nullable
    @Override
    protected int getLayoutId() {
        return R.layout.activity_travel;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
