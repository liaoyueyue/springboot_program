drop database if exists order_demo;
create database order_demo character set utf8mb4;

use order_demo;

drop table if exists user;
create table user
(
    id          int primary key auto_increment,
    username    varchar(64) unique not null ,
    password    varchar(64) not null,
    name        varchar(64),
    gender      varchar(64),
    permission  varchar(64),
    remark      varchar(64),
    phone       varchar(64),
    create_time datetime default now(),
    update_time datetime default now()
);
insert into user(username, password, name, gender, permission, remark, phone)
values ('admin', '123', '张三', '男', '管理员', '开发经验20年，高级JAVA开发工程师', '12312341234');
