package org.example.myojssm.controller;

import org.example.myojssm.common.Result;
import org.example.myojssm.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-08
 * Time: 21:03
 */

@RestController
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) {
        return fileUploadService.upload(file);
    }
}
