create database if not exists myoj default character set utf8mb4;
use myoj;

# 用户表
drop table if exists user;
create table user
(
    id          int primary key auto_increment,
    username    varchar(20) not null unique,
    password    varchar(30) not null,
    nickname    varchar(50),
    email       varchar(50) not null unique,
    user_pic    varchar(128),
    create_time datetime     default now(),
    update_time datetime     default now()
);

# 题目合集表
drop table if exists collection;
create table collection
(
    id          int primary key auto_increment,
    name        varchar(50) not null unique,
    description varchar(4090),
    create_time datetime default now(),
    update_time datetime default now()
);

# 题目表
drop table if exists problem;
create table problem
(
    id            int primary key auto_increment,
    title         varchar(100) not null,
    level         varchar(50),
    collection_id int,
    description   varchar(4090),
    template_code varchar(4090),
    test_code     varchar(4090),
    create_time   datetime default now(),
    update_time   datetime default now()
);

# 提交记录表
drop table if exists submission;
create table submission
(
    id              int auto_increment primary key,
    user_id         int,
    problem_id      int,
    submission_time datetime default now(),
    language        varchar(50),
    code            text,
    status          enum ('pending', 'judging', 'accepted', 'wrong answer', 'runtime error', 'time limit exceeded', 'memory limit exceeded'), -- '待处理', '评判中', '已接受', '答案错误', '运行时错误', '超时', '内存超限'
    execution_time  int,                                                                                                                      -- 执行时间
    memory_usage    int                                                                                                                       -- 内存使用
);
