package org.example.myojssm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.myojssm.entity.User;

/**
 * @author liaoyueyue
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-03-10 16:35:43
 * @Entity org.example.myojssm.entity.User
 */
@Mapper
public interface UserMapper {
    /**
     * 添加新用户
     *
     * @param user 新用户实体
     * @return 数据库影响行数
     */
    int insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户实体
     * @return 数据库影响行数
     */
    int updateUser(User user);

    /**
     * 查询用户使用用户名
     *
     * @param username 用户名
     * @return 用户实体
     */
    User queryUserByUsername(@Param("username") String username);

    /**
     * 查询用户使用编号
     *
     * @param id 编号
     * @return 用户实体
     */
    User queryUserById(int id);

    /**
     * 使用 email 查询用户
     *
     * @param email 邮箱
     * @return email 对应用户信息
     */
    User queryUserByEmail(@Param("email") String email);

    /**
     * 使用 username 或者 email  查询用户
     *
     * @param account 邮箱或者用户名
     * @return username 或者 email 对应用户信息
     */
    User queryUserByEmailOrUsername(@Param("account") String account);

    /**
     * 查询用户名是否已经存在
     *
     * @param username 用户名
     * @return 数据库影响行数
     */
    int queryUsernameExist(@Param("username") String username);

    /**
     * 查询邮箱是否已经存在
     *
     * @param email 邮箱
     * @return 数据库影响行数
     */
    int queryEmailExist(@Param("email") String email);

    /**
     * 更新用户头像
     *
     * @param avatarUrl 头像地址
     * @param id 用户编号
     * @return 数据库影响行数
     */
    int updateAvatar(@Param("avatarUrl") String avatarUrl, @Param("id") Integer id);

    /**
     * @param id 用户编号
     * @param newPwd 新密码
     * @return 数据库影响行数
     */
    int updatePwd(@Param("id") Integer id, @Param("newPwd") String newPwd);
}




