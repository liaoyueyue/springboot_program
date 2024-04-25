package com.example.orderdemo.service;

import com.example.orderdemo.common.Result;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-25
 * Time: 9:27
 */
public interface TableService {

    Result page(Integer page, Integer limit);

}
