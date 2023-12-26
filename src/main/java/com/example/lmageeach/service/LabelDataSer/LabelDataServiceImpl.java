package com.example.lmageeach.service.LabelDataSer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lmageeach.mapper.LabelDataMapper;
import com.example.lmageeach.model.LabelData;
import com.example.lmageeach.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LabelDataServiceImpl extends ServiceImpl<LabelDataMapper,LabelData> implements LabelDataService {

    @Resource
    private LabelDataMapper labelDataMapper;

    public Result labeList() {
        QueryWrapper<LabelData> labelDataQueryWrapper = new QueryWrapper<>();
        labelDataQueryWrapper.orderByDesc("total");
        labelDataMapper.selectList(labelDataQueryWrapper);
        return Result.ok(labelDataMapper.selectList(labelDataQueryWrapper));
    }
}
