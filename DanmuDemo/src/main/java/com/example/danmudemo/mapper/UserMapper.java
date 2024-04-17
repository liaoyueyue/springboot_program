package com.example.danmudemo.mapper;

import com.example.danmudemo.entiy.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

//    @Insert("insert into user(username, password) values (#{username}, #{password})")
//    Integer insertUser(User user);

    @Select("select * from user where username = #{username}")
    User queryUserByUsername(String username);
}
