package org.example.myojssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: OJ 题
 * User: liaoyueyue
 * Date: 2024-01-14
 * Time: 14:44
 */
@Data
public class Problem implements Serializable {
    private Integer id;
    private String title;
    private String level;
    private Integer categoryId;
    private String description;
    private String templateCode;
    private String testCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    private static final long serialVersionUID = 1L;
}
