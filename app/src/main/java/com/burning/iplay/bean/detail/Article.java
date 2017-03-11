package com.burning.iplay.bean.detail;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-15  15:08
 * Email f842728368@163.com
 * @desc 详情界面的新闻主题内容
 */
public class Article {

    private String title;
    private String body;
    private String docid;
    private List<Image> img;
    private String penName;
    private String ptime;
    private int replyCount;
    private List<Video> video;

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", docid='" + docid + '\'' +
                ", img=" + img +
                ", penName='" + penName + '\'' +
                ", ptime=" + ptime +
                ", replyCount=" + replyCount +
                ", video=" + video +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public List<Image> getImg() {
        return img;
    }

    public void setImg(List<Image> img) {
        this.img = img;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }
}
