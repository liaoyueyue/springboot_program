package org.example.myojssm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description: OJ 题
 * User: liaoyueyue
 * Date: 2024-01-14
 * Time: 14:44
 */
@Data
public class Problem implements Serializable {
    private int id;

    private String title;

    private String level;

    private String description;

    private String templateCode;

    private String testCode;

    private static final long serialVersionUID = 1L;
}
