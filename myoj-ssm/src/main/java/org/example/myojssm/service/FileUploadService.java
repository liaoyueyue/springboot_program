package org.example.myojssm.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-09
 * Time: 0:04
 */
public interface FileUploadService {
    /**
     * 上传图片
     * @param uploadFile 需要上传的文件
     * @return 上传到 OSS 的图片地址
     */
    String uploadImage(MultipartFile uploadFile);
}
