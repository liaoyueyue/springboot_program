package com.example.orderdemo.controller;

import com.example.orderdemo.common.Result;
import com.example.orderdemo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-25
 * Time: 8:46
 */
@RequestMapping("/table")
@Controller
public class TableController {

    @Autowired
    private TableService tableService;


    @GetMapping("/table")
    public String table() {
        return "/view/table/table";
    }

    @ResponseBody
    @GetMapping("/page")
    public Result page(Integer page, Integer limit) {


        return tableService.page(page, limit);
    }


}
