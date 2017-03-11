package com.burning.iplay.ui.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.burning.iplay.bean.detail.Video;
import com.burning.iplay.event.FinishVideoEvent;
import com.burning.iplay.event.RxBus;
import com.burning.iplay.view.MyVideoPlayer;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import rx.Subscription;
import rx.functions.Action1;

public class VideoActivity extends AppCompatActivity {

    public static final String VIDEO = "video";
    private Subscription mRxSbscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_video);

        Video bean = getIntent().getParcelableExtra(VIDEO);


        //JCVideoPlayerStandard videoPlayer = (JCVideoPlayerStandard) findViewById(R.id.video_player);
        //videoPlayer.setUp(bean.getUrl_mp4(), JCVideoPlayer.SCREEN_WINDOW_FULLSCREEN, "Video");
        //videPlayer.thumbImageView.setImageURI(Uri.parse(bean.getCover()));


        MyVideoPlayer.startFullscreen(this,
                MyVideoPlayer.class,
                bean.getUrl_mp4(),
                bean.getAlt());

        //videoPlayer.startButton.performClick();//模拟用户点击开始按钮，NORMAL状态下点击开始播放视频，播放中点击暂停视频

        // 监听对应事件
        mRxSbscription = RxBus.getInstance().toObserverable(FinishVideoEvent.class)
                .subscribe(new Action1<FinishVideoEvent>() {
                    @Override
                    public void call(FinishVideoEvent finishVideoEvent) {
                        finish();
                    }
                });
    }


    @Override
    public void onBackPressed() {
        MyVideoPlayer.backPress();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        if (!mRxSbscription.isUnsubscribed()) {
            mRxSbscription.unsubscribe();
        }
        super.onDestroy();
    }


}
