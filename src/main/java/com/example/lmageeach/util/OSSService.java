package com.example.lmageeach.util;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class OSSService {
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

    /**
     * 上传图片
     *
     * @param file
     * @return 图片的路径
     */
    public String uploadImg2Oss(MultipartFile file) {
//            if (file.getSize() > 1024 * 1024 *20) { //20M
//                return null;
//            }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name);
            return name;//RestResultGenerator.createSuccessResult(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 上传图片获取fileUrl
     *
     * @param instream
     * @param fileName
     * @return
     */
    private String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        OSS ossClient = null;
        try {
//                //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);


            //上传文件
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //判断仓库是否存在
            if (!ossClient.doesBucketExist(bucketName)) {
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(null);
                createBucketRequest.setBucketName(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpg";
    }

    /**
     * 获取图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (fileUrl != null) {
            String[] split = fileUrl.split("/");
            String url = this.getUrl(this.filedir + split[split.length - 1]);
//                log.info(url);
            String[] spilt1 = url.split("\\?");
            return spilt1[0];
//            return url;
        }
        return null;
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    /**
     * 多图片上传
     *
     * @param fileList
     * @return
     */
    public String checkList(List<MultipartFile> fileList) {
        String fileUrl = "";
        String str = "";
        String photoUrl = "";
        for (int i = 0; i < fileList.size(); i++) {
            fileUrl = uploadImg2Oss(fileList.get(i));
            str = getImgUrl(fileUrl);
            if (i == 0) {
                photoUrl = str;
            } else {
                photoUrl += "," + str;
            }
        }
        return photoUrl.trim();
    }

    /**
     * 单个图片上传
     *
     * @param file
     * @return
     */
    public String checkImage(MultipartFile file) {
        String fileUrl = uploadImg2Oss(file);
        String str = getImgUrl(fileUrl);
        return str.trim();
    }

    public Result uploadTest(MultipartFile file) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObjectRequest对象。
            InputStream inputStream = file.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filedir, inputStream);
            // 如果需要上传时设置存储类型和访问权限
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return Result.ok();
    }

    public boolean deleteImage(String lmageData) {
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            String bucketName = lmageData.split("\\.")[0].split("/")[2];
            String objectName = lmageData.substring(lmageData.indexOf("/", lmageData.indexOf("//") + 2) + 1);
            ossClient.deleteObject(bucketName,objectName);
            return true;
        }catch (Exception e){
            return false;
        }finally {
            ossClient.shutdown();
        }

    }
}




