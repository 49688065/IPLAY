package com.burning.iplay.bean.detail;

import java.util.List;

/**
 * @author lam
 * @time 2017-03-06  16:04
 * @desc 社区-爱玩社区的bean
 */
public class IplayCommunity {
    /**
     * code : 0
     * info : {"discuzList":[{"detailList":[{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/398_new.jpg","discuzModelTypeId":1,"fid":398,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/398_new2.png","modelDesc":"综合游戏讨论区","modelName":"玩家大厅","posts":78254,"recommendList":1,"specialRedirect":0,"threads":1837,"todayPosts":63,"top":0,"weight":100},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/416_new.jpg","discuzModelTypeId":1,"fid":416,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/416_new2.png","modelDesc":"游戏交流与推荐","modelName":"STEAM专区","posts":19635,"recommendList":1,"specialRedirect":0,"threads":873,"todayPosts":53,"top":0,"weight":99},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/417_new.png","discuzModelTypeId":1,"fid":417,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/417_new2.png","modelDesc":"分享会动的快乐","modelName":"GIF广场","posts":15835,"recommendList":1,"specialRedirect":0,"threads":782,"todayPosts":12,"top":0,"weight":92}],"type":{"category":0,"id":1,"typeName":"中心广场","weight":100}},{"detailList":[{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/421_new.jpg","discuzModelTypeId":1,"fid":421,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/421_new2.png","modelDesc":"天下侠客聚义厅","modelName":"剑网3茶庄","posts":82710,"recommendList":1,"specialRedirect":0,"threads":1911,"todayPosts":28,"top":0,"weight":99}],"type":{"category":0,"id":2,"typeName":"游戏大本营","weight":99}},{"detailList":[{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/icon/D3.png","discuzModelTypeId":4,"fid":0,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/D3_new2.png","modelDesc":"凯恩之角","modelName":"暗黑破坏神","recommendList":0,"specialRedirect":1,"todayPosts":586,"top":0,"weight":99},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/376.png","discuzModelTypeId":3,"fid":376,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/376_new2.png","modelDesc":"四种族经典归来","modelName":"魔兽世界","posts":15869,"recommendList":1,"specialRedirect":0,"threads":1773,"todayPosts":9,"top":0,"weight":98},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/375.png","discuzModelTypeId":3,"fid":375,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/375_new2.png","modelDesc":"团战一触即发","modelName":"守望先锋","posts":87190,"recommendList":1,"specialRedirect":0,"threads":4707,"todayPosts":32,"top":0,"weight":97},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/406.png","discuzModelTypeId":3,"fid":377,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/377_new2.png","modelDesc":"史诗级即时战略","modelName":"星际争霸","posts":9058,"recommendList":1,"specialRedirect":0,"threads":1679,"todayPosts":0,"top":0,"weight":95},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/354.png","discuzModelTypeId":3,"fid":354,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/354_new2.png","modelDesc":"策略类卡牌游戏","modelName":"炉石传说","posts":57031,"recommendList":1,"specialRedirect":0,"threads":5266,"todayPosts":28,"top":0,"weight":94},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/365.png","discuzModelTypeId":3,"fid":365,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/365_new2.png","modelDesc":"暴雪精英大荟萃","modelName":"风暴英雄","posts":127741,"recommendList":1,"specialRedirect":0,"threads":2887,"todayPosts":1,"top":0,"weight":67},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/377.png","discuzModelTypeId":3,"fid":406,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/406_new2.png","modelDesc":"即时战略经典","modelName":"魔兽争霸","posts":2272,"recommendList":1,"specialRedirect":0,"threads":576,"todayPosts":0,"top":0,"weight":63}],"type":{"category":0,"id":3,"typeName":"暴雪游戏","weight":98}},{"detailList":[{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/400_new.jpg","discuzModelTypeId":1,"fid":400,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/400_new2.png","modelDesc":"产品建议与反馈","modelName":"意见反馈","posts":7324,"recommendList":1,"specialRedirect":0,"threads":1693,"todayPosts":9,"top":0,"weight":90},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/433_new.jpg","discuzModelTypeId":1,"fid":433,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/433_new2.png","modelDesc":"对违规管理说NO","modelName":"爱玩议事厅","posts":89,"recommendList":1,"specialRedirect":0,"threads":25,"todayPosts":0,"top":0,"weight":90}],"type":{"category":0,"id":7,"typeName":"爱玩议会","weight":95}}]}
     * writeNull : false
     */

    private int code;
    private InfoBean info;
    private boolean writeNull;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public boolean isWriteNull() {
        return writeNull;
    }

    public void setWriteNull(boolean writeNull) {
        this.writeNull = writeNull;
    }

    public static class InfoBean {
        /**
         * detailList : [{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/398_new.jpg","discuzModelTypeId":1,"fid":398,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/398_new2.png","modelDesc":"综合游戏讨论区","modelName":"玩家大厅","posts":78254,"recommendList":1,"specialRedirect":0,"threads":1837,"todayPosts":63,"top":0,"weight":100},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/416_new.jpg","discuzModelTypeId":1,"fid":416,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/416_new2.png","modelDesc":"游戏交流与推荐","modelName":"STEAM专区","posts":19635,"recommendList":1,"specialRedirect":0,"threads":873,"todayPosts":53,"top":0,"weight":99},{"bannerUrl":"http://img2.cache.netease.com/game/app/bbs/banner/417_new.png","discuzModelTypeId":1,"fid":417,"iconUrl":"http://img2.cache.netease.com/game/app/bbs/icon/417_new2.png","modelDesc":"分享会动的快乐","modelName":"GIF广场","posts":15835,"recommendList":1,"specialRedirect":0,"threads":782,"todayPosts":12,"top":0,"weight":92}]
         * type : {"category":0,"id":1,"typeName":"中心广场","weight":100}
         */

        private List<DiscuzListBean> discuzList;

        public List<DiscuzListBean> getDiscuzList() {
            return discuzList;
        }

        public void setDiscuzList(List<DiscuzListBean> discuzList) {
            this.discuzList = discuzList;
        }

        public static class DiscuzListBean {
            @Override
            public String toString() {
                return "DiscuzListBean{" +
                        "type=" + type +
                        ", detailList=" + detailList +
                        '}';
            }

            /**
             * category : 0
             * id : 1
             * typeName : 中心广场
             * weight : 100
             */

            private TypeBean type;
            /**
             * bannerUrl : http://img2.cache.netease.com/game/app/bbs/banner/398_new.jpg
             * discuzModelTypeId : 1
             * fid : 398
             * iconUrl : http://img2.cache.netease.com/game/app/bbs/icon/398_new2.png
             * modelDesc : 综合游戏讨论区
             * modelName : 玩家大厅
             * posts : 78254
             * recommendList : 1
             * specialRedirect : 0
             * threads : 1837
             * todayPosts : 63
             * top : 0
             * weight : 100
             */

            private List<DetailListBean> detailList;

            public TypeBean getType() {
                return type;
            }

            public void setType(TypeBean type) {
                this.type = type;
            }

            public List<DetailListBean> getDetailList() {
                return detailList;
            }

            public void setDetailList(List<DetailListBean> detailList) {
                this.detailList = detailList;
            }

            public static class TypeBean {
                private int category;
                private int id;
                private String typeName;
                private int weight;

                public int getCategory() {
                    return category;
                }

                public void setCategory(int category) {
                    this.category = category;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTypeName() {
                    return typeName;
                }

                public void setTypeName(String typeName) {
                    this.typeName = typeName;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }
            }

            public static class DetailListBean {
                private String bannerUrl;
                private int discuzModelTypeId;
                private int fid;
                private String iconUrl;
                private String modelDesc;
                private String modelName;
                private int posts;
                private int recommendList;
                private int specialRedirect;
                private int threads;
                private int todayPosts;
                private int top;
                private int weight;

                public String getBannerUrl() {
                    return bannerUrl;
                }

                public void setBannerUrl(String bannerUrl) {
                    this.bannerUrl = bannerUrl;
                }

                public int getDiscuzModelTypeId() {
                    return discuzModelTypeId;
                }

                public void setDiscuzModelTypeId(int discuzModelTypeId) {
                    this.discuzModelTypeId = discuzModelTypeId;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public String getIconUrl() {
                    return iconUrl;
                }

                public void setIconUrl(String iconUrl) {
                    this.iconUrl = iconUrl;
                }

                public String getModelDesc() {
                    return modelDesc;
                }

                public void setModelDesc(String modelDesc) {
                    this.modelDesc = modelDesc;
                }

                public String getModelName() {
                    return modelName;
                }

                public void setModelName(String modelName) {
                    this.modelName = modelName;
                }

                public int getPosts() {
                    return posts;
                }

                public void setPosts(int posts) {
                    this.posts = posts;
                }

                public int getRecommendList() {
                    return recommendList;
                }

                public void setRecommendList(int recommendList) {
                    this.recommendList = recommendList;
                }

                public int getSpecialRedirect() {
                    return specialRedirect;
                }

                public void setSpecialRedirect(int specialRedirect) {
                    this.specialRedirect = specialRedirect;
                }

                public int getThreads() {
                    return threads;
                }

                public void setThreads(int threads) {
                    this.threads = threads;
                }

                public int getTodayPosts() {
                    return todayPosts;
                }

                public void setTodayPosts(int todayPosts) {
                    this.todayPosts = todayPosts;
                }

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }
            }
        }
    }
}
