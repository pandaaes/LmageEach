package com.example.lmageeach.service.CommentsDataSer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.CommentsDataMapper;
import com.example.lmageeach.model.CommentsData;

import javax.annotation.Resource;

public class CommentsDataServiceImpl extends ServiceImpl<CommentsDataMapper, CommentsData> implements CommentsDataService {

    @Resource
    private CommentsDataMapper commentsDataMapper;
}
