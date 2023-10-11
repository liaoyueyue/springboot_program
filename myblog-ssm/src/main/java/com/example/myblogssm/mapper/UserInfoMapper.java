package com.example.myblogssm.mapper;

import com.example.myblogssm.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:57
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 添加用户信息
     * @param userInfo 用户信息实体
     * @return 数据库受影响行数
     */
    int addUserInfo(UserInfo userInfo);

    /**
     * 查询用户信息-用于登录
     * @param userInfo 用户信息实体
     * @return 用户信息
     */
    UserInfo selectUserInfo(UserInfo userInfo);

}
