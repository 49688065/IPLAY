package com.burning.iplay.bean.jingxuan;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-27  23:14
 * Email f842728368@163.com
 * @desc 精选-专栏的Bean
 */

public class Zhuanlan {

    private int code;
    private boolean writeNull;
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

    public static class InfoBean implements Parcelable {
        /**
         * followUserCount : 499
         * topicDescription : 趣味发掘考据，解读游戏艺术
         * listImg : http://img2.cache.netease.com/game/app/Column/yxwh_518_300_5.png
         * topicId : IT1476772090492
         * topicName : 游戏文化
         */

        private String followUserCount;
        private String topicDescription;
        private String listImg;
        private String topicId;
        private String topicName;

        @Override
        public String toString() {
            return "InfoBean{" +
                    "followUserCount='" + followUserCount + '\'' +
                    ", topicDescription='" + topicDescription + '\'' +
                    ", listImg='" + listImg + '\'' +
                    ", topicId='" + topicId + '\'' +
                    ", topicName='" + topicName + '\'' +
                    '}';
        }

        public String getFollowUserCount() {
            return followUserCount;
        }

        public void setFollowUserCount(String followUserCount) {
            this.followUserCount = followUserCount;
        }

        public String getTopicDescription() {
            return topicDescription;
        }

        public void setTopicDescription(String topicDescription) {
            this.topicDescription = topicDescription;
        }

        public String getListImg() {
            return listImg;
        }

        public void setListImg(String listImg) {
            this.listImg = listImg;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        //************************* Parcelable ******************************/
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.followUserCount);
            dest.writeString(this.topicDescription);
            dest.writeString(this.listImg);
            dest.writeString(this.topicId);
            dest.writeString(this.topicName);
        }

        public InfoBean() {
        }

        protected InfoBean(Parcel in) {
            this.followUserCount = in.readString();
            this.topicDescription = in.readString();
            this.listImg = in.readString();
            this.topicId = in.readString();
            this.topicName = in.readString();
        }

        public static final Parcelable.Creator<InfoBean> CREATOR = new Parcelable.Creator<InfoBean>() {
            @Override
            public InfoBean createFromParcel(Parcel source) {
                return new InfoBean(source);
            }

            @Override
            public InfoBean[] newArray(int size) {
                return new InfoBean[size];
            }
        };
    }
}
