create database simple_user_system default character set utf8mb4 collate utf8mb4_bin;
-- grant all privileges on simple_user_system.* to 'fengwk'@'%';

drop table if exists user;
create table user
(
    id              bigint unsigned not null auto_increment comment '主键',
    gmt_created     datetime not null comment '创建时间',
    gmt_modified    datetime not null comment '修改时间',
    version         bigint unsigned not null comment '数据版本号',
    user_id         bigint unsigned not null comment '用户id',
    username        varchar(32) comment '用户名',
    password_digest varchar(64) comment '密码摘要',
    mobile          varchar(16) comment '手机号',
    email           varchar(128) comment '邮箱',
    properties      text not null comment '属性表，json格式',
    primary key (id),
    index idx_gmtModified(gmt_modified),
    unique uk_userId(user_id),
    unique uk_username(username),
    unique uk_mobile(mobile),
    unique uk_email(email)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='用户表';