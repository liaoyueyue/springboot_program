package com.example.danmudemo.mapper;

import com.example.danmudemo.entiy.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}




