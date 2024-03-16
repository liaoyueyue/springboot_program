package com.example.orderdemo.mapper;

import com.example.orderdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author liaoyueyue
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-02-29 19:33:22
* @Entity entity.com.example.orderdemo.User
*/
@Mapper
public interface UserMapper {
    List<User> queryAllUser();
}




