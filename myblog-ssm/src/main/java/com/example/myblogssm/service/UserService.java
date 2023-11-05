package com.example.myblogssm.service;

import com.example.myblogssm.entity.User;
import com.example.myblogssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:57
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }

    public User queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int updatePhotoById(Integer id, String photo) {
        return userMapper.updatePhotoById(id, photo);
    }

    public int updatePassword(Integer id, String newPassword) {
        return userMapper.updatePassword(id, newPassword);
    }

    public int queryUsernameExist(String username) {
        return userMapper.queryUsernameExist(username);
    }
    public int queryEmailExist(String email) {
        return userMapper.queryEmailExist(email);
    }

}
