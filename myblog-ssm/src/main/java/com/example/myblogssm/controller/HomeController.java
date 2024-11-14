package com.example.myblogssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-10-15
 * Time: 2:34
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "login.html";
    }
}
