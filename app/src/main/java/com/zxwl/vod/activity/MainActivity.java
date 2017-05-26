package com.zxwl.vod.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;
import com.zxwl.vod.R;
import com.zxwl.vod.bean.TabEntity;
import com.zxwl.vod.bean.VideoBean;
import com.zxwl.vod.fragment.HomeFourFragment;
import com.zxwl.vod.fragment.HomeOneFragment;
import com.zxwl.vod.fragment.HomeThreeFragment;
import com.zxwl.vod.fragment.HomeTwoFragment;
import com.zxwl.vod.net.api.video.VideoApi;
import com.zxwl.vod.net.callback.RxSubscriber;
import com.zxwl.vod.net.exception.ResponeThrowable;
import com.zxwl.vod.net.http.HttpUtils;
import com.zxwl.vod.net.transformer.ListDefaultTransformer;
import com.zxwl.vod.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CommonTabLayout tabLayout;

    private String[] mTitles = {"Video", "Travel", "Ticket", "Mine"};

    private int[] mIconUnselectIds = {
            R.mipmap.tab_video, R.mipmap.tab_travel,
            R.mipmap.tab_ticket, R.mipmap.tab_mine};

    private int[] mIconSelectIds = {
            R.mipmap.tab_video_select, R.mipmap.tab_travel_select,
            R.mipmap.tab_ticket_select, R.mipmap.tab_mine_select};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private ArrayList<Fragment> fragments = new ArrayList<>();

    /**
     * 判断是否已经点击过一次回退键
     */
    private boolean isBackPressed = false;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);

        setTab();

//        getData();
    }

    private void getData() {
        HttpUtils.getInstance(this)
                .getRetofitClinet()
                .builder(VideoApi.class)
                .getVideoList(4, 1)
                .compose(new ListDefaultTransformer<VideoBean>())
                .subscribe(new RxSubscriber<List<VideoBean>>() {
                    @Override
                    public void onSuccess(List<VideoBean> beanList) {
                        Toast.makeText(MainActivity.this, beanList.get(0).toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onError(ResponeThrowable throwable) {
                        Toast.makeText(MainActivity.this, throwable.message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 保存数据状态
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void setTab() {
        tabLayout = (CommonTabLayout) findViewById(R.id.tab_layout);

        for (int i = 0, count = mTitles.length; i < count; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        fragments.add(HomeOneFragment.newInstance());
        fragments.add(HomeTwoFragment.newInstance());
        fragments.add(HomeThreeFragment.newInstance());
        fragments.add(HomeFourFragment.newInstance());

        tabLayout.setTabData(mTabEntities, this, R.id.fl_change, fragments);
        tabLayout.setCurrentTab(0);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void doublePressBackToast() {
        if (!isBackPressed) {
            Log.i("doublePressBackToast", "再次点击返回退出程序");
            isBackPressed = true;
            Toast.makeText(this, "再次点击返回退出程序", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("doublePressBackToast", "exit");
            finish();
            AppManager.getInstance().appExit();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 2000);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doublePressBackToast();
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }
}
