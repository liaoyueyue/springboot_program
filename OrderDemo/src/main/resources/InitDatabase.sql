drop database if exists order_demo;
create database order_demo character set utf8mb4;

use order_demo;

drop table if exists usertbl;
create table usertbl
(
    id          int primary key auto_increment,
    username    varchar(64) unique not null,
    password    varchar(64)        not null,
    name        varchar(64),
    gender      varchar(64),
    permission  varchar(64),
    remark      varchar(64),
    phone       varchar(64),
    create_time datetime default now(),
    update_time datetime default now()
);
insert into usertbl(username, password, name, gender, permission, remark, phone)
values ('admin', '123', '张三', '男', '管理员', '开发经验20年，高级JAVA开发工程师', '12312341234');

drop table if exists tabletbl;
create table tabletbl
(
    id          int primary key auto_increment,
    ord_id      int,
    num         varchar(64),
    flag        varchar(64),
    description varchar(64)
);
insert into tabletbl(ord_id, num, flag, description)
values (1, 2, '0', '大桌1'),(2, 3, '1', '大桌2');

