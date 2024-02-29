drop database if exists danmu_demo;
create database danmu_demo character set utf8mb4;

use danmu_demo;

drop table if exists users;
create table users
(
    id          int primary key auto_increment,
    name        varchar(64),
    password    varchar(64),
    create_time timestamp default now()
);
insert into users(name, password)
values ('alice', '123');

drop table if exists danmus;
create table danmus
(
    id          int primary key auto_increment,
    text        varchar(64),
    username    varchar(64),
    create_time timestamp default now()
);
insert into danmus(text, username)
values ('这是一条弹幕', 'alice');
