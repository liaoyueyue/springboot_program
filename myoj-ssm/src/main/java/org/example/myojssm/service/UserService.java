package org.example.myojssm.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.myojssm.entity.User;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-23
 * Time: 13:27
 */
public interface UserService {
    /**
     * 用户登录
     * @param account 用户账号
     * @param password 用户密码
     * @return token - JWT令牌
     */
    String login(String account, String password);

    /**
     * 用户注册
     * @param email 邮箱
     * @param password 密码
     * @param nickname 昵称
     * @return 新用户的用户名
     */
    String addUser(String email, String password, String nickname);

    /**
     * 查询用户使用用户名
     * @param username 用户名
     * @return 用户实体
     */
    User queryUserByUsername(String username);

    /**
     * 查询用户名是否已经存在
     * @param username 用户名
     * @return 数据库影响行数
     */
    boolean queryUsernameExist(String username);

    /**
     * 查询邮箱是否存在
     * @param email 邮箱
     * @return 是否存在邮箱
     */
    boolean queryEmailExist(String email);

    /**
     * 更新用户信息
     * @param user 用户实体
     * @return 数据库影响行数
     */
    int updateUserInfo(User user);

    /**
     * 更新用户头像
     * @param avatarUrl 头像地址
     * @return 数据库影响行数
     */
    int updateAvatar(String avatarUrl);
}
