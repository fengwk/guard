# Simple Sender Client InJvm

simple-sender-client-injvm模块是基于SpringBoot且与被依赖者同属一个JVM进程的AbstractUserSystem库。

# Usage

1、通常在业务逻辑模块中引入abstract-user-system依赖：

```xml
<dependency>
    <groupId>fun.fengwk.guard</groupId>
    <artifactId>abstract-user-system</artifactId>
</dependency>
```

2、在部署模块中引入simple-user-system-aus-injvm依赖：

```xml
<dependency>
    <groupId>fun.fengwk.guard</groupId>
    <artifactId>simple-user-system-aus-injvm</artifactId>
</dependency>
```

3、添加SpringBoot配置：

```yaml
spring:
  application:
    name: sus-injvm
  datasource:
    url: jdbc:mysql://mysql.fengwk.fun:3306/simple_user_system?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&connectTimeout=3000&socketTimeout=3000&useSSL=false
    driver-class-name: com.mysql.jdbc.Driver
    username: fengwk
    password: a123
```