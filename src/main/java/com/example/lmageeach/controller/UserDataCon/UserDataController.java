package com.example.lmageeach.controller.UserDataCon;

import com.example.lmageeach.model.UserData;
import com.example.lmageeach.service.UserDataSer.UserDataServiceImp.UserDataServiceImpl;
import com.example.lmageeach.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/wudi")
public class UserDataController {

    @Resource
    private UserDataServiceImpl userDataService;

    //登录
    @RequestMapping("/login")
    public Result login(@RequestBody UserData userData, HttpSession session){
        return userDataService.login(userData,session);
    }

    //个人信息查询
    @RequestMapping("/userInformation")
    public Result userInformation(HttpSession session){
        return userDataService.userInformation(session);
    }

    //修改个人信息
    @RequestMapping("/modifyInformation")
    public Result modifyInformation(@RequestBody UserData userData,HttpSession session){
        return userDataService.modifyInformation(userData,session);
    }
}
