package com.example.danmudemo.controller;

import com.example.danmudemo.common.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-06
 * Time: 11:00
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${DanmuPlayer.videoPath}")
    private String videoPath;
    @Value("${DanmuPlayer.imagePath}")
    private String imagePath;

    @GetMapping("/add")
    public String add() {
        return "admin/add";
    }

    @PostMapping("/upload/video")
    @ResponseBody
    public AjaxResult uploadVideo(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResult.fail("没有文件");
        }
        // 获取用户上传的文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        File filePath = new File(videoPath, fileName);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(videoPath + fileName));
            return AjaxResult.success();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/upload/image")
    @ResponseBody
    public AjaxResult uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResult.fail("没有文件");
        }
        // 获取用户上传的文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        File filePath = new File(imagePath, fileName);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(imagePath + fileName));
            return AjaxResult.success();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
