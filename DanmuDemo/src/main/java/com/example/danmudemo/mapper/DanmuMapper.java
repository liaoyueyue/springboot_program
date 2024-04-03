package com.example.danmudemo.mapper;

import com.example.danmudemo.entiy.Danmu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-03
 * Time: 9:23
 */
@Mapper
public interface DanmuMapper {
    @Insert("insert into danmu(video_id, text, time, color, border, mode) values (#{videoId}, #{text}, #{time},#{color}, #{border}, #{mode})")
    void insertDanmu(Danmu danmu);

    @Select("select * from danmu where video_id = #{videoId}")
    List<Danmu> getDanmuLIstByVideoId(Long videoId);
}
