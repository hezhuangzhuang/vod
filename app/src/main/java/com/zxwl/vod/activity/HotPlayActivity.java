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
import com.zxwl.vod.fragment.VideoListFragment;

import java.util.ArrayList;
import java.util.List;

public class HotPlayActivity extends BaseActivity implements VideoListFragment.onSwitchPlayListener {
    private SlidingTabLayout tabLayout;
    private ViewPager vpContent;

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = {"All", "Free", "Vip", "pay"};
    private MyPagerAdapter mAdapter;

    private ImageView ivBackOperate;
    private TextView tvTopTitle;

    @Override
    protected void initView() {
        tabLayout = (SlidingTabLayout) findViewById(R.id.tab_layout);
        vpContent = (ViewPager) findViewById(R.id.vp_content);

        ivBackOperate = (ImageView) findViewById(R.id.iv_back_operate);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);
    }

    @Override
    protected void initData() {
        initViewpager();

        tvTopTitle.setText("HOT PLAY");
    }

    private void initViewpager() {
        for (String title : titles) {
            fragmentList.add(VideoListFragment.newInstance(title, title));
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
        return R.layout.activity_hot_play;
    }

    @Override
    public void switchPlay(String url, String title) {
        Intent intent = new Intent(this,VideoPlayActivity.class);
        intent.putExtra(VideoPlayActivity.PARAM_URL,url);
        intent.putExtra(VideoPlayActivity.PARAM_TITLE,title);
        startActivity(intent);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context,HotPlayActivity.class));
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
