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

    String addUser(String email, String password, String nickname);

    User queryUserByUsername(String username);

    boolean queryUsernameExist(String username);

    boolean queryEmailExist(String email);
}
