package com.example.danmudemo.entiy;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-27
 * Time: 10:28
 */
@Data
public class Danmu {
    Integer id;
    Integer videoId;
    String text;
    Double time;
    Integer mode;
    Boolean border;
    String color;
}
