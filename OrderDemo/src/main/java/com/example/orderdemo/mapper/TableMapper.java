package com.example.orderdemo.mapper;

import com.example.orderdemo.entity.Table;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-25
 * Time: 9:22
 */
@Mapper
public interface TableMapper {

    /**
     * 分页查询
     * @param startIndex 数据库查询的起始位置
     * @param pageSize 每页显示的数量
     * @return List<Table>
     */
    @Select("select * from tabletbl limit #{startIndex},#{pageSize}")
    List<Table> findByPage(Integer startIndex, Integer pageSize);


    /**
     * 求总数量
     * @return int
     */
    @Select("select count(*) from tabletbl")
    int count();

    // 删除
    @Delete("delete from tabletbl where id = #{id}")
    void delete(Integer id);

    // 修改
    @Update("update tabletbl set num = #{num},flag = #{flag}, description  = #{description} where id = #{id}")
    void update(Table table);
    // 添加
    @Insert("insert into tabletbl (num,description) values (#{num},#{description})")
    void add(Table table);
}
