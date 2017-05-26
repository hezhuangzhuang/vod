package com.zxwl.vod.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.zxwl.vod.R;
import com.zxwl.vod.fragment.VideoListFragment;
import com.zxwl.vod.utils.CommonUtils;
import com.zxwl.vod.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * 视频播放界面
 */
public class VideoPlayActivity extends AppCompatActivity implements VideoListFragment.onSwitchPlayListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {
    /*viewpager----start*/
    private SlidingTabLayout tabLayout;
    private ViewPager vpContent;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles = {"Introduce", "Catalogue", "recommend"};
    private MyPagerAdapter mAdapter;
    /*viewpager----end*/

    /*视频播放----start*/
    private static final int PROGRESS = 1;//更新进度条
    private static final int HIDE_CONTROLLER = 2;//隐藏进度条

    private ImageView ivBack;
    private TextView tvTitle;
    private LinearLayout llLoading;
    private FrameLayout flVideo;
    private RelativeLayout rlController;
    private TextView tvSwitchFullScreen;
    private TextView tvPause;
    private TextView tvAlreadyPalyTime;
    private TextView tvTotalTime;
    private AppCompatSeekBar sbTime;
    private LinearLayout llBuffer;
    private TextView tvBuffer;

    private Uri uri;
    private String path = "http://flv2.bn.netease.com/tvmrepo/2016/5/N/3/EBMTJBGN3/SD/EBMTJBGN3-mobile.mp4";
    private VideoView videoView;
    boolean isPortrait = true;//是否是竖屏
    private long mPosition = 0;
    private boolean isShowController = false;//是否显示控制面板  true显示   false不显示
    private int preCurrentPositon;//上一秒视频播放的位置
    private boolean isNetUrl = true;//是否是网络地址
    private boolean isUseSystem = false;//是否用系统本身的来检测
    /*视频播放----end*/

    //    private String baseUrl = "http://flv2.bn.netease.com/tvmrepo/2016/5/N/3/EBMTJBGN3/SD/EBMTJBGN3-mobile.mp4";
    private String baseUrl = "http://192.168.222.210:8080/2.m3u8";

    public static String PARAM_URL = "param_url";
    public static String PARAM_TITLE = "param_title";


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, VideoPlayActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_video_play);

        String url = getIntent().getStringExtra(PARAM_URL);
        String title = getIntent().getStringExtra(PARAM_TITLE);

        //初始化viewpager
        initViewPager();
        //初始化控件
        tvSwitchFullScreen = (TextView) findViewById(R.id.tv_switch_full_screen);
        videoView = (VideoView) findViewById(R.id.buffer);
        flVideo = (FrameLayout) findViewById(R.id.fl_video);
        rlController = (RelativeLayout) findViewById(R.id.media_controller);
        tvPause = (TextView) findViewById(R.id.tv_pause);
        tvAlreadyPalyTime = (TextView) findViewById(R.id.tv_already_paly_time);
        tvTotalTime = (TextView) findViewById(R.id.tv_total_time);
        sbTime = (AppCompatSeekBar) findViewById(R.id.sb_time);
        llLoading = (LinearLayout) findViewById(R.id.ll_loading);
        llBuffer = (LinearLayout) findViewById(R.id.ll_buffer);
        tvBuffer = (TextView) findViewById(R.id.tv_buffer);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPortrait) {
                    LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            DensityUtil.dip2px(200, VideoPlayActivity.this)
                    );

                    flVideo.setLayoutParams(fl_lp);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isPortrait = true;
                } else {
                    finish();
                }
            }
        });

        //判断传过来的标题是否为空
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }

        //判断传过来的路径是否为空
        if (!TextUtils.isEmpty(url)) {
            baseUrl = url;
        }

        //转换路径
        uri = Uri.parse(baseUrl);
        //设置播放的uri
        videoView.setVideoURI(uri);

        //拖动进度条
        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //如果是用户引起的
                if (fromUser) {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //触摸屏幕隐藏控制面板
        flVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideMediaController();
            }
        });

        //全屏和竖屏切换
        tvSwitchFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPortrait) {
                    LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                            getHeightPixel(VideoPlayActivity.this),
                            getWidthPixel(VideoPlayActivity.this)
                                    - getStatusBarHeight(VideoPlayActivity.this)
                    );

                    flVideo.setLayoutParams(fl_lp);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
                    isPortrait = false;
                } else {
                    LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            DensityUtil.dip2px(200, VideoPlayActivity.this)
                    );
                    flVideo.setLayoutParams(fl_lp);

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isPortrait = true;
                }
            }
        });

        //暂停和播放切换
        tvPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAndPause();
            }
        });

        videoView.requestFocus();
        videoView.setOnInfoListener(this);
        videoView.setOnBufferingUpdateListener(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                //设置为播放状态
                tvPause.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.video_pause, 0, 0, 0);
                mediaPlayer.setPlaybackSpeed(1.0f);
                videoView.start();
                /*--------------- add begin  坑爹的bug啊，播放器参数就在括号里啊，乱用什么~~~~(>_<)~~~~---------------*/
//            VideoWidth = mp.getVideoWidth();
//            VideoHeight = mp.getVideoHeight();
                /*--------------- add end  ---------------*/

                //得到视频宽高
                int duration = (int) videoView.getDuration();
                tvTotalTime.setText(CommonUtils.timeToString(duration));
                sbTime.setMax(duration);
                //自定义控件后默认全屏，所以要先保持小屏
                mHandler.sendEmptyMessage(PROGRESS);
                //默认隐藏控制面板
                hideMediaController();
                llLoading.setVisibility(View.GONE);
            }
        });

        //播放完毕之后的监听
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //转到第一帧
                videoView.seekTo(0);
                videoView.pause();
                tvPause.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.video_play, 0, 0, 0);
            }
        });
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
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
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (videoView.isPlaying()) {
                    videoView.pause();
//                    pb.setVisibility(View.VISIBLE);
//                    downloadRateView.setText("");
//                    loadRateView.setText("");
//                    downloadRateView.setVisibility(View.VISIBLE);
//                    loadRateView.setVisibility(View.VISIBLE);
                }
                break;

            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                videoView.start();
//                pb.setVisibility(View.GONE);
//                downloadRateView.setVisibility(View.GONE);
//                loadRateView.setVisibility(View.GONE);
                break;

            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
//                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;

        }
        return true;
    }

    /**
     * 缓冲更新
     *
     * @param mp      the MediaPlayer the update pertains to
     * @param percent the percentage (0-100) of the buffer that has been filled thus
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        loadRateView.setText(percent + "%");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isPortrait) {
                LinearLayout.LayoutParams fl_lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        DensityUtil.dip2px(200, VideoPlayActivity.this)
                );

                flVideo.setLayoutParams(fl_lp);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                isPortrait = true;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mPosition = videoView.getCurrentPosition();
        videoView.stopPlayback();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mPosition > 0) {
            videoView.seekTo(mPosition);
            mPosition = 0;
        }
        super.onResume();
        videoView.start();
    }

    /**
     * 暂停和播放切换
     */
    private void startAndPause() {
        //正在播放
        if (videoView.isPlaying()) {
            videoView.pause();
            tvPause.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.video_play, 0, 0, 0);
        } else {
            videoView.start();
            tvPause.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.video_pause, 0, 0, 0);
        }
    }

    /**
     * 显示或隐藏控制面板
     */
    private void hideMediaController() {
        if (isShowController) {
            rlController.setVisibility(View.GONE);
            isShowController = false;
        } else {
            rlController.setVisibility(View.VISIBLE);
            isShowController = true;
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                //隐藏控制栏
                case HIDE_CONTROLLER:
                    //Toast.makeText(VideoPlayerActivity.this, "hiden", Toast.LENGTH_SHORT).show();
                    hideMediaController();
                    break;

                //设置进度条
                case PROGRESS:
                    int currentPosition = (int) videoView.getCurrentPosition();
                    sbTime.setProgress(currentPosition);
                    tvAlreadyPalyTime.setText(CommonUtils.timeToString(currentPosition));
                    //如果是网络视频，设置缓冲
                    if (isNetUrl) {
                        //只有网络资源才有缓存效果,实际测试，这个缓冲是假的，断了网根本用不了，对于大文件视频也没法播放
                        int bufferPercentage = videoView.getBufferPercentage();//默认0到100，并不是百分号，所以一会要除100
                        int totalBuffer = bufferPercentage * sbTime.getMax() / 100;
                        //                        Toast.makeText(VideoPlayerActivity.this,
                        //                                "bufferPercentage:" + bufferPercentage + "totalbuffer" + totalBuffer
                        //                                , Toast.LENGTH_SHORT).show();
                        sbTime.setSecondaryProgress((totalBuffer / 100));
                    } else {
                        sbTime.setSecondaryProgress(0);
                    }

                    //自定义测试视频卡不卡，算一下单位时间内是否走过500的距离，走不过说明比较卡
                    //实践证明，系统自己的根本不靠谱，还是自己写比较好
                    if (!isUseSystem && videoView.isPlaying()) {
                        int buffer = currentPosition - preCurrentPositon;
                        if (buffer < 500) {
                            String netSpeed = CommonUtils.getNetSpeed(VideoPlayActivity.this);
                            if (isNetUrl) {
                                tvBuffer.setText("加载中" + netSpeed);
                            } else {
                                tvBuffer.setText("拼命加载中");
                            }
                            llBuffer.setVisibility(View.VISIBLE);
                        } else {
                            llBuffer.setVisibility(View.GONE);
                        }
                        preCurrentPositon = currentPosition;
                    }

                    //每秒更新一次,坑爹啊，这里忘记改了
                    mHandler.removeMessages(PROGRESS);
                    mHandler.sendEmptyMessageDelayed(PROGRESS, 1000);
                    break;
            }
        }
    };

    public int getHeightPixel(Activity activity) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }

    public int getWidthPixel(Activity activity) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }

    public int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    @Override
    public void switchPlay(String url, String title) {
        uri = Uri.parse(url);
        //设置播放的uri
        videoView.setVideoURI(uri);
        //设置标题
        tvTitle.setText(title);
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

