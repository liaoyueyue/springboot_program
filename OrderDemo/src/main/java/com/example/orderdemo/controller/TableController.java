package com.example.orderdemo.controller;

import com.example.orderdemo.common.Result;
import com.example.orderdemo.entity.Table;
import com.example.orderdemo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    // 分页查询
    @ResponseBody
    @GetMapping("/page")
    public Result page(Integer page, Integer limit) {
        return tableService.page(page, limit);
    }

    // 删除
    @ResponseBody
    @PostMapping("/delete")
    public Result delete(Integer id) {

        return tableService.delete(id);
    }

    // 修改
    @ResponseBody
    @PostMapping("/update")
    public Result update(Table table) {

        return tableService.update(table);
    }

    // 批量删除
    @ResponseBody
    @PostMapping("/deletes")
    public Result deletes(String ids) {

        return tableService.deletes(ids);
    }

    // 添加
    @ResponseBody
    @PostMapping("/add")
    public Result add(Table table) {
        System.out.println("table = " + table);
        return tableService.add(table);
    }
}
