package org.example.myojssm.service;

import org.example.myojssm.entity.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-23
 * Time: 13:27
 */
public interface UserService {
    User login(String email, String username);

    boolean addUser(User user);

    int queryUsernameExist(String username);
}
