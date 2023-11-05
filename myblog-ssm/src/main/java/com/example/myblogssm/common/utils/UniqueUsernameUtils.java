package com.example.myblogssm.common.utils;

import com.example.myblogssm.service.UserService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-05
 * Time: 12:48
 */
public class UniqueUsernameUtils {
    public static String getUsername(String nickname, UserService userService) {
        // 用户名校正
        String baseUsername = generateBaseUsername(nickname);
        String uniqueUsername = baseUsername + "#1";
        int counter = 2;
        while (isUsernameTaken(uniqueUsername, userService)) {
            // 如果用户名已被占用，尝试生成一个带计数器后缀的唯一用户名
            uniqueUsername = baseUsername + "#" + counter;
            counter++;
        }
        return uniqueUsername;
    }

    private static String generateBaseUsername(String nickname) {
        // 这里将昵称中的空格替换为下划线并转换为小写
        return nickname.replaceAll(" ", "_").toLowerCase();
    }

    private static boolean isUsernameTaken(String username, UserService userService) {
        // 在数据库中检查用户名是否已被占用
        return userService.queryUsernameExist(username) > 0;
    }
}
