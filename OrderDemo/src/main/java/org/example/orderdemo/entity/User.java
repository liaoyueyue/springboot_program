package org.example.orderdemo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private Date createTime;
}