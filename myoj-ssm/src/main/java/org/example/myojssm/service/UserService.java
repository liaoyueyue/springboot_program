package org.example.myojssm.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.example.myojssm.entity.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-23
 * Time: 13:27
 */
public interface UserService {
    User login(HttpServletRequest request, String account, String password);

    boolean addUser(User user);

    boolean queryUsernameExist(String username);

    boolean queryEmailExist(String email);
}
