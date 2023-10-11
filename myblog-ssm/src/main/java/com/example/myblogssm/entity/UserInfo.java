package com.example.myblogssm.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-10-11
 * Time: 17:39
 */
@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String photo;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int state;
}
