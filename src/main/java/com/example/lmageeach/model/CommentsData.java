package com.example.lmageeach.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("comments")
public class CommentsData {

    public int id;//主键
    public String commentators;//评论人
    public String lmageId;//图片id
    public String content;//内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentators() {
        return commentators;
    }

    public void setCommentators(String commentators) {
        this.commentators = commentators;
    }

    public String getLmageId() {
        return lmageId;
    }

    public void setLmageId(String lmageId) {
        this.lmageId = lmageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
