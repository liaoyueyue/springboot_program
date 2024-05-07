package com.example.danmudemo.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.danmudemo.common.AjaxResult;
import com.example.danmudemo.entiy.Danmu;
import com.example.danmudemo.entiy.User;
import com.example.danmudemo.entiy.Video;
import com.example.danmudemo.mapper.DanmuMapper;
import com.example.danmudemo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-06
 * Time: 9:34
 */
@Controller
public class HomeController {
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    DanmuMapper danmuMapper;

    @RequestMapping("/index")
    public String index(Model model) {
        List<Video> videoList = videoMapper.getVideoList();
        model.addAttribute("videos", videoList);
        return "index";
    }

    @RequestMapping("/play/{videoId}")
    public String play(@PathVariable String videoId, Model model) {
        Video video = videoMapper.getVideoById(Integer.valueOf(videoId));
        model.addAttribute(video);
        return "play";
    }

    @ResponseBody
    @RequestMapping("/getDanmu/{videoId}")
    List<Danmu> getDanmu(@PathVariable String videoId, Model model) {
        List<Danmu> danmus = danmuMapper.getDanmuLIstByVideoId(Long.valueOf(videoId));
        return danmus;
    }


    @ResponseBody
    @PostMapping("/emitDanmu")
    AjaxResult emitDanmu(@RequestBody Danmu danmu) {
        danmu.setBorder(false);
        danmuMapper.insertDanmu(danmu);
        return AjaxResult.success();
    }

    @GetMapping("/user/login")
    public String login(User user) {
        return "user/login";
    }

    @GetMapping("/user/logout")
    public String logout() {
        StpUtil.logout();
        return "/user/login";
    }
}
