package com.example.lmageeach.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("support")
public class SupportData {

    public int id;//主键
    public int userId;//点赞人id
    public int lmageId;//图片id

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

    public int getLmageId() {
        return lmageId;
    }

    public void setLmageId(int lmageId) {
        this.lmageId = lmageId;
    }
}
