package org.example.myojssm.common.utils;


import org.example.myojssm.service.UserService;
import org.example.myojssm.service.impl.UserServiceImpl;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 唯一用户名工具类
 * User: liaoyueyue
 * Date: 2023-11-05
 * Time: 12:48
 */
public class UniqueUsernameUtil {

    public static String getUsername(String nickname) {
        String baseUsername = generateBaseUsername(nickname);
        String uniqueSuffix = generateUniqueSuffix();
        return baseUsername + "_" + uniqueSuffix;
    }

    private static String generateBaseUsername(String nickname) {
        return nickname.replaceAll(" ", "_").toLowerCase();
    }

    private static String generateUniqueSuffix() {
        return UUID.randomUUID().toString().substring(0, 8); // 使用UUID生成唯一标识符的一部分作为后缀
    }
}
