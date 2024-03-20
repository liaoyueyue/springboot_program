package com.example.danmudemo.mapper;

import com.example.danmudemo.entiy.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liaoyueyue
 * @description 针对表【video】的数据库操作Mapper
 * @createDate 2024-03-13 11:47:52
 * @Entity com.example.danmudemo.entiy.Video
 */
@Mapper
public interface VideoMapper {
    @Insert("insert into video(title, image_path, video_path) values (#{title}, #{imagePath}, #{videoPath})")
    void insertVideo(Video video);

    @Select("select * from video")
    List<Video> getVideoList();

    @Select("select * from video where id = #{id}")
    Video getVideoById(Integer id);

    @Update("update video set title = #{title}, video_path = #{videoPath}, image_path = #{imagePath} where id = #{id}")
    void updateVideoById(Integer id, String title, String videoPath, String imagePath);

    @Delete("delete from video where id= #{id}")
    void delVideoById(Integer id);
}




