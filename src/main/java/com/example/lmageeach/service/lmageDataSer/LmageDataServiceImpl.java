package com.example.lmageeach.service.lmageDataSer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.CommentsDataMapper;
import com.example.lmageeach.mapper.LabelDataMapper;
import com.example.lmageeach.mapper.LmageDataMapper;
import com.example.lmageeach.mapper.UserDataMapper;
import com.example.lmageeach.model.CommentsData;
import com.example.lmageeach.model.LabelData;
import com.example.lmageeach.model.LmageData;
import com.example.lmageeach.model.UserData;
import com.example.lmageeach.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class LmageDataServiceImpl extends ServiceImpl<LmageDataMapper, LmageData> implements LmageDataService {

    private final String filePath = "D:\\idea\\test\\LmageEach\\src\\main\\resources\\Image\\";

    @Resource
    private LmageDataMapper lmageDataMapper;

    @Resource
    private UserDataMapper userDataMapper;

    @Resource
    private LabelDataMapper labelDataMapper;

    @Resource
    private CommentsDataMapper commentsDataMapper;

    public Result upload(MultipartFile file,LmageData lmageData, HttpSession session) {

        // 检查文件是否为空
        if (file.isEmpty()) {
            return Result.fail("请选择文件");
        }

        //检查文件系统是否存在
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File destFile = new File(filePath + lmageData.getLmageName());

        try {
            //保存文件和地址
            file.transferTo(destFile);
            lmageData.setLmageData(String.valueOf(destFile.getAbsolutePath()));

            //设置图片id
            String lmageUUID = UUID.randomUUID().toString();
            lmageData.setLmageId(lmageUUID);

            //图片标签
            QueryWrapper<LabelData> labelDataQueryWrapper = new QueryWrapper<>();
            labelDataQueryWrapper.eq("labelName",lmageData.getLabelName());
            List<LabelData> newLabelData = labelDataMapper.selectList(labelDataQueryWrapper);
            if (newLabelData.isEmpty()){
                LabelData labelData = new LabelData();
                labelData.setLabelName(lmageData.getLabelName());
                labelData.setTotal(labelData.total++);
                labelDataMapper.insert(labelData);
            }else {
                UpdateWrapper<LabelData> update = Wrappers.update();
                update.eq("labelName",lmageData.getLmageName());
                update.setSql("total = total + 1");
                labelDataMapper.update(null,update);
            }

            //作者
            QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
            userDataQueryWrapper.eq("userId",session.getAttribute("token"));
            UserData userData = userDataMapper.selectOne(userDataQueryWrapper);
            lmageData.setUser_name(userData.getUsername());

            //时间
            lmageData.setCreateTime(LocalDate.now());

            lmageDataMapper.insert(lmageData);
        }catch (IOException e){
            e.printStackTrace();
            return Result.fail("文件保存失败");
        }

        return Result.ok("文件上传成功");
    }

    //评论写入
    public Result commentsWrite(CommentsData commentsData, HttpSession session) {
//        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
//        userDataQueryWrapper.eq("userId",session.getAttribute("token"));
//        UserData userData = userDataMapper.selectOne(userDataQueryWrapper);
        commentsDataMapper.insert(commentsData);
        return Result.ok("评论成功");
    }


    //评论查询
    public Result showComments(String lmageId) {
        QueryWrapper<CommentsData> commentsDataQueryWrapper = new QueryWrapper<>();
        commentsDataQueryWrapper.eq("lmageId",lmageId);
        List<CommentsData> commentsData = commentsDataMapper.selectList(commentsDataQueryWrapper);
        return Result.ok(commentsData);
    }

    //作品搜索
    public Result lmageSearch(String lmageName,Integer type) {
        if (type == 1){
            QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
            lmageDataQueryWrapper.eq("lmageName",lmageName);
            lmageDataMapper.selectList(lmageDataQueryWrapper);
            return Result.ok(lmageDataMapper.selectList(lmageDataQueryWrapper));
        }

        if (type == 2){
            QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
            lmageDataQueryWrapper.eq("labelName",lmageName);
            lmageDataMapper.selectList(lmageDataQueryWrapper);
            return Result.ok(lmageDataMapper.selectList(lmageDataQueryWrapper));
        }

        return Result.fail("无作品");
    }
}