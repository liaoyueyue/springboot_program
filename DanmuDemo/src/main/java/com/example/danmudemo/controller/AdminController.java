package com.example.danmudemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.example.danmudemo.common.AjaxResult;
import com.example.danmudemo.entiy.Video;
import com.example.danmudemo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-06
 * Time: 11:00
 */
@SaCheckLogin
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    VideoMapper videoMapper;

    @Value("${DanmuPlayer.videoPath}")
    private String videoPath;
    @Value("${DanmuPlayer.imagePath}")
    private String imagePath;

    @GetMapping("/index")
    public String index(Model model) {
        List<Video> videos = videoMapper.getVideoList();
        model.addAttribute("videos", videos);
        return "admin/index";
    }

    @GetMapping("/add")
    public String add() {
        return "admin/add";
    }

    @GetMapping("/edit/{videoId}")
    public String edit(@PathVariable int videoId, Model model) {
        Video video = videoMapper.getVideoById(videoId);
        model.addAttribute(video);
        return "admin/edit";
    }

    @PostMapping("/delete/{videoId}")
    public AjaxResult delete(@PathVariable int videoId) {
        System.out.println("进入删除接口");
        videoMapper.delVideoById(videoId);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public RedirectView edit(Video video) {
        System.out.println("修改的视频信息：" + video.toString());
        videoMapper.updateVideoById(video.getId(), video.getTitle(), video.getVideoPath(), video.getImagePath());
        return new RedirectView("/admin/index");

    }

    @PostMapping("/add")
    public RedirectView add(Video video) {
        System.out.println("添加的视频信息：" + video.toString());
        videoMapper.insertVideo(video);
        return new RedirectView("/admin/index");
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
            return AjaxResult.success(fileName);
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
            return AjaxResult.success(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
