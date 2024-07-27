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

        return Result.success().data(tables).count(count);
    }

    // 删除
    @Override
    public Result delete(Integer id) {
        try {
            tableMapper.delete(id);
            return Result.success().msg("删除成功");
        }
        catch (Exception e) {
            return Result.fail().msg("删除失败");
        }
    }

    // 修改
    @Override
    public Result update(Table table) {
        try {
            tableMapper.update(table);
            return Result.success().msg("修改成功");
        }
        catch (Exception e) {
            return Result.fail().msg("修改失败");
        }
    }

    // 批量删除
    @Override
    public Result deletes(String ids) {

        try {

            String[] arrIds = ids.split(",");
            for (String id : arrIds) {
                tableMapper.delete(Integer.parseInt(id));
            }

            return Result.success().msg("删除成功");
        }
        catch (Exception e) {
            return Result.fail().msg("删除失败");
        }



    }
    // 添加
    @Override
    public Result add(Table table) {
        try {
            tableMapper.add(table);
            return Result.success().msg("添加成功");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.fail().msg("添加失败");
        }
    }
}
