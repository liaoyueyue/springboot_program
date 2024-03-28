package com.example.orderdemo.entity;

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
    private Date createTime;
    private Date updateTime;
}