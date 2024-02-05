package com.example.lmageeach.controller;


import com.example.lmageeach.model.CommentsData;
import com.example.lmageeach.service.LabelDataSer.LabelDataServiceImpl;
import com.example.lmageeach.service.SupportDataSer.SupportDataServiceImpl;
import com.example.lmageeach.service.collectionSer.CollectionDataServiceImpl;
import com.example.lmageeach.service.lmageDataSer.LmageDataServiceImpl;
import com.example.lmageeach.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/lmage")
public class LmageDataController {

    @Autowired
    private LmageDataServiceImpl lmageDataService;

    @Autowired
    private LabelDataServiceImpl labelDataService;

    @Autowired
    private SupportDataServiceImpl supportDataService;

    @Autowired
    private CollectionDataServiceImpl collectionDataService;


    /**
     * 上传图片
     * @param session
     * @return
     */
    @RequestMapping("/upload")
    public Result lmageUpload( @RequestParam("file") MultipartFile file,@RequestParam("lmageName") String lmageName,
                               @RequestParam("labelNamee") String labelName,@RequestParam("lmageType") String lmageType,
//                               @RequestBody LmageUpload lmageUpload,
                               HttpSession session){
        return lmageDataService.upload(file,lmageName,labelName,lmageType,session);
//        return lmageDataService.upload(lmageUpload,session);
    }

    @RequestMapping("/uploadTest")
    public Result lmageUploadTest( @RequestParam("file") MultipartFile file){
        return lmageDataService.uploadTest(file);
    }

    @RequestMapping("/{imageName}")
    public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        // 构建图片资源路径
        String imagePath = new File("").getAbsolutePath()+"/image/"+imageName;
//        String imagePath = "static/images/"+imageName;
        File imageFile = new File(imagePath);

        // 读取图片文件
//        Resource resource = new ClassPathResource(imagePath);
//        InputStream inputStream = resource.getInputStream();
        FileInputStream inputStream = new FileInputStream(imageFile);
        byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);

        // 设置响应头部
        response.setContentType("image/jpeg");
        response.getOutputStream().write(imageBytes);
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
     * @param type 1：点赞 2：取消点赞
     * @return
     */
    @RequestMapping("/support")
    public Result lmageSupport(@RequestParam("userid") String userid,@RequestParam("lmageId") String lmageId, @RequestParam("type") Integer type){
        return supportDataService.lmageSupport(userid,lmageId,type);
    }

    /**
     *  作品收藏
     * @param type 1：收藏 2：取消收藏
     * @return
     */
    @RequestMapping("/collection")
    public Result lmageCollection(@RequestParam("userid") String userid,@RequestParam("lmageId") String lmageId, @RequestParam("type") Integer type){
        return collectionDataService.lmageCollection(userid,lmageId,type);
    }

    /**
     * 浏览
     * @return
     */
    @RequestMapping("/views")
    public Result lmageViews(@RequestParam("lmageId") String lmageId){
        return lmageDataService.views(lmageId);
    }

    /**
     * 下载
     * @return
     */
    @RequestMapping("/downloads")
    public Result lmageDownloads(@RequestParam("lmageId") String lmageId){
        return lmageDataService.downloads(lmageId);
    }


}
