# Abstract User System SUS InJVM

simple-user-system-aus-injvm模块是基于SpringBoot且与被依赖者同属一个JVM进程的客户端库，当前仅支持邮件发送功能。

Simple User System bridging to Abstract User System in jvm.

推荐的抽象用户系统使用方式是在逻辑实现层依赖abstract-user-system，在应用部署层依赖一个具体实现，比如abstract-user-system-sus-injvm。