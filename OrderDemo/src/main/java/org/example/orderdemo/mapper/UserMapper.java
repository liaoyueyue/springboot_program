package org.example.orderdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.orderdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* @author liaoyueyue
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-02-29 19:33:22
* @Entity org.example.orderdemo.entity.User
*/
@Mapper
public interface UserMapper {
    List<User> queryAllUser();
}




