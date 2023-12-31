package com.example.lmageeachtwo.model;


import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

@TableName("lmage")
public class LmageData {

    public int id;//主键
    public int lmageId;//图片id
    public String lmageName;//图片名称
    public byte[] lmageData;//图片
    public String lmageType;//图片类型
    public String labelName;//标签名称
    public String user_name;//作者
    public int support;//点赞
    public int downloads;//下载
    public String camera;//相机名称
    public LocalDate createTime;//上传日期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLmageId() {
        return lmageId;
    }

    public void setLmageId(int lmageId) {
        this.lmageId = lmageId;
    }

    public String getLmageName() {
        return lmageName;
    }

    public void setLmageName(String lmageName) {
        this.lmageName = lmageName;
    }

    public byte[] getLmageData() {
        return lmageData;
    }

    public void setLmageData(byte[] lmageData) {
        this.lmageData = lmageData;
    }

    public String getLmageType() {
        return lmageType;
    }

    public void setLmageType(String lmageType) {
        this.lmageType = lmageType;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }
}
