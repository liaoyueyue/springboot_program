drop database if exists danmu_demo;
create database danmu_demo character set utf8mb4;

use danmu_demo;

drop table if exists user;
create table user
(
    id          int primary key auto_increment,
    name        varchar(64),
    password    varchar(64),
    create_time timestamp default now()
);
insert into user(name, password)
values ('alice', '123');

drop table if exists danmu;
create table danmu
(
    id          int primary key auto_increment,
    text        varchar(64),
    username    varchar(64),
    create_time timestamp default now()
);
insert into danmu(text, username)
values ('这是一条弹幕', 'alice');
