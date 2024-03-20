package com.example.danmudemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-06
 * Time: 9:34
 */
@Controller
public class HomeController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/play/{videoPath}")
    public String play(@PathVariable String videoPath, Model model) {
        model.addAttribute(videoPath);
        return "play";
    }

}
