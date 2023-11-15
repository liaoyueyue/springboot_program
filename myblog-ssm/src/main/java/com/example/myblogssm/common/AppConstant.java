package com.example.myblogssm.common;

import org.springframework.boot.system.ApplicationHome;

import java.io.File;

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
    public static final String JAR_FOLDER_PATH;

    static {
        ApplicationHome applicationHome = new ApplicationHome(AppConstant.class);
        File jarFolder = applicationHome.getSource();
        JAR_FOLDER_PATH = jarFolder.getParentFile().toString();
    }
    public static final String IMG_PATH_ABSOLUTE = JAR_FOLDER_PATH + "/upload/";
    public static final String IMG_PATH_RELATIVE = "/upload/";
    public static final String DEFAULT_PHOTO = "/upload/defaultPhoto.jpg";

}
