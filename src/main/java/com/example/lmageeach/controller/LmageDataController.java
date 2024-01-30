package com.example.lmageeach.controller;


import com.example.lmageeach.model.CommentsData;
import com.example.lmageeach.model.LmageData;
import com.example.lmageeach.model.LmageUpload;
import com.example.lmageeach.model.SupportData;
import com.example.lmageeach.service.LabelDataSer.LabelDataServiceImpl;
import com.example.lmageeach.service.SupportDataSer.SupportDataServiceImpl;
import com.example.lmageeach.service.lmageDataSer.LmageDataServiceImpl;
import com.example.lmageeach.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/lmage")
public class LmageDataController {

    @Autowired
    private LmageDataServiceImpl lmageDataService;

    @Autowired
    private LabelDataServiceImpl labelDataService;

    @Resource
    private SupportDataServiceImpl supportDataService;


    /**
     * 上传图片
     * @param lmageUpload
     * @param session
     * @return
     */
    @RequestMapping("/upload")
    public Result lmageUpload( @RequestBody LmageUpload lmageUpload, HttpSession session){
        return lmageDataService.upload(lmageUpload,session);
    }



    /**
     * 查看所有作品
     * @return
     */
    @RequestMapping("/imageAll")
    public Result imageAll(){
        return lmageDataService.imageAll();
    }

    /**
     * 作品搜索
     * @param Name 搜索名称或者标签名称
     * @param type 1：名称搜索  2：标签搜索
     * @return
     */
    @RequestMapping("/search")
    public Result lmageSearch(@RequestParam("Name") String Name,@RequestParam("type") Integer type){
        return lmageDataService.lmageSearch(Name,type);
    }


    /**
     * 标签list
     * @return
     */
    @RequestMapping("/labelList")
    public Result labelList(){
        return labelDataService.labeList();
    }



    /**
     * 输入评论
     * @param commentsData
     * @param session
     * @return
     */
    @RequestMapping("/commentsWrite")
    public Result commentsWrite(@RequestBody CommentsData commentsData, HttpSession session){
        return lmageDataService.commentsWrite(commentsData,session);
    }

    /**
     * 评论查询
     * @param lmageId
     * @return
     */
    @RequestMapping("/showComments")
    public Result showComments(@RequestParam("lmage") String lmageId){
        return lmageDataService.showComments(lmageId);
    }


    /**
     *  作品点赞
     * @param supportData
     * @param type 1：点赞 2：取消点赞
     * @return
     */
    @RequestMapping("/support")
    public Result lmageSupport(@RequestParam("lmageId") SupportData supportData, @RequestParam("type") Integer type){
        return supportDataService.lmageSupport(supportData,type);
    }


}
