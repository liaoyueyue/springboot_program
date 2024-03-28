package com.example.orderdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-28
 * Time: 8:58
 */
@Controller
public class HomeController {
    @GetMapping(value = {"/","/login"})
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }
}
