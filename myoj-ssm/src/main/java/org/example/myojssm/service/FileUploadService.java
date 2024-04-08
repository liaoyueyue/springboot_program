package org.example.myojssm.service;

import org.example.myojssm.common.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-09
 * Time: 0:04
 */
public interface FileUploadService {
    Result upload(MultipartFile uploadFile);
}
