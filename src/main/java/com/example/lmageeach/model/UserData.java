package com.example.lmageeach.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserData {

    @TableId(value="id",type = IdType.AUTO)
    public int id;              //主键
    public String userId;       //用户id
    public String username;     //用户名称
    public String password;     //密码
    public String lastName;     //姓
    public String firstName;    //名
    public String urban;        //城市
    public String country;      //国家
    public String birthday;  //出生日期
    public String bio;      //个人简介
    public String email;        //电子邮件
    public int fans;        //粉丝
    public int  concern;    //关注
    public int love;        //喜欢
    public int download;    //下载
    public int total;       //作品总数
}
