package com.example.orderdemo.mapper;

import com.example.orderdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author liaoyueyue
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-02-29 19:33:22
* @Entity entity.com.example.orderdemo.User
*/
@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from usertbl where username = #{username}")
    User queryUserByUsername(String username);

    //修改用户个人信息
    @Update("update usertbl set username = #{username}, name = #{name}, gender = #{gender}, permission = #{permission}, remark = #{remark} where id = #{id}")
    void updateUser(User user);
}




