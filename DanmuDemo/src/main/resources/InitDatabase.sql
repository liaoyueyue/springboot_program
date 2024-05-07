drop database if exists danmu_demo;
create database danmu_demo character set utf8mb4;

use danmu_demo;

drop table if exists user;
create table user
(
    id          int primary key auto_increment,
    username    varchar(64),
    password    varchar(64),
    create_time timestamp default now()
);
insert into user(username, password)
values ('admin', '123');

drop table if exists video;
create table video
(
    id          int primary key auto_increment,
    title       varchar(64),
    video_path  varchar(128),
    image_path  varchar(128),
    upload_time timestamp default now()
);
insert into video(title, video_path, image_path)
values ('这里是我测试的第一个视频', 'yourname.mp4', 'yourname.png');

drop table if exists danmu;
create table danmu
(
    id       bigint primary key auto_increment,
    video_id bigint       not null,
    text     varchar(255) not null,
    time     double       not null,
    color    varchar(255) not null,
    border   bit          not null,
    mode     int          not null
);
insert into danmu(video_id, text, time, color, border, mode) values (1, '这是一条弹幕', 1.0, '#FFFFFF', true, 0)
