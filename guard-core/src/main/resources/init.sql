create
database guard default character set utf8mb4 collate utf8mb4_bin;
-- grant all privileges on guard.* to 'fengwk'@'%';

drop table if exists user_namespace;
create table user_namespace
(
    id                    bigint unsigned not null auto_increment comment '主键',
    gmt_created           datetime     not null comment '创建时间',
    gmt_modified          datetime     not null comment '修改时间',
    user_namespace        varchar(32)  not null comment '用户命名空间',
    description           varchar(128) not null comment '用户命名空间的描述',
    user_system_type      varchar(32)  not null comment '当前用户命名空间关联的用户系统类型',
    user_system_namespace varchar(32)  not null comment '当前用户命名空间关联的用户系统命名空间',
    is_enabled            tinyint unsigned not null comment '客户端是否启用，0-禁用，1-启用',
    primary key (id),
    index                 idx_gmtModified(gmt_modified),
    unique uk_userNamespace(user_namespace)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='用户命名空间表';

drop table if exists client;
create table client
(
    id                            bigint unsigned not null auto_increment comment '主键',
    gmt_created                   datetime     not null comment '创建时间',
    gmt_modified                  datetime     not null comment '修改时间',
    client_id                     varchar(32)  not null comment '客户端id',
    name                          varchar(32)  not null comment '客户端名称',
    user_namespace                varchar(32)  not null comment '客户端绑定的用户命名空间',
    client_secret_key             varchar(32)  not null comment '客户端密钥',
    redirect_uri_pattern          varchar(128) not null comment '支持的重定向地址模式',
    authorization_code_expires_in bigint unsigned not null comment '授权码过期时间，单位：秒',
    access_token_expires_in       bigint unsigned not null comment '访问令牌过期时间，单位：秒',
    refresh_token_expires_in      bigint unsigned not null comment '刷新令牌过期时间，单位：秒',
    is_enabled                    tinyint unsigned not null comment '客户端是否启用，0-禁用，1-启用',
    primary key (id),
    index                         idx_gmtModified(gmt_modified),
    unique uk_clientId(client_id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='客户端表';

----------------------------
-- 以下表需要支持命名空间隔离 --
----------------------------
drop table if exists authorization_code;
create table authorization_code
(
    id           bigint unsigned not null auto_increment comment '主键',
    gmt_created  datetime      not null comment '创建时间',
    gmt_modified datetime      not null comment '修改时间',
    code         varchar(32)   not null comment '授权码',
    user_id      varchar(64)   not null comment '授权码关联的用户id',
    client_id    varchar(32)   not null comment '授权码关联的客户端id',
    redirect_uri varchar(1024) not null comment '重定向地址',
    scope        varchar(256)  not null comment '申请的权限范围',
    gmt_expired  datetime      not null comment '过期时间',
    is_used      tinyint unsigned not null comment '是否已使用，0-未使用，1-已使用',
    primary key (id),
    index        idx_gmtModified(gmt_modified),
    unique uk_code(code)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='授权码表';

drop table if exists token;
create table token
(
    id                        bigint unsigned not null auto_increment comment '主键',
    gmt_created               datetime    not null comment '创建时间',
    gmt_modified              datetime    not null comment '修改时间',
    access_token              varchar(32) not null comment '访问令牌',
    gmt_access_token_expired  datetime    not null comment '访问令牌过期时间',
    refresh_token             varchar(32) not null comment '刷新令牌',
    gmt_refresh_token_expired datetime    not null comment '刷新令牌过期时间',
    client_id                 varchar(32) not null comment '令牌关联的客户端id',
    user_id                   varchar(64) not null comment '令牌关联的用户id',
    is_revoke                 tinyint unsigned not null comment '是否已回收，0-未回收，1-已回收',
    primary key (id),
    index                     idx_gmtModified(gmt_modified),
    unique uk_accessToken(access_token),
    index                     idx_refreshToken_gmtModified(refresh_token, gmt_modified)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='令牌表';

drop table if exists role;
create table role
(
    id           bigint unsigned not null auto_increment comment '主键',
    gmt_created  datetime     not null comment '创建时间',
    gmt_modified datetime     not null comment '修改时间',
    role_id      bigint unsigned not null comment '角色id',
    name         varchar(32)  not null comment '角色名称',
    description  varchar(128) not null comment '角色描述',
    is_enabled   tinyint unsigned not null comment '角色是否启用，0-禁用，1-启用',
    primary key (id),
    index        idx_gmtModified(gmt_modified),
    unique uk_roleId(role_id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='角色表';

drop table if exists permission;
create table permission
(
    id                  bigint unsigned not null auto_increment comment '主键',
    gmt_created         datetime     not null comment '创建时间',
    gmt_modified        datetime     not null comment '修改时间',
    permission_id       bigint unsigned not null comment '权限id',
    name                varchar(32)  not null comment '权限名称',
    description         varchar(128) not null comment '权限描述',
    action              varchar(16)  not null comment '动作，如read、write',
    resource_descriptor varchar(128) not null comment '资源描述符',
    is_enabled          tinyint unsigned not null comment '权限是否启用，0-禁用，1-启用',
    primary key (id),
    index               idx_gmtModified(gmt_modified),
    unique uk_permissionId(permission_id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='权限表';

drop table if exists user_role;
create table user_role
(
    id           bigint unsigned not null auto_increment comment '主键',
    gmt_created  datetime    not null comment '创建时间',
    gmt_modified datetime    not null comment '修改时间',
    user_id      varchar(64) not null comment '用户id',
    role_id      bigint unsigned not null comment '角色id',
    primary key (id),
    index        idx_gmtModified(gmt_modified),
    unique uk_userId_roleId(user_id, role_id),
    index        idx_roleId(role_id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='用户角色关联表';

drop table if exists role_permission;
create table role_permission
(
    id            bigint unsigned not null auto_increment comment '主键',
    gmt_created   datetime not null comment '创建时间',
    gmt_modified  datetime not null comment '修改时间',
    role_id       bigint unsigned not null comment '角色id',
    permission_id bigint unsigned not null comment '权限id',
    primary key (id),
    index         idx_gmtModified(gmt_modified),
    unique uk_roleId_permissionId(role_id, permission_id),
    index         idx_permissionId(permission_id)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_bin comment='角色权限关联表';
