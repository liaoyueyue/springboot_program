package org.example.myojssm.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.example.myojssm.common.Result;
import org.example.myojssm.config.AliyunOssConfig;
import org.example.myojssm.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-09
 * Time: 0:03
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    // 允许上传文件(图片)的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".png"};
    @Autowired
    private OSS ossClient;// 注入阿里云oss文件服务器客户端
    @Autowired
    private AliyunOssConfig aliyunOssConfig;// 注入阿里云OSS基本配置类

    public Result upload(MultipartFile uploadFile) {
        // 获取oss的Bucket名称
        String bucketName = aliyunOssConfig.getBucketName();
        // 获取 Bucket 域名
        String urlPrefix = aliyunOssConfig.getUrlPrefix();
        // 获取oss目标文件夹
        String filehost = aliyunOssConfig.getFileHost();
        // 返回图片上传后返回的url
        String returnImgeUrl = "";

        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        if (!isLegal) {// 如果图片格式不合法
            return Result.fail("图片格式不合法");
        }
        // 获取文件原名称
        String originalFilename = uploadFile.getOriginalFilename();
        // 获取文件类型
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 新文件名称
        String newFileName = UUID.randomUUID().toString() + fileType;
        // 文件上传的路径地址
        String uploadImgeUrl = filehost + newFileName;
        // 获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 更改获取图片链接在线浏览链接
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uploadImgeUrl, inputStream, meta);
        // 上传文件
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        returnImgeUrl = urlPrefix + uploadImgeUrl;
        return Result.success(returnImgeUrl);
    }
}
