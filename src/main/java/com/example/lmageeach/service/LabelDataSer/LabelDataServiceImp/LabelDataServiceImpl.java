package com.example.lmageeach.service.LabelDataSer.LabelDataServiceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.LabelDataMapper;
import com.example.lmageeach.model.LabelData;
import com.example.lmageeach.service.LabelDataSer.LabelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelDataServiceImpl extends ServiceImpl<LabelDataMapper,LabelData> implements LabelDataService {

    @Autowired
    private LabelDataMapper labelDataMapper;
}
