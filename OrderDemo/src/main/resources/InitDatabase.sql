drop database if exists order_demo;
create database order_demo character set utf8mb4;

use order_demo;

drop table if exists user;
create table user
(
    id          int primary key auto_increment,
    username    varchar(64) unique not null ,
    password    varchar(64) not null,
    email       varchar(64),
    phone       varchar(64),
    create_time datetime default now(),
    update_time datetime default now()
);
insert into user(username, password)
values ('admin', '123'),
       ('alice', '123'),
       ('mike', '123');