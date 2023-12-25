package com.example.lmageeachtwo.model.service.UserDataSer;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeachtwo.model.UserData;
import com.example.lmageeachtwo.model.mapper.userDataMap.UserDataMapper;
import com.example.lmageeachtwo.model.service.UserDataService;
import org.springframework.stereotype.Service;



@Service
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData> implements UserDataService {



//    @Override
//    public List<UserData> listTest() {
//        return userDataMapper.selectList(null);
//    }
}

