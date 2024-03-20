package com.example.danmudemo.mapper;

import com.example.danmudemo.entiy.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user values (#{username}, #{password})")
    void insertUser(User user);
    @Select("select * from user")
    List<User> getAllUser();
}
