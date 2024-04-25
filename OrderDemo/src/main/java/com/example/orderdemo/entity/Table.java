package com.example.orderdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-25
 * Time: 8:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table implements Serializable {
    private Integer id;
    private Integer ordId;
    private String num;
    private String flag;
    private String description;
}
