package com.example.lmageeach.controller;

import com.example.lmageeach.model.CommentsData;
import com.example.lmageeach.model.LmageData;
import com.example.lmageeach.model.SupportData;
import com.example.lmageeach.service.LabelDataSer.LabelDataServiceImpl;
import com.example.lmageeach.service.SupportDataSer.SupportDataService;
import com.example.lmageeach.service.SupportDataSer.SupportDataServiceImpl;
import com.example.lmageeach.service.lmageDataSer.LmageDataServiceImpl;
import com.example.lmageeach.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/lmage")
public class LmageDataController {

    @Autowired
    private LmageDataServiceImpl lmageDataService;

    @Autowired
    private LabelDataServiceImpl labelDataService;

    @Resource
    private SupportDataServiceImpl supportDataService;

    //上传
    @RequestMapping("/upload")
    public Result lmageUpload(@RequestParam("image") MultipartFile file, @RequestBody LmageData lmageData, HttpSession session){
        return lmageDataService.upload(file,lmageData,session);
    }

    //查看所有作品
    @RequestMapping("/imageAll")
    public Result imageAll(){
        return lmageDataService.imageAll();
    }

    /**
     * 作品搜索
     * @param lmageName 搜索名称或者标签名称
     * @param type 1：名称搜索  2：标签搜索
     * @return
     */
    @RequestMapping("/search")
    public Result lmageSearch(@RequestParam("lmageName") String lmageName,@RequestParam("type") Integer type){
        return lmageDataService.lmageSearch(lmageName,type);
    }


    //标签list
    @RequestMapping("/labelList")
    public Result labelList(){
        return labelDataService.labeList();
    }


    //输入评论
    @RequestMapping("/commentsWrite")
    public Result commentsWrite(@RequestBody CommentsData commentsData, HttpSession session){
        return lmageDataService.commentsWrite(commentsData,session);
    }

    //评论查询
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
