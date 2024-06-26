package com.example.danmudemo.entiy;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName video
 */
@Data
public class Video implements Serializable {
    private Integer id;

    private String title;

    private String videoPath;

    private String imagePath;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime uploadTime;

    private static final long serialVersionUID = 1L;
}