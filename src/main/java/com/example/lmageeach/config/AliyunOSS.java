package com.example.lmageeach.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliyunOSS {
    //阿里云OSS地址
    @Value("${aliyun.oos.endpoint}")
    private static  String endpoint;
    //阿里云OSS账号
    @Value("${aliyun.oos.accessKeyId}")
    private String accessKeyId;
    //阿里云OSS密钥
    @Value("${aliyun.oos.accessKeySecret}")
    private String accessKeySecret;
    //阿里云OSS上的存储块bucket名字
    @Value("${aliyun.oos.bucketName}")
    private String bucketName;
    //阿里云图片文件存储目录
    @Value("${aliyun.oos.filedir}")
    private String filedir;

}
