package com.example.lmageeach.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lmage")
public class LmageData {

    @TableId(value = "id", type = IdType.AUTO)
    public int id;//主键
    public String lmageId;//图片id
    public String lmageName;//图片名称
    public String lmageData;//图片地址
    public String lmageType;//图片类型
    public String labelName;//标签名称
    public String userName;//作者
    public int support;//点赞
    public int downloads;//下载
    public String camera;//相机名称
    public LocalDate createTime;//上传日期
    public String lmageLocal;//本地文件地址
    public int views;//浏览
}