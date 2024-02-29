drop database if exists order_demo;
create database order_demo character set utf8mb4;

use order_demo;

drop table if exists user;
create table user
(
    id          int primary key auto_increment,
    name        varchar(64),
    password    varchar(64),
    create_time timestamp default now()
);
insert into user(name, password)
values ('alice', '123'),
       ('mike', '123'),
       ('franklin', '123');