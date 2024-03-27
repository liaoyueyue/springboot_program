package com.example.danmudemo.controller;

import com.example.danmudemo.entiy.Danmu;
import com.example.danmudemo.entiy.Video;
import com.example.danmudemo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @RequestMapping("/getDanmu")
    List<Danmu> getDanmu() {
        List<Danmu> danmus = new ArrayList<>();
        danmus.add(new Danmu("Test1", 1.0));
        danmus.add(new Danmu("Test2", 2.0));
        danmus.add(new Danmu("Test3", 3.0));
        return danmus;
    }

}
