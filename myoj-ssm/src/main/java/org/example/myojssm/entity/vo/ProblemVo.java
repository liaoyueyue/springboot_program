package org.example.myojssm.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: 给 problem/list返回提供
 * User: liaoyueyue
 * Date: 2024-04-23
 * Time: 1:49
 */
@Data
public class ProblemVo {
    private int id;
    private String title;
    private String collectionName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    private String level;
}
