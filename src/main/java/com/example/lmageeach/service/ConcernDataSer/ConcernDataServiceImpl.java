package com.example.lmageeach.service.ConcernDataSer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.ConcernDataMapper;
import com.example.lmageeach.mapper.UserDataMapper;
import com.example.lmageeach.model.ConcernData;
import com.example.lmageeach.model.SupportData;
import com.example.lmageeach.model.UserData;
import com.example.lmageeach.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ConcernDataServiceImpl extends ServiceImpl<ConcernDataMapper, ConcernData> implements ConcernDataService {

    @Resource
    private ConcernDataMapper concernDataMapper;

    @Resource
    private UserDataMapper userDataMapper;


    /**
     * 查看关注
     * @param userId
     * @return
     */
    public Result showConcern(String userId) {
        QueryWrapper<ConcernData> concernDataQueryWrapper = new QueryWrapper<>();
        concernDataQueryWrapper.eq("user_id", userId);
        List<ConcernData> concernData1 = concernDataMapper.selectList(concernDataQueryWrapper);
        if (concernData1.isEmpty()){
            return Result.fail("无关注");
        }else {
            ArrayList<String> list = new ArrayList<>();
            for (ConcernData data : concernData1) {
                list.add(data.getAuthorId());
            }

            QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
            userDataQueryWrapper.in("user_id",list);
            return Result.ok(userDataMapper.selectList(userDataQueryWrapper));
        }
    }

    /**
     * 查看粉丝
     * @param userId
     * @return
     */
    public Result showBeConcern(String userId) {
        QueryWrapper<ConcernData> concernDataQueryWrapperFans = new QueryWrapper<>();
        concernDataQueryWrapperFans.eq("author_id", userId);
        List<ConcernData> concernData1 = concernDataMapper.selectList(concernDataQueryWrapperFans);
        if (concernData1.isEmpty()){
            return Result.fail("抱歉你当前无粉丝");
        }else {
            ArrayList<String> list = new ArrayList<>();
            for (ConcernData data : concernData1) {
                list.add(data.userId);
            }
            QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
            userDataQueryWrapper.in("user_id",list);
            return Result.ok(userDataMapper.selectList(userDataQueryWrapper));
        }

    }
}
