package com.example.lmageeach.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionData {

    public int id;//主键
    public String userId;//收藏人id
    public String lmageId;//图片id

}
