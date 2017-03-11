package com.burning.iplay.bean.db;

import org.litepal.crud.DataSupport;

/**
 * @author lam
 * @time 2017-02-22  16:43
 * @desc ${TODD}
 */
public class SplashInfoDbBean extends DataSupport {
    private String expireBeginTime;
    private String expireEndTime;
    private String imageName;
    private String imgUrl;
    private String redirectData;
    private int redirectTo;
    private int showOrder;
    private int showTime;

    public SplashInfoDbBean() {
    }

    public SplashInfoDbBean(String expireBeginTime, String expireEndTime,
                            String imageName, String imgUrl, String redirectData,
                            int redirectTo, int showOrder, int showTime) {
        this.expireBeginTime = expireBeginTime;
        this.expireEndTime = expireEndTime;
        this.imageName = imageName;
        this.imgUrl = imgUrl;
        this.redirectData = redirectData;
        this.redirectTo = redirectTo;
        this.showOrder = showOrder;
        this.showTime = showTime;
    }

    public String getExpireBeginTime() {
        return expireBeginTime;
    }

    public void setExpireBeginTime(String expireBeginTime) {
        this.expireBeginTime = expireBeginTime;
    }

    public String getExpireEndTime() {
        return expireEndTime;
    }

    public void setExpireEndTime(String expireEndTime) {
        this.expireEndTime = expireEndTime;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRedirectData() {
        return redirectData;
    }

    public void setRedirectData(String redirectData) {
        this.redirectData = redirectData;
    }

    public int getRedirectTo() {
        return redirectTo;
    }

    public void setRedirectTo(int redirectTo) {
        this.redirectTo = redirectTo;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public int getShowTime() {
        return showTime;
    }

    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "expireBeginTime='" + expireBeginTime + '\'' +
                ", expireEndTime='" + expireEndTime + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", redirectData='" + redirectData + '\'' +
                ", redirectTo=" + redirectTo +
                ", showOrder=" + showOrder +
                ", showTime=" + showTime +
                '}';
    }
}