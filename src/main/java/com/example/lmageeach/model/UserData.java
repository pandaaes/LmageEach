package com.example.lmageeach.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

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
    public LocalDate birthday;  //出生日期
    public String bio;      //个人简介
    public String email;        //电子邮件
    public int fans;        //粉丝
    public int  concern;    //关注
    public int love;        //喜欢
    public int download;    //下载
    public int total;       //作品总数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUrban() {
        return urban;
    }

    public void setUrban(String urban) {
        this.urban = urban;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getConcern() {
        return concern;
    }

    public void setConcern(int concern) {
        this.concern = concern;
    }

    public int getLove() {
        return love;
    }

    public void setLike(int love) {
        this.love = love;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
