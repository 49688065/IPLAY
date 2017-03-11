package com.burning.iplay.ui.splash;

import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.widget.ImageView;

import com.burning.iplay.R;
import com.burning.iplay.api.ApiManager;
import com.burning.iplay.base.BaseActivity;
import com.burning.iplay.bean.SplashData;
import com.burning.iplay.bean.db.SplashInfoDbBean;
import com.burning.iplay.ui.main.MainActivity;
import com.burning.iplay.utils.ActivityUtil;
import com.burning.iplay.utils.ImageLoader;
import com.burning.iplay.utils.LogUtils;
import com.burning.iplay.utils.TimeUtils;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
/**
 * @author lam
 * @email 49688065@qq.com
 * @time ${DATE}  ${TIME}
 * @desc
 */
public class SplashActivity extends BaseActivity {

    /*运行时权限成功授权码*/
    private static final int GRAND = PackageManager.PERMISSION_GRANTED;

    private ImageView mSplashIv;
    private boolean mSplashDbExist;
    private long mStartTimeMills;
    private long mShowTime;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initUI() {
        mStartTimeMills = TimeUtils.getNowTimeMills();

        mSplashIv = (ImageView) findViewById(R.id.splash_iv);
        mSplashIv.setBackgroundResource(R.drawable.splash720x1280);

        requestImmersion();

    }


    /**
     * 权限请求成功时调用
     */
    protected void requestPermissionSucceed() {
        initData();
    }


    //读取缓存,看有没有缓存,并且缓存的json没有超过有效期
    private void initData() {
        //1.读取数据库的
        //查看数据库是否有保存app启动图片的json数据
        mSplashDbExist = DataSupport.isExist(SplashInfoDbBean.class);
        if (mSplashDbExist) {
            SplashInfoDbBean lastSplashDbBean = DataSupport.findLast(SplashInfoDbBean.class);
            String expireEndTime = lastSplashDbBean.getExpireEndTime();
            long endTime = TimeUtils.string2Millis(expireEndTime);
            long nowTime = TimeUtils.date2Millis(new Date());
            if (nowTime < endTime) {
                String imgUrl = lastSplashDbBean.getImgUrl();
                ImageLoader.loadImage(this, imgUrl, mSplashIv);//glide会自动缓存图片
                //拿到图片显示的时间换算成毫秒单位
                mShowTime = lastSplashDbBean.getShowTime() * 1000;
                final long nowTimeMills = TimeUtils.getNowTimeMills();
                //判断app启动的时间是否大于3秒
                if (nowTimeMills - mStartTimeMills > mShowTime) {
                    startMainActivity();
                } else {
                    new Thread() {
                        public void run() {
                            //重新拿到当前时间计算等待时间
                            SystemClock.sleep(mShowTime - (nowTimeMills - mStartTimeMills));
                            startMainActivity();
                        }
                    }.start();
                }
            }
            //如果现在的间大于保存的时间则需要请求新的数据
            else {
                requestNetData();
            }
            //如果第一次登陆数据库表不存在,需要请求新的数据
        } else {
            requestNetData();
            new Thread() {
                public void run() {
                    long nowTimeMills = TimeUtils.getNowTimeMills();
                    // 第一次进入应用，要请求权限，直接设置广告界面时间是3秒
                    SystemClock.sleep(3000);
                    LogUtils.i("SplashActivity run()" + "toMainActivity");
                    startMainActivity();
                }
            }.start();
        }

    }

    private void requestNetData() {
        LogUtils.i("SplashActivity requestNetData()");
        ApiManager.getInstance().getNewsApi()
                .getSphlashData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SplashData>() {
                    @Override
                    public void call(SplashData splashData) {//拿到登陆图片的数据
                        //Log.i("tag", "SplashActivity call()" + splashData.toString());
                        //数据保存到数据库
                        saveData2Db(splashData);
                    }
                });
    }

    private void saveData2Db(SplashData splashData) {
        LogUtils.i("SplashActivity saveData2Db()");
        List<SplashData.InfoBean> infos = splashData.getInfo();
        for (int i = 0; i < infos.size(); i++) {
            SplashData.InfoBean infoBean = infos.get(i);
            SplashInfoDbBean splashInfoDbBean = new SplashInfoDbBean(infoBean.getExpireBeginTime(), infoBean.getExpireEndTime(),
                    infoBean.getImageName(), infoBean.getImgUrl(), infoBean.getRedirectData(), infoBean.getRedirectTo(),
                    infoBean.getShowOrder(), infoBean.getShowTime());
            if (mSplashDbExist) {
                splashInfoDbBean.update(i);
            } else {
                splashInfoDbBean.save();
            }
        }
    }

    private void startMainActivity() {
        ActivityUtil.startActivityAndFinishSelf(this, MainActivity.class);
        finish();
    }


}
