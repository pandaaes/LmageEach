package com.example.lmageeach.controller;

import com.example.lmageeach.model.ConcernData;
import com.example.lmageeach.model.SupportData;
import com.example.lmageeach.model.UserData;
import com.example.lmageeach.service.ConcernDataSer.ConcernDataServiceImpl;
import com.example.lmageeach.service.SupportDataSer.SupportDataServiceImpl;
import com.example.lmageeach.service.UserDataSer.UserDataServiceImpl;
import com.example.lmageeach.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserDataController {

    @Resource
    private UserDataServiceImpl userDataService;

    @Resource
    private SupportDataServiceImpl supportDataService;

    @Resource
    private ConcernDataServiceImpl concernDataService;

    /**
     * 登录
     * @param userData
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestBody UserData userData, HttpSession session){
        return userDataService.login(userData,session);
    }

    /**
     * 个人信息查询
     * @param session
     * @return
     */
    @RequestMapping("/Information")
    public Result userInformation(HttpSession session){
        return userDataService.userInformation(session);
    }

    /**
     * 修改个人信息
     * @param userData
     * @param session
     * @return
     */
    @RequestMapping("/modifyInformation")
    public Result modifyInformation(@RequestBody UserData userData,HttpSession session){
        return userDataService.modifyInformation(userData,session);
    }

    /**
     * 个人作品查询
     * @param userData
     * @param session
     * @return
     */
    @RequestMapping("/artwork")
    public Result userArtwork(@RequestBody UserData userData,HttpSession session){
        return userDataService.artwork(userData,session);
    }

    /**
     * 作者关注
     * @param concernData
     * @return
     */
    @RequestMapping("/concern")
    public Result concern(@RequestBody ConcernData concernData){
        return userDataService.concern(concernData);
    }

    /**
     * 查看点赞作品
     * @param supportData
     * @return
     */
    @RequestMapping("showSupport")
    public Result showSupport(@RequestBody SupportData supportData){
        return supportDataService.showSupport(supportData);
    }

    /**
     * 查看关注
     * @param userId
     * @return
     */
    @RequestMapping("showConcern")
    public Result showConcern(@RequestParam("userId") String userId){
        return concernDataService.showConcern(userId);
    }

    /**
     * 查看粉丝
     * @param userId
     * @return
     */
    @RequestMapping("showBeConcern")
    public Result showBeConcern(@RequestParam("userId") String userId){
        return concernDataService.showBeConcern(userId);
    }
}
