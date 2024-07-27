package com.example.orderdemo.service;

import com.example.orderdemo.common.Result;
import com.example.orderdemo.entity.Table;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-25
 * Time: 9:27
 */
public interface TableService {

    // 分页查询
    Result page(Integer page, Integer limit);

    // 删除
    Result delete(Integer id);

    // 修改
    Result update(Table table);

    // 批量删除
    Result deletes(String ids);

    // 添加
    Result add(Table table);
}
