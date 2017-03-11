package com.burning.iplay.bean.forum;

import java.util.List;

/**
 * @FileName ForumHotBean
 * @Author Jay_Ping
 * @Time 2017/3/6
 * @Email 1054335787@qq.com
 * @Desc 社区 热门推荐中的bean
 */
public class ForumHotBean {

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

        //第二个头部
        private int onlineUserNum;
        /**
         * id : 44
         * img : http://img1.cache.netease.com/photo/0031/2017-03-03/CEKFQ7I671570031.jpg
         * priority : 108
         * subtitleOne : #活动#:参与话题讨论拿小米手环
         * subtitleTwo : 你最喜欢的游戏女神是谁呢？
         * tid : 173396145
         * title : #活动#参与话题拿小米手环
         * url :
         */

        //第一个头部
        private List<FocusListBean> focusList;


        //列表
        private List<ThreadListBean> threadList;

        public int getOnlineUserNum() {
            return onlineUserNum;
        }

        public void setOnlineUserNum(int onlineUserNum) {
            this.onlineUserNum = onlineUserNum;
        }

        public List<FocusListBean> getFocusList() {
            return focusList;
        }

        public void setFocusList(List<FocusListBean> focusList) {
            this.focusList = focusList;
        }

        public List<ThreadListBean> getThreadList() {
            return threadList;
        }

        public void setThreadList(List<ThreadListBean> threadList) {
            this.threadList = threadList;
        }

        //社区热门第一个头部
        public static class FocusListBean {
            private int id;
            private String img;
            private int priority;
            private String subtitleOne;
            private String subtitleTwo;
            private String tid;
            private String title;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
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

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "FocusListBean{" +
                        "id=" + id +
                        ", img='" + img + '\'' +
                        ", priority=" + priority +
                        ", subtitleOne='" + subtitleOne + '\'' +
                        ", subtitleTwo='" + subtitleTwo + '\'' +
                        ", tid='" + tid + '\'' +
                        ", title='" + title + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }

        /**
         * author : 星邪龙隐
         * authorid : 1254576
         * dateline : 1488697196
         * digest : 0
         * fid : 398
         * fidColor : ee4b62
         * fname : 玩家大厅
         * highlight : 0
         * img2Url :
         * img3Url :
         * imgUrl :
         * lastpost : 1488796668
         * lastposter : 网名
         * lastposterid : 212680
         * replies : 4
         * replyList : [{"id":187,"replyTieContent":"贪吃蛇","replyUserName":"三木控","tieId":600}]
         * showType : 4
         * subject : 讲道理……上手玩过之后被扔掉的游戏数不胜数，同学，说出你的故事
         * subtitle : 讲道理……上手玩过之后被扔掉的游戏数不胜数，同学，说出你的故事
         * tid : 173396408
         * title : 有没有初期觉得很好玩后期却一点也不想玩的游戏？
         * typeid : 1101
         * typename : 讨论
         * views : 86
         * weight : 619
         */
        public static class ThreadListBean {
            private String author;
            private String authorid;
            private String dateline;
            private String digest;
            private String fid;
            private String fidColor;
            private String fname;
            private String highlight;
            private String img2Url;
            private String img3Url;
            private String imgUrl;
            private String lastpost;
            private String lastposter;
            private String lastposterid;
            private String replies;
            private int showType;
            private String subject;
            private String subtitle;
            private String tid;
            private String title;
            private String typeid;
            private String typename;
            private String views;
            private int weight;



            private List<ReplyListBean> replyList;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAuthorid() {
                return authorid;
            }

            public void setAuthorid(String authorid) {
                this.authorid = authorid;
            }

            public String getDateline() {
                return dateline;
            }

            public void setDateline(String dateline) {
                this.dateline = dateline;
            }

            public String getDigest() {
                return digest;
            }

            public void setDigest(String digest) {
                this.digest = digest;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getFidColor() {
                return fidColor;
            }

            public void setFidColor(String fidColor) {
                this.fidColor = fidColor;
            }

            public String getFname() {
                return fname;
            }

            public void setFname(String fname) {
                this.fname = fname;
            }

            public String getHighlight() {
                return highlight;
            }

            public void setHighlight(String highlight) {
                this.highlight = highlight;
            }

            public String getImg2Url() {
                return img2Url;
            }

            public void setImg2Url(String img2Url) {
                this.img2Url = img2Url;
            }

            public String getImg3Url() {
                return img3Url;
            }

            public void setImg3Url(String img3Url) {
                this.img3Url = img3Url;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLastpost() {
                return lastpost;
            }

            public void setLastpost(String lastpost) {
                this.lastpost = lastpost;
            }

            public String getLastposter() {
                return lastposter;
            }

            public void setLastposter(String lastposter) {
                this.lastposter = lastposter;
            }

            public String getLastposterid() {
                return lastposterid;
            }

            public void setLastposterid(String lastposterid) {
                this.lastposterid = lastposterid;
            }

            public String getReplies() {
                return replies;
            }

            public void setReplies(String replies) {
                this.replies = replies;
            }

            public int getShowType() {
                return showType;
            }

            public void setShowType(int showType) {
                this.showType = showType;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public List<ReplyListBean> getReplyList() {
                return replyList;
            }

            public void setReplyList(List<ReplyListBean> replyList) {
                this.replyList = replyList;
            }


            /**
             * id : 187
             * replyTieContent : 贪吃蛇
             * replyUserName : 三木控
             * tieId : 600
             */
            public static class ReplyListBean {
                private int id;
                private String replyTieContent;
                private String replyUserName;
                private int tieId;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getReplyTieContent() {
                    return replyTieContent;
                }

                public void setReplyTieContent(String replyTieContent) {
                    this.replyTieContent = replyTieContent;
                }

                public String getReplyUserName() {
                    return replyUserName;
                }

                public void setReplyUserName(String replyUserName) {
                    this.replyUserName = replyUserName;
                }

                public int getTieId() {
                    return tieId;
                }

                public void setTieId(int tieId) {
                    this.tieId = tieId;
                }
            }

            @Override
            public String toString() {
                return "ThreadListBean{" +
                        "author='" + author + '\'' +
                        ", authorid='" + authorid + '\'' +
                        ", dateline='" + dateline + '\'' +
                        ", digest='" + digest + '\'' +
                        ", fid='" + fid + '\'' +
                        ", fidColor='" + fidColor + '\'' +
                        ", fname='" + fname + '\'' +
                        ", highlight='" + highlight + '\'' +
                        ", img2Url='" + img2Url + '\'' +
                        ", img3Url='" + img3Url + '\'' +
                        ", imgUrl='" + imgUrl + '\'' +
                        ", lastpost='" + lastpost + '\'' +
                        ", lastposter='" + lastposter + '\'' +
                        ", lastposterid='" + lastposterid + '\'' +
                        ", replies='" + replies + '\'' +
                        ", showType=" + showType +
                        ", subject='" + subject + '\'' +
                        ", subtitle='" + subtitle + '\'' +
                        ", tid='" + tid + '\'' +
                        ", title='" + title + '\'' +
                        ", typeid='" + typeid + '\'' +
                        ", typename='" + typename + '\'' +
                        ", views='" + views + '\'' +
                        ", weight=" + weight +
                        ", replyList=" + replyList +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "InfoBean{" +
                    "onlineUserNum=" + onlineUserNum +
                    ", focusList=" + focusList +
                    ", threadList=" + threadList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ForumHotBean{" +
                "code=" + code +
                ", info=" + info +
                ", writeNull=" + writeNull +
                '}';
    }
}
