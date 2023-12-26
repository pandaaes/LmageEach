package com.example.lmageeach.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@TableName("support")
@Data
@AllArgsConstructor
public class SupportData {

    public int id;//主键
    public String userId;//点赞人id
    public String lmageId;//图片id

}
