package com.burning.iplay.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.burning.iplay.event.FinishVideoEvent;
import com.burning.iplay.event.RxBus;

import java.util.Timer;
import java.util.TimerTask;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCUserActionStandard;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author Kiven
 * @time 2017-2-21  21:47
 * Email f842728368@163.com
 * @desc 自定义的VideoPlayer
 */
public class MyVideoPlayer extends JCVideoPlayer {

    protected static Timer DISMISS_CONTROL_VIEW_TIMER;

    public ImageView backButton;
    public ProgressBar bottomProgressBar, loadingProgressBar;
    public TextView titleTextView;
    public ImageView thumbImageView;
    public ImageView tinyBackImageView;


    protected DismissControlViewTimerTask mDismissControlViewTimerTask;


    public MyVideoPlayer(Context context) {
        super(context);
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        bottomProgressBar = (ProgressBar) findViewById(fm.jiecao.jcvideoplayer_lib.R.id.bottom_progress);
        titleTextView = (TextView) findViewById(fm.jiecao.jcvideoplayer_lib.R.id.title);
        backButton = (ImageView) findViewById(fm.jiecao.jcvideoplayer_lib.R.id.back);
        thumbImageView = (ImageView) findViewById(fm.jiecao.jcvideoplayer_lib.R.id.thumb);
        loadingProgressBar = (ProgressBar) findViewById(fm.jiecao.jcvideoplayer_lib.R.id.loading);
        tinyBackImageView = (ImageView) findViewById(fm.jiecao.jcvideoplayer_lib.R.id.back_tiny);

        thumbImageView.setOnClickListener(this);
        backButton.setOnClickListener(this);
        tinyBackImageView.setOnClickListener(this);

    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        if (objects.length == 0)
            return;
        titleTextView.setText(objects[0].toString());
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            fullscreenButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_shrink);
            //************************* 让退出全屏按钮不显示出来 ******************************/
            fullscreenButton.setVisibility(View.GONE);
            //************************* 让退出全屏按钮不显示出来 ******************************/
            backButton.setVisibility(View.VISIBLE);
            tinyBackImageView.setVisibility(View.INVISIBLE);
            changeStartButtonSize((int) getResources().getDimension(fm.jiecao.jcvideoplayer_lib.R.dimen.jc_start_button_w_h_fullscreen));
        } else if (currentScreen == SCREEN_LAYOUT_NORMAL
                || currentScreen == SCREEN_LAYOUT_LIST) {
            fullscreenButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_enlarge);
            //************************* 让退出全屏按钮不显示出来 ******************************/
            fullscreenButton.setVisibility(View.GONE);
            //************************* 让退出全屏按钮不显示出来 ******************************/
            backButton.setVisibility(View.GONE);
            tinyBackImageView.setVisibility(View.INVISIBLE);
            changeStartButtonSize((int) getResources().getDimension(fm.jiecao.jcvideoplayer_lib.R.dimen.jc_start_button_w_h_normal));
        } else if (currentScreen == SCREEN_WINDOW_TINY) {
            tinyBackImageView.setVisibility(View.VISIBLE);
            setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                    View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
        }
    }

    public void changeStartButtonSize(int size) {
        ViewGroup.LayoutParams lp = startButton.getLayoutParams();
        lp.height = size;
        lp.width = size;
        lp = loadingProgressBar.getLayoutParams();
        lp.height = size;
        lp.width = size;
    }

    @Override
    public int getLayoutId() {
        return fm.jiecao.jcvideoplayer_lib.R.layout.jc_layout_standard;
    }

    @Override
    public void setUiWitStateAndScreen(int state) {
        super.setUiWitStateAndScreen(state);
        switch (currentState) {
            case CURRENT_STATE_NORMAL:
                changeUiToNormal();
                break;
            case CURRENT_STATE_PREPARING:
                changeUiToPreparingShow();
                startDismissControlViewTimer();
                break;
            case CURRENT_STATE_PLAYING:
                changeUiToPlayingShow();
                startDismissControlViewTimer();
                break;
            case CURRENT_STATE_PAUSE:
                changeUiToPauseShow();
                cancelDismissControlViewTimer();
                break;
            case CURRENT_STATE_ERROR:
                changeUiToError();
                break;
            case CURRENT_STATE_AUTO_COMPLETE:
                //LogUtils.i("MyVideoPlayer setUiWitStateAndScreen()" + "CURRENT_STATE_AUTO_COMPLETE");
                // 这里播放完成，同样需要关闭自身Activity
                RxBus.getInstance().post(new FinishVideoEvent());

                changeUiToCompleteShow();
                cancelDismissControlViewTimer();
                bottomProgressBar.setProgress(100);
                break;
            case CURRENT_STATE_PLAYING_BUFFERING_START:
                changeUiToPlayingBufferingShow();
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        if (id == fm.jiecao.jcvideoplayer_lib.R.id.surface_container) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    if (mChangePosition) {
                        int duration = getDuration();
                        int progress = mSeekTimePosition * 100 / (duration == 0 ? 1 : duration);
                        bottomProgressBar.setProgress(progress);
                    }
                    if (!mChangePosition && !mChangeVolume) {
                        onEvent(JCUserActionStandard.ON_CLICK_BLANK);
                        onClickUiToggle();
                    }
                    break;
            }
        } else if (id == fm.jiecao.jcvideoplayer_lib.R.id.bottom_seek_progress) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    cancelDismissControlViewTimer();
                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    break;
            }
        }
        return super.onTouch(v, event);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == fm.jiecao.jcvideoplayer_lib.R.id.thumb) {
            if (TextUtils.isEmpty(url)) {
                Toast.makeText(getContext(), getResources().getString(fm.jiecao.jcvideoplayer_lib.R.string.no_url), Toast.LENGTH_SHORT).show();
                return;
            }
            if (currentState == CURRENT_STATE_NORMAL) {
                if (!url.startsWith("file") && !url.startsWith("/") &&
                        !JCUtils.isWifiConnected(getContext()) && !WIFI_TIP_DIALOG_SHOWED) {
                    showWifiDialog();
                    return;
                }
                startVideo();
            } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
                onClickUiToggle();
            }
        } else if (i == fm.jiecao.jcvideoplayer_lib.R.id.surface_container) {
            startDismissControlViewTimer();
        } else if (i == fm.jiecao.jcvideoplayer_lib.R.id.back) {
            backPress();
        } else if (i == fm.jiecao.jcvideoplayer_lib.R.id.back_tiny) {
            backPress();
        }
    }


    @Override
    public void showWifiDialog() {
        super.showWifiDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getResources().getString(fm.jiecao.jcvideoplayer_lib.R.string.tips_not_wifi));
        builder.setPositiveButton(getResources().getString(fm.jiecao.jcvideoplayer_lib.R.string.tips_not_wifi_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startVideo();
                WIFI_TIP_DIALOG_SHOWED = true;
            }
        });
        // 取消wifi按钮的事件
        builder.setNegativeButton(getResources().getString(fm.jiecao.jcvideoplayer_lib.R.string.tips_not_wifi_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        cancelDismissControlViewTimer();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        startDismissControlViewTimer();
    }

    public void startVideo() {
        prepareMediaPlayer();
        onEvent(JCUserActionStandard.ON_CLICK_START_THUMB);
    }

    public void onClickUiToggle() {
        if (currentState == CURRENT_STATE_PREPARING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPreparingClear();
            } else {
                changeUiToPreparingShow();
            }
        } else if (currentState == CURRENT_STATE_PLAYING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
            } else {
                changeUiToPlayingShow();
            }
        } else if (currentState == CURRENT_STATE_PAUSE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            } else {
                changeUiToPauseShow();
            }
        } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToCompleteClear();
            } else {
                changeUiToCompleteShow();
            }
        } else if (currentState == CURRENT_STATE_PLAYING_BUFFERING_START) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingBufferingClear();
            } else {
                changeUiToPlayingBufferingShow();
            }
        }
    }

    public void onCLickUiToggleToClear() {
        if (currentState == CURRENT_STATE_PREPARING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPreparingClear();
            } else {
            }
        } else if (currentState == CURRENT_STATE_PLAYING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
            } else {
            }
        } else if (currentState == CURRENT_STATE_PAUSE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            } else {
            }
        } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToCompleteClear();
            } else {
            }
        } else if (currentState == CURRENT_STATE_PLAYING_BUFFERING_START) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingBufferingClear();
            } else {
            }
        }
    }

    @Override
    public void setProgressAndText() {
        super.setProgressAndText();
        int position = getCurrentPositionWhenPlaying();
        int duration = getDuration();
        int progress = position * 100 / (duration == 0 ? 1 : duration);
        if (progress != 0)
            bottomProgressBar.setProgress(progress);
    }

    @Override
    public void setBufferProgress(int bufferProgress) {
        super.setBufferProgress(bufferProgress);
        if (bufferProgress != 0)
            bottomProgressBar.setSecondaryProgress(bufferProgress);
    }

    @Override
    public void resetProgressAndTime() {
        super.resetProgressAndTime();
        bottomProgressBar.setProgress(0);
        bottomProgressBar.setSecondaryProgress(0);
    }

    //Unified management Ui
    public void changeUiToNormal() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }
    }

    public void changeUiToPreparingShow() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPreparingClear() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    //JustPreparedUi
    @Override
    public void onPrepared() {
        super.onPrepared();
        setAllControlsVisible(View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
                View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
        startDismissControlViewTimer();
    }

    public void changeUiToPlayingShow() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPlayingClear() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPauseShow() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPauseClear() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPlayingBufferingShow() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPlayingBufferingClear() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToCompleteShow() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToCompleteClear() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToError() {
        switch (currentScreen) {
            case SCREEN_LAYOUT_NORMAL:
            case SCREEN_LAYOUT_LIST:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisible(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void setAllControlsVisible(int topCon, int bottomCon, int startBtn, int loadingPro,
                                      int thumbImg, int coverImg, int bottomPro) {


        topContainer.setVisibility(topCon);
        bottomContainer.setVisibility(bottomCon);
        startButton.setVisibility(startBtn);
        loadingProgressBar.setVisibility(loadingPro);
        thumbImageView.setVisibility(thumbImg);
        bottomProgressBar.setVisibility(bottomPro);
    }

    public void updateStartImage() {
        if (currentState == CURRENT_STATE_PLAYING) {
            startButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_click_pause_selector);
        } else if (currentState == CURRENT_STATE_ERROR) {
            startButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_click_error_selector);
        } else {
            startButton.setImageResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_click_play_selector);
        }
    }


    protected Dialog mProgressDialog;
    protected ProgressBar mDialogProgressBar;
    protected TextView mDialogSeekTime;
    protected TextView mDialogTotalTime;
    protected ImageView mDialogIcon;

    @Override
    public void showProgressDialog(float deltaX, String seekTime, int seekTimePosition, String totalTime, int totalTimeDuration) {
        super.showProgressDialog(deltaX, seekTime, seekTimePosition, totalTime, totalTimeDuration);
        if (mProgressDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(fm.jiecao.jcvideoplayer_lib.R.layout.jc_dialog_progress, null);
            mDialogProgressBar = ((ProgressBar) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.duration_progressbar));
            mDialogSeekTime = ((TextView) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.tv_current));
            mDialogTotalTime = ((TextView) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.tv_duration));
            mDialogIcon = ((ImageView) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.duration_image_tip));
            mProgressDialog = new Dialog(getContext(), fm.jiecao.jcvideoplayer_lib.R.style.jc_style_dialog_progress);
            mProgressDialog.setContentView(localView);
            mProgressDialog.getWindow().addFlags(Window.FEATURE_ACTION_BAR);
            mProgressDialog.getWindow().addFlags(32);
            mProgressDialog.getWindow().addFlags(16);
            mProgressDialog.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams localLayoutParams = mProgressDialog.getWindow().getAttributes();
            localLayoutParams.gravity = Gravity.CENTER;
            mProgressDialog.getWindow().setAttributes(localLayoutParams);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }

        mDialogSeekTime.setText(seekTime);
        mDialogTotalTime.setText(" / " + totalTime);
        mDialogProgressBar.setProgress(totalTimeDuration <= 0 ? 0 : (seekTimePosition * 100 / totalTimeDuration));
        if (deltaX > 0) {
            mDialogIcon.setBackgroundResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_forward_icon);
        } else {
            mDialogIcon.setBackgroundResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_backward_icon);
        }
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected Dialog mVolumeDialog;
    protected ProgressBar mDialogVolumeProgressBar;
    protected TextView mDialogVolumeTextView;
    protected ImageView mDialogVolumeImageView;

    @Override
    public void showVolumeDialog(float deltaY, int volumePercent) {
        super.showVolumeDialog(deltaY, volumePercent);
        if (mVolumeDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(fm.jiecao.jcvideoplayer_lib.R.layout.jc_dialog_volume, null);
            mDialogVolumeImageView = ((ImageView) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.volume_image_tip));
            mDialogVolumeTextView = ((TextView) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.tv_volume));
            mDialogVolumeProgressBar = ((ProgressBar) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.volume_progressbar));
            mVolumeDialog = new Dialog(getContext(), fm.jiecao.jcvideoplayer_lib.R.style.jc_style_dialog_progress);
            mVolumeDialog.setContentView(localView);
            mVolumeDialog.getWindow().addFlags(8);
            mVolumeDialog.getWindow().addFlags(32);
            mVolumeDialog.getWindow().addFlags(16);
            mVolumeDialog.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams localLayoutParams = mVolumeDialog.getWindow().getAttributes();
            localLayoutParams.gravity = Gravity.CENTER;
            mVolumeDialog.getWindow().setAttributes(localLayoutParams);
        }
        if (!mVolumeDialog.isShowing()) {
            mVolumeDialog.show();
        }
        if (volumePercent <= 0) {
            mDialogVolumeImageView.setBackgroundResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_close_volume);
        } else {
            mDialogVolumeImageView.setBackgroundResource(fm.jiecao.jcvideoplayer_lib.R.drawable.jc_add_volume);
        }
        if (volumePercent > 100) {
            volumePercent = 100;
        } else if (volumePercent < 0) {
            volumePercent = 0;
        }
        mDialogVolumeTextView.setText(volumePercent + "%");
        mDialogVolumeProgressBar.setProgress(volumePercent);
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissVolumeDialog() {
        super.dismissVolumeDialog();
        if (mVolumeDialog != null) {
            mVolumeDialog.dismiss();
        }
    }

    protected Dialog mBrightnessDialog;
    protected ProgressBar mDialogBrightnessProgressBar;
    protected TextView mDialogBrightnessTextView;

    @Override
    public void showBrightnessDialog(int brightnessPercent) {
        super.showBrightnessDialog(brightnessPercent);
        if (mBrightnessDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(fm.jiecao.jcvideoplayer_lib.R.layout.jc_dialog_brightness, null);
            mDialogBrightnessTextView = ((TextView) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.tv_brightness));
            mDialogBrightnessProgressBar = ((ProgressBar) localView.findViewById(fm.jiecao.jcvideoplayer_lib.R.id.brightness_progressbar));
            mBrightnessDialog = new Dialog(getContext(), fm.jiecao.jcvideoplayer_lib.R.style.jc_style_dialog_progress);
            mBrightnessDialog.setContentView(localView);
            mBrightnessDialog.getWindow().addFlags(Window.FEATURE_ACTION_BAR);
            mBrightnessDialog.getWindow().addFlags(32);
            mBrightnessDialog.getWindow().addFlags(16);
            mBrightnessDialog.getWindow().setLayout(-2, -2);
            WindowManager.LayoutParams localLayoutParams = mBrightnessDialog.getWindow().getAttributes();
            localLayoutParams.gravity = Gravity.CENTER;
            mBrightnessDialog.getWindow().setAttributes(localLayoutParams);

        }
        if (!mBrightnessDialog.isShowing()) {
            mBrightnessDialog.show();
        }
        if (brightnessPercent > 100) {
            brightnessPercent = 100;
        } else if (brightnessPercent < 0) {
            brightnessPercent = 0;
        }
        mDialogBrightnessTextView.setText(brightnessPercent + "%");
        mDialogBrightnessProgressBar.setProgress(brightnessPercent);
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissBrightnessDialog() {
        super.dismissBrightnessDialog();
        if (mBrightnessDialog != null) {
            mBrightnessDialog.dismiss();
        }
    }

    public void startDismissControlViewTimer() {
        cancelDismissControlViewTimer();
        DISMISS_CONTROL_VIEW_TIMER = new Timer();
        mDismissControlViewTimerTask = new DismissControlViewTimerTask();
        DISMISS_CONTROL_VIEW_TIMER.schedule(mDismissControlViewTimerTask, 2500);
    }

    public void cancelDismissControlViewTimer() {
        if (DISMISS_CONTROL_VIEW_TIMER != null) {
            DISMISS_CONTROL_VIEW_TIMER.cancel();
        }
        if (mDismissControlViewTimerTask != null) {
            mDismissControlViewTimerTask.cancel();
        }

    }

    public class DismissControlViewTimerTask extends TimerTask {

        @Override
        public void run() {
            if (currentState != CURRENT_STATE_NORMAL
                    && currentState != CURRENT_STATE_ERROR
                    && currentState != CURRENT_STATE_AUTO_COMPLETE) {
                if (getContext() != null && getContext() instanceof Activity) {
                    ((Activity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bottomContainer.setVisibility(View.INVISIBLE);
                            topContainer.setVisibility(View.INVISIBLE);
                            startButton.setVisibility(View.INVISIBLE);
                            if (currentScreen != SCREEN_WINDOW_TINY) {
                                bottomProgressBar.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        }
    }

    //************************* 处理退出全屏时的相关逻辑 ******************************/

    public static boolean backPress() {
        //LogUtils.i("MyVideoPlayer backPress()  退出全屏");
        if ((System.currentTimeMillis() - CLICK_QUIT_FULLSCREEN_TIME) < FULL_SCREEN_NORMAL_DELAY)
            return false;
        if (JCVideoPlayerManager.getSecondFloor() != null) {
            CLICK_QUIT_FULLSCREEN_TIME = System.currentTimeMillis();
            JCVideoPlayer jcVideoPlayer = JCVideoPlayerManager.getSecondFloor();
            jcVideoPlayer.onEvent(jcVideoPlayer.currentScreen == JCVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN ?
                    JCUserAction.ON_QUIT_FULLSCREEN :
                    JCUserAction.ON_QUIT_TINYSCREEN);
            JCVideoPlayerManager.getFirstFloor().playOnThisJcvd();


            // 点击右下角退出全屏时，关闭Activity
            //LogUtils.i("MyVideoPlayer backPress()  点击右下角退出全屏时，关闭Activity");
            RxBus.getInstance().post(new FinishVideoEvent());
            return true;
        } else if (JCVideoPlayerManager.getFirstFloor() != null &&
                (JCVideoPlayerManager.getFirstFloor().currentScreen == SCREEN_WINDOW_FULLSCREEN ||
                        JCVideoPlayerManager.getFirstFloor().currentScreen == SCREEN_WINDOW_TINY)) {//以前我总想把这两个判断写到一起，这分明是两个独立是逻辑
            CLICK_QUIT_FULLSCREEN_TIME = System.currentTimeMillis();
            //直接退出全屏和小窗
            JCVideoPlayerManager.getCurrentJcvd().currentState = CURRENT_STATE_NORMAL;
            JCVideoPlayerManager.getFirstFloor().clearFloatScreen();
            JCMediaManager.instance().releaseMediaPlayer();
            JCVideoPlayerManager.setFirstFloor(null);

            // 点击左上方返回按钮时，关闭Activity
            //LogUtils.i("MyVideoPlayer backPress()  点击左上方返回按钮时，关闭Activity");
            RxBus.getInstance().post(new FinishVideoEvent());
            return true;
        }
        return false;
    }

}
