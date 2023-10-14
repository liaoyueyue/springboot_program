package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:57
 */
@Mapper
public interface UserMapper {
    /**
     * 添加用户
     * @param user 用户实体
     * @return 数据库受影响行数
     */
    int addUser(User user);

    /**
     * 用户名查询用户
     * @param username 用户名
     * @return 用户实体
     */
    User queryUserByName(@Param("username") String username);

}
