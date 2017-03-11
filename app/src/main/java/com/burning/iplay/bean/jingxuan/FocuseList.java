package com.burning.iplay.bean.jingxuan;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-19  23:37
 * Email f842728368@163.com
 * @desc 关注--游戏专区列表
 */
public class FocuseList {

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

    public static class InfoBean {
        /**
         * addToTask : false
         * banner :
         * followUserCount : 16930
         * id : 37
         * idxpic : http://img2.cache.netease.com/game/app/Game/blz/bx_list_996_346.png
         * ifOrder : false
         * listImg :
         * newIcon : http://img2.cache.netease.com/game/app/Game/blz/bx_icon_220_220.png
         * newIconBgImg : http://img2.cache.netease.com/game/app/Game/blz/bx_detail_1080_480.png
         * recommendNum : 0
         * topicDescription : 暴雪粉兴趣大本营
         * topicIconRectangleUrl : http://img2.cache.netease.com/game/app/Game/blz/bx_guide_660_419.png
         * topicId : T1461396489605
         * topicName : 暴雪资讯
         * topicType : 3
         * weight : 95
         */

        private boolean addToTask;
        private String banner;
        private int followUserCount;
        private int id;
        private String idxpic;
        private boolean ifOrder;
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

        public boolean isIfOrder() {
            return ifOrder;
        }

        public void setIfOrder(boolean ifOrder) {
            this.ifOrder = ifOrder;
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
    }
}
