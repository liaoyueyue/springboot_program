package com.example.orderdemo.service.impl;

import com.example.orderdemo.common.Result;
import com.example.orderdemo.entity.Table;
import com.example.orderdemo.mapper.TableMapper;
import com.example.orderdemo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-25
 * Time: 9:27
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    // 分页查询
    @Override
    public Result page(Integer page, Integer limit) {


        List<Table> tables = tableMapper.findByPage((page - 1) * limit, limit);

        int count = tableMapper.count();

        return Result.success().setData(tables).setCount(count);
    }
}
