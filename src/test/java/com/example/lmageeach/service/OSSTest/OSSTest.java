package com.example.lmageeach.service.OSSTest;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OSSTest {
    //阿里云OSS地址
    @Value("${aliyun.oos.endpoint}")
    private String endpoint;
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

    @Test
    public void getOSSCLient(){
        OSS build = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (build.doesBucketExist(bucketName)){
            System.out.println(bucketName);
        }else {
            System.out.println("null");
        }
    }
}
