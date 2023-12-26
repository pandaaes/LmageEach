package com.example.lmageeach.service.SupportDataSer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.LmageDataMapper;
import com.example.lmageeach.mapper.SupportDataMapper;
import com.example.lmageeach.model.LmageData;
import com.example.lmageeach.model.SupportData;
import com.example.lmageeach.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupportDataServiceImpl extends ServiceImpl<SupportDataMapper, SupportData> implements SupportDataService {

    @Resource
    private SupportDataMapper supportDataMapper;

    @Autowired
    private LmageDataMapper lmageDataMapper;

    //点赞功能
    public Result lmageSupport(SupportData supportData,Integer type) {
        if (type==1){
            UpdateWrapper<LmageData> update = Wrappers.update();
            update.eq("lmageId",supportData.getLmageId());
            update.setSql("support = support + 1");
            lmageDataMapper.update(null,update);
            supportDataMapper.insert(supportData);
            return Result.ok("点赞成功");
        }

        if (type == 2){
            UpdateWrapper<LmageData> update = Wrappers.update();
            update.eq("lmageId",supportData.lmageId);
            update.setSql("support = support - 1");
            lmageDataMapper.update(null,update);

            QueryWrapper<SupportData> supportDataQueryWrapper = new QueryWrapper<>();
            supportDataQueryWrapper.eq("userId",supportData.getUserId()).eq("lmageId",supportData.getLmageId());
            supportDataMapper.delete(supportDataQueryWrapper);

            return Result.ok("已取消点赞");
        }

        return Result.ok("操作失败");
    }


    //查看点赞作品
    public Result showSupport(SupportData supportData) {
        QueryWrapper<SupportData> supportDataQueryWrapper = new QueryWrapper<>();
        supportDataQueryWrapper.eq("userId",supportData.getUserId());
        List<SupportData> supportData1 = supportDataMapper.selectList(supportDataQueryWrapper);
        List<String> list = new ArrayList<>();
        for (SupportData data : supportData1) {
            list.add(data.getLmageId());
        }
        QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
        lmageDataQueryWrapper.in("lmageId",list);
//        lmageDataMapper.selectList(lmageDataQueryWrapper);
        return Result.ok(lmageDataMapper.selectList(lmageDataQueryWrapper));
    }
}
