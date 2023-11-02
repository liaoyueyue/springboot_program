package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
     * 添加用户 - 用于注册
     * @param user 用户实体
     * @return 数据库受影响行数
     */
    int addUser(User user);

    /**
     * 用户名查询用户 - 用于登录
     * @param username 用户名
     * @return 用户实体
     */
    User queryUserByName(@Param("username") String username);

    /**
     * 使用 id 查询用户 -用于博客详情页获取作者信息
     * @param id 用户id
     * @return 用户实体
     */
    User queryUserById(@Param("id") Integer id);

}
