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
    username   varchar(100) not null unique,
    password   varchar(65)  not null,
    nickname   varchar(100) not null,
    photo      varchar(500) default '/upload/defaultPhoto.jpg',
    email      varchar(65)  default '',
    codecloud  varchar(65)  default 'https://www.gitee.com',
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
-- 创建评论表
drop table if exists commentinfo;
CREATE TABLE commentinfo
(
    id          int primary key auto_increment,
    aid         int  not null,
    uid         int  not null,
    ctext text not null,
    createtime  datetime default now(),
    updatetime  datetime default now()
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
INSERT INTO `myblog`.`userinfo` (`id`, `username`, `password`, `nickname`, `photo`, `email`, `codecloud`,
                                 `createtime`, `updatetime`, `state`)
VALUES (1, 'admin', '6c8d5f9332c143d5a1f3130c8c93aa28$fb3c06583fb4d64a8b228a874fcd5b5a', '管理员',
        '/upload/defaultPhoto.jpg', '123456789@163.com', 'https://www.gitee.com', '2021-12-06 17:10:48',
        '2021-12-06 17:10:48', 1);
-- ⽂章添加测试数据
insert into articleinfo(title, content, uid)
values ('Java', 'Java是一种面向对象的编程语言，由Sun Microsystems（现在为Oracle Corporation）于1995年推出。Java具有跨平台性，即一次编写的程序可以在不同的操作系统上运行，这是通过Java虚拟机（JVM）实现的。以下是Java的一些主要特点和用途：

1. 面向对象：Java是一种完全面向对象的语言，支持封装、继承和多态等面向对象的概念。

2. 跨平台性：Java程序编译后生成字节码，可以在任何有安装Java虚拟机的操作系统上运行，使得Java成为一种具有跨平台特性的语言。

3. 安全性：Java提供了安全性机制，如内建的安全管理器和字节码验证器，用于防止恶意代码的执行。

4. 强大的类库：Java拥有丰富的类库，提供了各种功能，包括输入输出、网络通信、图形用户界面、数据库连接等，开发者可以直接使用这些类库，加快开发速度。

5. 大型社区和生态系统：Java拥有庞大的开发者社区和丰富的第三方库和框架，开发者可以从中获取支持和资源。

Java广泛应用于各个领域，包括服务器端开发、移动应用开发、桌面应用程序、嵌入式系统、大数据处理等。它是世界上最流行的编程语言之一，被许多大型企业和互联网公司广泛采用。',
        1),
       ('C++', 'C++是一种通用的高级编程语言，最初由Bjarne Stroustrup于1983年在C语言的基础上开发而成。C++是C语言的扩展，添加了面向对象编程的特性，同时仍然保留了C语言的底层编程能力。

C++具有以下特点：

1. 面向对象：C++支持类和对象的概念，可以使用封装、继承和多态等面向对象编程的特性。这使得C++能够更好地组织和管理大型程序，提高代码的可重用性和可维护性。

2. 高效性：C++具有接近于底层的性能，可以直接访问计算机的硬件资源。它支持指针和引用等底层概念，允许直接操作内存。这使得C++成为开发高性能和资源敏感的应用程序的首选语言。

3. 泛型编程：C++引入了模板的概念，使得编写泛型代码变得更容易。通过模板，可以实现通用的数据结构和算法，从而提高代码的灵活性和重用性。

4. 可移植性：C++标准库提供了丰富的功能和跨平台的支持，使得开发人员可以编写具有较好可移植性的程序。C++的代码可以在多个操作系统和硬件平台上运行。

5. 扩展性：C++具有丰富的第三方库支持，开发人员可以利用这些库来扩展C++的功能。同时，C++还支持与其他编程语言（如C、Java和Python）的接口，可以方便地与其他语言进行集成。

总之，C++是一种功能强大且灵活的编程语言，适用于各种应用场景，包括系统软件、游戏开发、嵌入式系统、高性能计算等。它在计算机科学领域有着广泛的应用和影响。',
        1),
       ('Python', 'Python是一种高级的、动态的、解释型的编程语言，由Guido van Rossum于1991年首次发布。Python的设计理念强调代码的可读性和简洁性，使得开发人员可以用更少的代码表达同样的功能。它具有以下特点：

1. 简洁易读：Python采用简洁的语法和清晰的代码结构，使得代码易于理解和阅读。这使得Python非常适合初学者入门，也提高了团队合作开发的效率。

2. 功能丰富：Python拥有丰富的标准库和第三方库，提供了许多可用于开发各种应用程序的功能。例如，NumPy和Pandas用于科学计算和数据分析，Django和Flask用于Web开发，TensorFlow和PyTorch用于机器学习等。

3. 跨平台性：Python可以在多个操作系统上运行，包括Windows、Linux和Mac OS。这使得开发人员可以轻松地在不同平台上进行开发和部署。

4. 动态类型：Python是一种动态类型语言，它允许变量在运行时自动确定类型。这使得代码编写更加灵活，但也要注意变量类型的正确使用，以避免潜在的错误。

5. 面向对象：Python支持面向对象编程，可以使用类、对象、继承和多态等概念来组织和管理代码。这使得Python可以构建复杂的软件系统，并提高代码的可重用性和可维护性。

6. 社区支持：Python拥有庞大活跃的开源社区，提供了大量的教程、文档和开源项目。开发人哦豁,我回答出错了耶~ 你重新试一试呗~

', 1);
-- 添加评论
insert into commentinfo(aid, uid, commenttext) VALUES (1, 1, '写的太好了，支持！');
-- 添加视频
insert into videoinfo(vid, title, url, uid)
values (1, 'baidu title', 'https://www.baidu.com', 1);
