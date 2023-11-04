package com.example.myblogssm.common;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-14
 * Time: 21:35
 */

public class AppConstant {
    // 用户会话键
    public static final String USER_SESSION_KEY = "USER_SESSION_KEY";
    public static final String PROJECT_PATH;

    static {
        try {
            PROJECT_PATH = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static final String IMG_PATH_ABSOLUTE = PROJECT_PATH + "/images/";
    public static final String IMG_PATH_RELATIVE = "images/";
}
