package com.burning.iplay.bean.comment;

/**
 * @author Kiven
 * @time 2017-3-7  10:35
 * Email f842728368@163.com
 * @desc 评论的Bean
 */
public class Comment {


    /**
     * commentId : 39775112
     * content : 然而贴吧大神早已万分
     * createTime : 2017-03-06 17:42:46
     * user
     * vote : 137
     */

    private int commentId;
    private int vote;
    private String createTime;
    private String content;
    private UserBean user;


    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", vote=" + vote +
                ", createTime='" + createTime + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
