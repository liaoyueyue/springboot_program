package org.example.myojssm.service;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.User;


/**
 * Created with IntelliJ IDEA.
 * Description: 用户服务层
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
    Result login(String account, String password);

    /**
     * 用户注册
     *
     * @param email    邮箱
     * @param password 密码
     * @param nickname 昵称
     * @return 新用户的用户名
     */
    Result register(String email, String password, String nickname);

    /**
     * 查询用户使用用户名
     * @param username 用户名
     * @return 用户实体
     */
    User queryUserByUsername(String username);

    /**
     * 查询用户使用编号
     * @param id 编号
     * @return 用户实体
     */
    User queryUserById(int id);

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
     *
     * @param user 用户实体
     * @return 是否更新成功
     */
    Result updateUserInfo(User user);

    /**
     * 更新用户头像
     *
     * @param avatarUrl 头像地址
     * @return 是否更新成功
     */
    Result updateAvatar(String avatarUrl);

    /**
     * 更新用户密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 是否更新成功
     */
    Result updatePwd(String oldPwd, String newPwd);

    /**
     * 获取当前用户信息
     * @return 对应用户信息
     */
    User getUserInfo();
}
