package com.example.lmageeachtwo.model;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName("concern")
public class ConcernData {

    public int id;//主键
    public int userId;//关注人id
    public int authorId;//被关注人id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
