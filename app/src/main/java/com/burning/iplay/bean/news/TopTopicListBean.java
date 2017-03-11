package com.burning.iplay.bean.news;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Kiven
 * @time 2017-2-17  15:04
 * Email f842728368@163.com
 * @desc 关注标题列表
 */
public class TopTopicListBean implements Parcelable {
    /**
     * addToTask : true
     * banner :
     * followUserCount : 6699
     * id : 63
     * idxpic : http://img2.cache.netease.com/game/app/Game/dota2/dota_list_996_346.png
     * listImg :
     * newIcon : http://img2.cache.netease.com/game/app/Game/dota2/dota_icon_220_220.png
     * newIconBgImg : http://img2.cache.netease.com/game/app/Game/dota2/dota_detail_1080_480.png
     * recommendNum : 0
     * topicDescription : 玩转全球顶级竞技游戏
     * topicIconRectangleUrl : http://img2.cache.netease.com/game/app/Game/dota2/dota_guide_660_419.png
     * topicId : T1461396524087
     * topicName : DOTA2
     * topicType : 3
     * weight : 76
     */

    private boolean addToTask;
    private String banner;
    private int followUserCount;
    private int id;
    private String idxpic;
    private String listImg;
    private String newIcon;
    private String newIconBgImg;
    private int recommendNum;
    private String topicDescription;
    private String topicIconRectangleUrl;
    private String topicId;
    private String topicName;
    private int topicType;
    private int weight;

    public boolean isAddToTask() {
        return addToTask;
    }

    public void setAddToTask(boolean addToTask) {
        this.addToTask = addToTask;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getFollowUserCount() {
        return followUserCount;
    }

    public void setFollowUserCount(int followUserCount) {
        this.followUserCount = followUserCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdxpic() {
        return idxpic;
    }

    public void setIdxpic(String idxpic) {
        this.idxpic = idxpic;
    }

    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    public String getNewIcon() {
        return newIcon;
    }

    public void setNewIcon(String newIcon) {
        this.newIcon = newIcon;
    }

    public String getNewIconBgImg() {
        return newIconBgImg;
    }

    public void setNewIconBgImg(String newIconBgImg) {
        this.newIconBgImg = newIconBgImg;
    }

    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getTopicIconRectangleUrl() {
        return topicIconRectangleUrl;
    }

    public void setTopicIconRectangleUrl(String topicIconRectangleUrl) {
        this.topicIconRectangleUrl = topicIconRectangleUrl;
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

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    //************************* Parcel化代码 ******************************/


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.addToTask ? (byte) 1 : (byte) 0);
        dest.writeString(this.banner);
        dest.writeInt(this.followUserCount);
        dest.writeInt(this.id);
        dest.writeString(this.idxpic);
        dest.writeString(this.listImg);
        dest.writeString(this.newIcon);
        dest.writeString(this.newIconBgImg);
        dest.writeInt(this.recommendNum);
        dest.writeString(this.topicDescription);
        dest.writeString(this.topicIconRectangleUrl);
        dest.writeString(this.topicId);
        dest.writeString(this.topicName);
        dest.writeInt(this.topicType);
        dest.writeInt(this.weight);
    }

    public TopTopicListBean() {
    }

    protected TopTopicListBean(Parcel in) {
        this.addToTask = in.readByte() != 0;
        this.banner = in.readString();
        this.followUserCount = in.readInt();
        this.id = in.readInt();
        this.idxpic = in.readString();
        this.listImg = in.readString();
        this.newIcon = in.readString();
        this.newIconBgImg = in.readString();
        this.recommendNum = in.readInt();
        this.topicDescription = in.readString();
        this.topicIconRectangleUrl = in.readString();
        this.topicId = in.readString();
        this.topicName = in.readString();
        this.topicType = in.readInt();
        this.weight = in.readInt();
    }

    public static final Creator<TopTopicListBean> CREATOR = new Creator<TopTopicListBean>() {
        @Override
        public TopTopicListBean createFromParcel(Parcel source) {
            return new TopTopicListBean(source);
        }

        @Override
        public TopTopicListBean[] newArray(int size) {
            return new TopTopicListBean[size];
        }
    };


    @Override
    public String toString() {
        return "TopTopicListBean{" +
                "addToTask=" + addToTask +
                ", banner='" + banner + '\'' +
                ", followUserCount=" + followUserCount +
                ", id=" + id +
                ", idxpic='" + idxpic + '\'' +
                ", listImg='" + listImg + '\'' +
                ", newIcon='" + newIcon + '\'' +
                ", newIconBgImg='" + newIconBgImg + '\'' +
                ", recommendNum=" + recommendNum +
                ", topicDescription='" + topicDescription + '\'' +
                ", topicIconRectangleUrl='" + topicIconRectangleUrl + '\'' +
                ", topicId='" + topicId + '\'' +
                ", topicName='" + topicName + '\'' +
                ", topicType=" + topicType +
                ", weight=" + weight +
                '}';
    }
}
