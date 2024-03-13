package com.example.danmudemo.controller;

import org.springframework.stereotype.Controller;
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

    @RequestMapping("/play")
    public String play() {
        return "play";
    }

    @RequestMapping("/admin/add")
    public String admin() {
        return "admin/add";
    }
}
