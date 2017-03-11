package com.burning.iplay.bean;

import java.util.List;

/**
 * @author lam
 * @time 2017-02-22  11:38
 * @desc ${TODD}
 */
public class SplashData  {
    /**
     * code : 0
     * info : [{"expireBeginTime":"2016-10-02 10:03:03","expireEndTime":"2018-11-03 10:02:54","imageName":"默认启动","imgUrl":"http://img3.cache.netease.com/game/app/qidong/default/720x1280.jpg","redirectData":"","redirectTo":1,"showOrder":1,"showTime":3}]
     * writeNull : false
     */

    private int code;
    private boolean writeNull;
    /**
     * expireBeginTime : 2016-10-02 10:03:03
     * expireEndTime : 2018-11-03 10:02:54
     * imageName : 默认启动
     * imgUrl : http://img3.cache.netease.com/game/app/qidong/default/720x1280.jpg
     * redirectData :
     * redirectTo : 1
     * showOrder : 1
     * showTime : 3
     */

    private List<InfoBean> info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isWriteNull() {
        return writeNull;
    }

    public void setWriteNull(boolean writeNull) {
        this.writeNull = writeNull;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        private String expireBeginTime;
        private String expireEndTime;
        private String imageName;
        private String imgUrl;
        private String redirectData;
        private int redirectTo;
        private int showOrder;
        private int showTime;

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

    @Override
    public String toString() {
        return "SplashData{" +
                "code=" + code +
                ", writeNull=" + writeNull +
                ", info=" + info +
                '}';
    }
}
