package org.example.myojssm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.myojssm.entity.User;

/**
* @author liaoyueyue
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-03-10 16:35:43
* @Entity org.example.myojssm.entity.User
*/
@Mapper
public interface UserMapper {
    /**
     * 使用 username 查询用户
     * @return username 对应用户信息
     */
    User queryOneByUsername(@Param("username") String username);

    /**
     * 添加新用户
     * @param user 新用户实体
     * @return 数据库返回数字
     */
    int addUser(User user);

    /**
     * 查询用户名是否已经存在
     * @param username 用户名
     * @return 数据库影响行数
     */
    int queryUsernameExist(@Param("username") String username);
}




