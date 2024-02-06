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
        try {
            UpdateWrapper<UserData> updateWrapper =Wrappers.update();
            if (userData.getUsername() != null)
                updateWrapper.set("username",userData.getUsername());
            if (userData.getPassword()!= null)
                updateWrapper.set("password",userData.getPassword());
            if (userData.getFirstName()!= null)
                updateWrapper.set("first_name",userData.getFirstName());
            if (userData.getLastName()!= null)
                updateWrapper.set("last_name",userData.getLastName());
            if (userData.getUrban()!= null)
                updateWrapper.set("urban",userData.getUrban());
            if (userData.getCountry()!= null)
                updateWrapper.set("country",userData.getCountry());
            if (userData.getBirthday() != null)
                updateWrapper.set("birthday",userData.getBirthday());
            if (userData.getBio()!= null)
                updateWrapper.set("bio",userData.getBio());
            if (userData.getEmail()!= null)
                updateWrapper.set("email",userData.getEmail());
            updateWrapper.eq("user_id",session.getAttribute("token"));
            userDataMapper.update(null,updateWrapper);
            return Result.ok("修改成功");
        }catch (Exception e){
            return Result.fail("修改失败");
        }

    }


    /**
     * 个人作品查询
     * @param session
     * @return
     */
    public Result artwork(HttpSession session) {
        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
        userDataQueryWrapper.eq("user_id",session.getAttribute("token"));
        UserData user = userDataMapper.selectOne(userDataQueryWrapper);
        QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
        lmageDataQueryWrapper.eq("user_name",user.getUsername());
        List<LmageData> lmageDataList = lmageDataMapper.selectList(lmageDataQueryWrapper);
        if (lmageDataList.isEmpty()){
            return Result.ok("暂无作品");
        }
        return Result.ok(lmageDataList);
    }



    /**
     * 关注
     * @param concernData
     * @return
     */
    public Result concern(ConcernData concernData) {
        try {
            concernDataMapper.insert(concernData);
            QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
            userDataQueryWrapper.eq("user_id",concernData.getUserId());
            UserData userData = userDataMapper.selectOne(userDataQueryWrapper);
            userData.setConcern(userData.getConcern()+1);
            UpdateWrapper<UserData> userDataUpdateWrapper = Wrappers.update();
            userDataUpdateWrapper.eq("user_id",concernData.getUserId());
            userDataMapper.update(userData,userDataUpdateWrapper);

            QueryWrapper<UserData> userDataQueryWrapperTwo = new QueryWrapper<>();
            userDataQueryWrapperTwo.eq("user_id",concernData.getAuthorId());
            UserData userDataTwo = userDataMapper.selectOne(userDataQueryWrapperTwo);
            userDataTwo.setFans(userData.getFans()+1);
            UpdateWrapper<UserData> userDataUpdateWrapperTwo = Wrappers.update();
            userDataUpdateWrapperTwo.eq("user_id",concernData.getAuthorId());
            userDataMapper.update(userDataTwo,userDataUpdateWrapperTwo);

            return Result.ok("关注成功");
        }catch (Exception e){
            return Result.fail("关注失败");
        }

    }


    public Result userOutformation(String userId) {
        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<UserData>();

        userDataQueryWrapper.eq("user_id", userId);

        List<UserData> userData1 = userDataMapper.selectList(userDataQueryWrapper);
        if (userData1.isEmpty())
            return Result.fail("系统错误");
        return Result.ok(userDataMapper.selectList(userDataQueryWrapper));

    }

    public Result userOutArtwork(String userId) {
        QueryWrapper<UserData> userDataQueryWrapper = new QueryWrapper<>();
        userDataQueryWrapper.eq("user_id",userId);
        UserData user = userDataMapper.selectOne(userDataQueryWrapper);
        if (user == null){
            return Result.fail("用户不存在");
        }
        QueryWrapper<LmageData> lmageDataQueryWrapper = new QueryWrapper<>();
        lmageDataQueryWrapper.eq("user_name",user.getUsername());
        List<LmageData> lmageDataList = lmageDataMapper.selectList(lmageDataQueryWrapper);
        if (lmageDataList.isEmpty()){
            return Result.ok("暂无作品");
        }
        return Result.ok(lmageDataList);
    }
}

