package com.example.lmageeach.service.ConcernDataSer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.ConcernDataMapper;
import com.example.lmageeach.mapper.UserDataMapper;
import com.example.lmageeach.model.ConcernData;
import com.example.lmageeach.model.SupportData;
import com.example.lmageeach.model.UserData;
import com.example.lmageeach.util.Result;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ConcernDataServiceImpl extends ServiceImpl<ConcernDataMapper, ConcernData> implements ConcernDataService {

    @Resource
    private ConcernDataMapper concernDataMapper;

    @Resource
    private UserDataMapper userDataMapper;


    //查看关注
    public Result showConcern(String userId) {
        QueryWrapper<ConcernData> concernDataQueryWrapper = new QueryWrapper<>();
        concernDataQueryWrapper.eq("userId", userId);
        List<ConcernData> concernData1 = concernDataMapper.selectList(concernDataQueryWrapper);
        ArrayList<String> list = new ArrayList<>();
        for (ConcernData data : concernData1) {
            list.add(data.getAuthorId());
        }

        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
        userDataQueryWrapper.in("userId",list);
        return Result.ok(userDataMapper.selectList(userDataQueryWrapper));
    }

    //查看粉丝
    public Result showBeConcern(String userId) {
        QueryWrapper<ConcernData> concernDataQueryWrapperFans = new QueryWrapper<>();
        concernDataQueryWrapperFans.eq("authorId", userId);
        List<ConcernData> concernData1 = concernDataMapper.selectList(concernDataQueryWrapperFans);
        ArrayList<String> list = new ArrayList<>();
        for (ConcernData data : concernData1) {
            list.add(data.userId);
        }

        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
        userDataQueryWrapper.in("userId",list);
        return Result.ok(userDataMapper.selectList(userDataQueryWrapper));
    }
}
