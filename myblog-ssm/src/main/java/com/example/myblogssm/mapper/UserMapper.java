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
     * @param id 用户编号
     * @return 用户实体
     */
    User queryUserById(@Param("id") Integer id);

    /**
     * 更新用户信息
     * @param user 用户实体
     * @return 数据库受影响行数
     */
    int updateUser(User user);

    /**
     * 更新用户头像
     * @param id 用户编号
     * @param photo 用户头像
     * @return 数据库受影响行数
     */
    int updatePhotoById(@Param("id") Integer id,@Param("photo") String photo);

    /**
     * 更新用户密码
     * @param id 用户id
     * @param newPassword 用户新密码
     * @return 数据影响行数
     */
    int updatePassword(@Param("id") Integer id, @Param("newPassword") String newPassword);

    /**
     * 查询用户名是否已经存在
     * @param username 用户名
     * @return 数据库影响行数
     */
    int queryUsernameExist(@Param("username") String username);

    /**
     * 查询邮箱是否已经存在
     * @param email 邮箱
     * @return 数据库影响行数
     */
    int queryEmailExist(@Param("email") String email);

    /**
     * 更新用户邮箱
     * @param id 用户id
     * @param email 新邮箱
     * @return 数据库影响行数
     */
    int updateEmailById(@Param("id") Integer id, @Param("email") String email);

}
