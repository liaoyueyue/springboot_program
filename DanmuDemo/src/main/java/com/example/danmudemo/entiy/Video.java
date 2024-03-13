package com.example.danmudemo.entiy;

import java.io.Serializable;
import java.util.Date;
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

    private Date uploadTime;

    private static final long serialVersionUID = 1L;
}