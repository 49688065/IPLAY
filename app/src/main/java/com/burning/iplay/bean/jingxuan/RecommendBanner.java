package com.burning.iplay.bean.jingxuan;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-18  20:04
 * Email f842728368@163.com
 * @desc 精选-推荐-头部轮播图
 */
public class RecommendBanner {

    /**
     * code : 0
     * info : [{"address":"http://play.163.com/17/0217/16/CDG6E1NG00314502.html","docid":"CDG6E1NG00314502","imgUrl":"http://img1.cache.netease.com/photo/0031/2017-02-17/CDG970CG71570031.jpg","priority":99,"subtitleOne":"百万稿费","subtitleTwo":"讨论","title":"你怎么看待手游电竞化趋势？"},{"address":"http://play.163.com/16/0406/14/BJVN2VM800314502.html","docid":"BJVN2VM800314502","imgUrl":"http://img1.cache.netease.com/game/2016/5/20/20160520133705cba83.jpg","priority":98,"subtitleOne":"爱玩也爱写","subtitleTwo":"","title":"欢迎参加百万稿费活动！"},{"address":"http://play.163.com/16/1103/16/C4VB1AF400314502.html","docid":"C4VB1AF400314502","imgUrl":"http://img2.cache.netease.com/game/2016/11/3/201611031704382c46f.jpg","priority":97,"subtitleOne":"爱玩精选","subtitleTwo":"公告","title":"爱玩APP2.0【精选】指南"}]
     * writeNull : false
     */

    private int code;
    private boolean writeNull;
    private List<InfoBean> info;

    @Override
    public String toString() {
        return "RecommendBanner{" +
                "code=" + code +
                ", writeNull=" + writeNull +
                ", info=" + info +
                '}';
    }

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
        @Override
        public String toString() {
            return "InfoBean{" +
                    "address='" + address + '\'' +
                    ", docid='" + docid + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", priority=" + priority +
                    ", subtitleOne='" + subtitleOne + '\'' +
                    ", subtitleTwo='" + subtitleTwo + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        /**
         * address : http://play.163.com/17/0217/16/CDG6E1NG00314502.html
         * docid : CDG6E1NG00314502
         * imgUrl : http://img1.cache.netease.com/photo/0031/2017-02-17/CDG970CG71570031.jpg
         * priority : 99
         * subtitleOne : 百万稿费
         * subtitleTwo : 讨论
         * title : 你怎么看待手游电竞化趋势？
         */


        private String address;
        private String docid;
        private String imgUrl;
        private int priority;
        private String subtitleOne;
        private String subtitleTwo;
        private String title;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getSubtitleOne() {
            return subtitleOne;
        }

        public void setSubtitleOne(String subtitleOne) {
            this.subtitleOne = subtitleOne;
        }

        public String getSubtitleTwo() {
            return subtitleTwo;
        }

        public void setSubtitleTwo(String subtitleTwo) {
            this.subtitleTwo = subtitleTwo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
