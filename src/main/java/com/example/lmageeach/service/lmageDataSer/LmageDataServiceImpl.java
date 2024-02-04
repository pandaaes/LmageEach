package com.example.lmageeach.service.lmageDataSer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.CommentsDataMapper;
import com.example.lmageeach.mapper.LabelDataMapper;
import com.example.lmageeach.mapper.LmageDataMapper;
import com.example.lmageeach.mapper.UserDataMapper;
import com.example.lmageeach.model.*;
import com.example.lmageeach.util.OSSService;
import com.example.lmageeach.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
public class LmageDataServiceImpl extends ServiceImpl<LmageDataMapper, LmageData> implements LmageDataService {

//    private final String filePath = "D:\\idea\\test\\LmageEach\\src\\main\\resources\\Image\\";

    @Resource
    private LmageDataMapper lmageDataMapper;

    @Resource
    private UserDataMapper userDataMapper;

    @Resource
    private LabelDataMapper labelDataMapper;

    @Resource
    private CommentsDataMapper commentsDataMapper;

    @Resource
    private OSSService ossService;


    /**
     * 文件上传
     * @return
     */
//    public Result upload(LmageUpload lmageUpload, HttpSession session) {
      public Result upload(MultipartFile file,String lmageName,String labelName,String lmageType, HttpSession session) {

        LmageData lmageData = new LmageData();
        lmageData.setLmageName(lmageName);
        lmageData.setLabelName(labelName);
        lmageData.setLmageType(lmageType);
        //检查文件是否为空
        if (file.isEmpty()) {
            return Result.fail("请选择文件");
        }

        //检查文件系统是否存在
//        String filePath = new File("").getAbsolutePath()+"/src/main/resources/static/images/";
          String filePath = new File("").getAbsolutePath()+"/image/";
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        File destFile = new File(filePath + name);

        try {
            //图片上传oss
            String fileURL = ossService.checkImage(file);

            if (fileURL == null )
                return Result.fail("上传失败");

            //保存文件和地址
            file.transferTo(destFile);
//            lmageData.setLmageLocal(destFile.toString());
            lmageData.setLmageLocal(name);
            lmageData.setLmageData(fileURL);

            //设置图片id
            String lmageUUID = UUID.randomUUID().toString();
            lmageData.setLmageId(lmageUUID);

            //图片标签
            String[] labelList = labelName.split(",");
            for (String s : labelList) {
                QueryWrapper<LabelData> labelDataQueryWrapper = new QueryWrapper<>();
                labelDataQueryWrapper.eq("label_name",s);
                List<LabelData> newLabelData = labelDataMapper.selectList(labelDataQueryWrapper);
                if (newLabelData.isEmpty()){
                    LabelData labelData = new LabelData();
                    labelData.setLabelName(s);
                    labelData.setTotal(labelData.total++);
                    labelDataMapper.insert(labelData);
                }else {
                    UpdateWrapper<LabelData> update = Wrappers.update();
                    update.eq("label_name",s);
                    update.setSql("total = total + 1");
                    labelDataMapper.update(null,update);
                }

            }

            //作者
            QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
            userDataQueryWrapper.eq("user_id",session.getAttribute("token"));
            UserData userData = userDataMapper.selectOne(userDataQueryWrapper);
            lmageData.setUserName(userData.getUsername());

            //时间
            lmageData.setCreateTime(LocalDate.now());

            lmageDataMapper.insert(lmageData);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("文件保存失败");
        }

        return Result.ok("文件上传成功");
    }



    /**
     * 评论
     * @param commentsData
     * @param session
     * @return
     */
    public Result commentsWrite(CommentsData commentsData, HttpSession session) {
//        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
//        userDataQueryWrapper.eq("userId",session.getAttribute("token"));
//        UserData userData = userDataMapper.selectOne(userDataQueryWrapper);
        commentsDataMapper.insert(commentsData);
        return Result.ok("评论成功");
    }




    /**
     * 评论查询
     * @param lmageId
     * @return
     */
    public Result showComments(String lmageId) {
        QueryWrapper<CommentsData> commentsDataQueryWrapper = new QueryWrapper<>();
        commentsDataQueryWrapper.eq("lmage_id",lmageId);
        List<CommentsData> commentsData = commentsDataMapper.selectList(commentsDataQueryWrapper);
        return Result.ok(commentsData);
    }

    /**
     * 作品搜索
     * @param Name
     * @param type
     * @return
     */
    public Result lmageSearch(String Name,Integer type) {
        if (type == 1){
            QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
            lmageDataQueryWrapper.eq("lmage_name",Name);
            lmageDataMapper.selectList(lmageDataQueryWrapper);
            return Result.ok(lmageDataMapper.selectList(lmageDataQueryWrapper));
        }

        if (type == 2){
            QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
            lmageDataQueryWrapper.eq("label_name",Name);
            lmageDataMapper.selectList(lmageDataQueryWrapper);
            return Result.ok(lmageDataMapper.selectList(lmageDataQueryWrapper));
        }

        return Result.ok();
    }



    /**
     * 查看所有作品
     * @return
     */
    public Result imageAll() {
        return Result.ok(lmageDataMapper.selectList(null));
    }


    /**
     * 上传ossces Test
     * @param file
     * @return
     */
    public Result uploadTest(MultipartFile file) {
        return ossService.uploadTest(file);
    }

    public Result views(String lmageId) {
        QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
        lmageDataQueryWrapper.eq("lmage_id",lmageId);
        LmageData lmageData = lmageDataMapper.selectOne(lmageDataQueryWrapper);
        if (lmageData != null){
            lmageData.setViews(lmageData.getViews()+1);
            UpdateWrapper<LmageData> update = Wrappers.update();
            update.eq("lmage_id",lmageId);
//        update.setSql("views = views + 1");
            lmageDataMapper.update(lmageData,update);
            return Result.ok();
        }
        return Result.fail("无图片数据");
    }

    public Result downloads(String lmageId) {
        UpdateWrapper<LmageData> update = Wrappers.update();
        update.eq("lmage_id",lmageId);
        update.setSql("downloads = downloads + 1");
        lmageDataMapper.update(null,update);
        return Result.ok();
    }
}
