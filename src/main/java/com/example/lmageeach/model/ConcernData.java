package com.example.lmageeach.model;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@TableName("concern")
@Data
@AllArgsConstructor
public class ConcernData {

    public int id;//主键
    public String userId;//关注人id
    public String authorId;//被关注人id

}
