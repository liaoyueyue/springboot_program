package org.example.myojssm.service;

import org.example.myojssm.entity.User;
import org.example.myojssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-10
 * Time: 17:02
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User login(String email, String username) {
        User user;
        if (StringUtils.hasLength(email)) {
            user = userMapper.queryOneByEmail(email);
        } else {
            user = userMapper.queryOneByUsername(username);
        }
        return user;
    }

    public boolean addUser(User user){
        if (userMapper.addOne(user) > 0) {
            return true;
        }
        return false;
    }

    public int queryUsernameExist(String username) {
        return userMapper.queryUsernameExist(username);
    }
}
