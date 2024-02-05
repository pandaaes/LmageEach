package com.example.lmageeach.service.collectionSer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.CollectionDataMapper;
import com.example.lmageeach.mapper.LmageDataMapper;
import com.example.lmageeach.model.CollectionData;
import com.example.lmageeach.model.LmageData;
import com.example.lmageeach.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CollectionDataServiceImpl extends ServiceImpl<CollectionDataMapper, CollectionData> implements CollectionDataService {


    @Autowired
    private CollectionDataMapper collectionDataMapper;

    @Resource
    private LmageDataMapper lmageDataMapper;

    public Result lmageCollection(String userid, String lmageId, Integer type) {
        CollectionData collectionData = new CollectionData();
        collectionData.setUserId(userid);
        collectionData.setLmageId(lmageId);
        if (type==1){
            QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
            lmageDataQueryWrapper.eq("lmage_id",lmageId);
            LmageData lmageData = lmageDataMapper.selectOne(lmageDataQueryWrapper);
            if (lmageData != null){
                lmageData.setCollection(lmageData.getCollection()+1);
                UpdateWrapper<LmageData> update = Wrappers.update();
                update.eq("lmage_id",lmageId);
                lmageDataMapper.update(lmageData,update);
                collectionDataMapper.insert(collectionData);
                return Result.ok("收藏成功");
            }else {
                return Result.fail("无图片");
            }
        }

        if (type == 2){
            UpdateWrapper<LmageData> update = Wrappers.update();
            update.eq("lmage_id",collectionData.lmageId);
            update.setSql("collection = collection - 1");
            lmageDataMapper.update(null,update);

            QueryWrapper<CollectionData> collectionDataQueryWrapper = new QueryWrapper<>();
            collectionDataQueryWrapper.eq("user_id",collectionData.getUserId()).eq("lmage_id",collectionData.getLmageId());
            collectionDataMapper.delete(collectionDataQueryWrapper);

            return Result.ok("已取消收藏");
        }

        return Result.fail("系统错误");
    }
}
