-- 创建数据库
drop database if exists myblog;
create database myblog DEFAULT CHARACTER SET utf8mb4;
-- 使⽤数据数据
use myblog;
-- 创建表[⽤户表]
drop table if exists userinfo;
create table userinfo
(
    id         int primary key auto_increment,
    username   varchar(100) not null unique ,
    password   varchar(65)  not null,
    nickname   varchar(100) default '',
#     nickname   varchar(100)  not null,
    photo      varchar(500) default 'images/defaultPhoto.jpg',
    email      varchar(65)  default '',
    codecloud  varchar(65)  default '',
    createtime datetime     default now(),
    updatetime datetime     default now(),
    state      int          default 1
) default charset 'utf8mb4';
-- 创建⽂章表
drop table if exists articleinfo;
create table articleinfo
(
    id         int primary key auto_increment,
    title      varchar(100) not null,
    content    text         not null,
    createtime datetime              default now(),
    updatetime datetime              default now(),
    uid        int          not null,
    rcount     int          not null default 1,
    state      int                   default 1
) default charset 'utf8mb4';
-- 创建视频表
drop table if exists videoinfo;
create table videoinfo
(
    vid        int primary key,
    title      varchar(250),
    url        varchar(1000),
    createtime datetime default now(),
    updatetime datetime default now(),
    uid        int
) default charset 'utf8mb4';
-- 添加⼀个⽤户信息 用户admin 密码123
INSERT INTO `myblog`.`userinfo` (`id`, `username`, `password`,`nickname`, `photo`, `email`,`codecloud`,
                                   `createtime`, `updatetime`, `state`)
VALUES (1, 'admin', '6c8d5f9332c143d5a1f3130c8c93aa28$fb3c06583fb4d64a8b228a874fcd5b5a','管理员', 'images/defaultPhoto.jpg', '123@163.com','www.github.com/admin',  '2021-12-06 17:10:48', '2021-12-06 17:10:48', 1);
-- ⽂章添加测试数据
insert into articleinfo(title, content, uid)
values ('Java', 'Java正⽂', 1), ('C++', 'C++正⽂', 1), ('Python', 'Python正⽂', 1);
-- 添加视频
insert into videoinfo(vid, title, url, uid)
values (1, 'baidu title', 'https://www.baidu.com', 1);
