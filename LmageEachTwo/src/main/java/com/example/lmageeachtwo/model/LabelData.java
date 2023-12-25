package com.example.lmageeachtwo.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("label")
public class LabelData {

    public int id;//主键
    public String labelName;//标签名称
    public int total;//总数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
