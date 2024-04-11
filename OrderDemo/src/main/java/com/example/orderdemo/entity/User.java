package com.example.orderdemo.entity;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String permission;
    private String remark;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}