package com.example.myblogssm.controller;

import com.example.myblogssm.common.AjaxResult;
import com.example.myblogssm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-14
 * Time: 15:32
 */
@Controller
@ResponseBody
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

}
