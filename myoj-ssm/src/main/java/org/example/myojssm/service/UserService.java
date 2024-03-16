package org.example.myojssm.service;

import org.apache.ibatis.annotations.Param;
import org.example.myojssm.entity.User;
import org.example.myojssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User queryOneByUsername(String username) {
        return userMapper.queryOneByUsername(username);
    }

    public boolean addUser(User user){
        if (userMapper.addUser(user) > 0) {
            return true;
        }
        return false;
    }

    public int queryUsernameExist(String username) {
        return userMapper.queryUsernameExist(username);
    }
}
