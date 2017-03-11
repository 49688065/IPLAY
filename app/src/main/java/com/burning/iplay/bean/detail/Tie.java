package com.burning.iplay.bean.detail;

import com.burning.iplay.bean.comment.Comment;

import java.util.List;

/**
 * @author Kiven
 * @time 2017-2-15  15:09
 * Email f842728368@163.com
 * @desc 评论的所有数据
 */
public class Tie {

    private List<Comment> comments;
    private List<String> commentIds;

    @Override
    public String toString() {
        return "Tie{" +
                "comments=" + comments +
                ", commentIds=" + commentIds +
                '}';
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }
}
