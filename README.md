# Zam 多技术集成事例后台展示系统

## 平台简介

Zam是在Spring Framework基础上搭建的一个Java基础开发平台，以Spring MVC为模型视图控制器，MyBatis为数据访问层，Mysql为数据库。

Zam主要定位于自用。

Zam目前包括以下四大模块，系统管理（SYS）模块、
工作流管理（ACT）模块、系统监控（MON）块、消息队列（AMQ）模块。 **系统管理模块** ，包括用户管理、
资源管理、图标管理； **工作流管理** ，包括部署管理、任务管理、请假管理;**系统监控模块**,包括系统监控; **消息队列模块**,包括发送模板(主要用于测试)。

## 内置功能

1.	用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  资源管理：用于管理项目中的菜单、按钮相关配置。
3.	角色管理：角色菜单权限分配。
4.  图标管理：用于展示LayUI中要用到的图标集。
...

## 技术选型

1、后端

* 核心框架：Spring Framework 4.3.4
* 安全框架：Apache Shiro 1.3.2
* 视图框架：Spring MVC 4.3.4
* 布局框架：Layui 2.3.0
* 工作流引擎：Activiti 5.15.1
* 持久层框架：MyBatis 3.4.6
* 数据库连接池：Alibaba Druid 1.1.10
* 缓存框架：Ehcache 1.3.2
* 工具类：Apache Commons、Jackson 2.2、POI 3.9等

...

2、前端

* JS框架：jQuery 1.9、Layui 2.3.0。
* CSS框架：Layui 2.3.0、[x-admin](http://x.xuebingsi.com/ "x-admin")。
...

3、中间件

*  消息队列：activeMQ 5.10.1

...

## 截图

....







