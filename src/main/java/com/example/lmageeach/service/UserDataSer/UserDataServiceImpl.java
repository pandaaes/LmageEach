package com.example.lmageeach.service.UserDataSer;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.ConcernDataMapper;
import com.example.lmageeach.mapper.LmageDataMapper;
import com.example.lmageeach.mapper.UserDataMapper;
import com.example.lmageeach.model.ConcernData;
import com.example.lmageeach.model.LmageData;
import com.example.lmageeach.model.UserData;
import com.example.lmageeach.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;


@Service
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData> implements UserDataService {

    @Resource
    private UserDataMapper userDataMapper;

    @Resource
    private LmageDataMapper lmageDataMapper;

    @Resource
    private ConcernDataMapper concernDataMapper;


    /**
     * 登录
     * @param userData
     * @param session
     * @return
     */
    public Result login(UserData userData, HttpSession session) {

        QueryWrapper<UserData> QueryWrapper = new QueryWrapper<UserData>();
        QueryWrapper.eq("username",userData.username).eq("password",userData.password);
        List<UserData> userData1 = userDataMapper.selectList(QueryWrapper);

        //自动创建用户
        if (userData1.isEmpty()) {
            String token = UUID.randomUUID().toString();
            userData.setUserId(token);
            userDataMapper.insert(userData);
            session.setAttribute("token",userData.userId);
        }else {
            for (UserData data : userData1) {
                session.setAttribute("token",data.userId);
            }
        }

        return Result.ok();
    }


    /**
     * 个人信息查询
     * @param session
     * @return
     */
    public Result userInformation(HttpSession session) {

        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<UserData>();

        userDataQueryWrapper.eq("user_id", session.getAttribute("token"));

        List<UserData> userData1 = userDataMapper.selectList(userDataQueryWrapper);
        if (userData1.isEmpty())
            return Result.fail("系统错误");
        return Result.ok(userDataMapper.selectList(userDataQueryWrapper));
    }


    /**
     * 用户信息修改
     * @param userData
     * @param session
     * @return
     */
    public Result modifyInformation(UserData userData,HttpSession session) {
//        Result result = userInformation(session);
        UpdateWrapper<UserData> updateWrapper =Wrappers.update();
//        if (!userData.username.isEmpty())
//            updateWrapper.set("username",userData.getUsername());
//        if (!userData.password.isEmpty())
//            updateWrapper.set("password",userData.getPassword());
//        if (!userData.firstName.isEmpty())
//            updateWrapper.set("firstName",userData.getFirstName());
//        if (!userData.lastName.isEmpty())
//            updateWrapper.set("lastName",userData.getLastName());
//        if (!userData.urban.isEmpty())
//            updateWrapper.set("urban",userData.getUrban());

        updateWrapper.eq("userId",session.getAttribute("token"));

        userDataMapper.update(userData,updateWrapper);

        return Result.ok();
    }


    /**
     * 个人作品查询
     * @param userData
     * @param session
     * @return
     */
    public Result artwork(UserData userData, HttpSession session) {
        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
        userDataQueryWrapper.eq("user_id",session.getAttribute("token"));
        UserData user = userDataMapper.selectOne(userDataQueryWrapper);
        QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
        lmageDataQueryWrapper.eq("user_name",user.getUsername());
        List<LmageData> lmageDataList = lmageDataMapper.selectList(lmageDataQueryWrapper);
        return Result.ok(lmageDataList);
    }



    /**
     * 关注
     * @param concernData
     * @return
     */
    public Result concern(ConcernData concernData) {
        concernDataMapper.insert(concernData);

        UpdateWrapper<UserData> userDataUpdateWrapper = Wrappers.update();
        userDataUpdateWrapper.eq("user_id",concernData.getUserId());
        userDataUpdateWrapper.setSql("concern = concern + 1");
        userDataMapper.update(null,userDataUpdateWrapper);

        UpdateWrapper<UserData> userDataUpdateWrapperTwo = Wrappers.update();
        userDataUpdateWrapperTwo.eq("user_id",concernData.getAuthorId());
        userDataUpdateWrapperTwo.setSql("fans = fans + 1");
        userDataMapper.update(null,userDataUpdateWrapperTwo);

        return Result.ok("关注成功");
    }


}

