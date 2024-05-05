package org.example.myojssm.service.impl;

import org.example.myojssm.common.Result;
import org.example.myojssm.common.exception.ImageVaildException;
import org.example.myojssm.common.utils.AliyunOSSUtil;
import org.example.myojssm.service.FileUploadService;
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

    public String uploadImage(MultipartFile imageFile) {
        // 校验图片格式
        try {
            boolean isLegal = false;
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(imageFile.getOriginalFilename(), type)) {
                    isLegal = true;
                    break;
                }
            }
            if (!isLegal) {// 如果图片格式不合法
                throw new ImageVaildException();
            }
        } catch (ImageVaildException e) {
            System.out.println("捕获到图片格式验证异常：" + e.getMessage());
        }
        // 获取文件原名称
        String originalFilename = imageFile.getOriginalFilename();
        // 获取文件类型
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 新文件名称
        String newFileName = UUID.randomUUID() + fileType;
        // 获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = imageFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AliyunOSSUtil.upload(newFileName, inputStream);
    }
}
