package com.example.myblogssm.service;

import com.example.myblogssm.entity.UserInfo;
import com.example.myblogssm.mapper.UserInfoMapper;
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
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    public int addUserInfo(UserInfo userInfo) {
        return userInfoMapper.addUserInfo(userInfo);
    }

    public UserInfo selectUserInfo(UserInfo userInfo) {
        return userInfoMapper.selectUserInfo(userInfo);
    }
}
