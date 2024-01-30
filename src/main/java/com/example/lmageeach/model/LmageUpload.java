package com.example.lmageeach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LmageUpload {

    public MultipartFile file;//图片文件

    public String lmageName;//图片名称

    public String labelName;//标签名称

    public String userName;//作者
}
