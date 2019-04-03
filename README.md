# spring-cloud-yes
`Spring Cloud YES` 是一个构建在Spring Cloud基础上的、用于快速开发的脚手架。

## 技术选型

| 框架         | 作用           |
| ------------ | -------------- |
| Spring Cloud | 你懂的         |
| Keycloak     | 微服务认证授权 |
| Jenkins      | 持续集成       |
| SonarQube    | 代码质量控制   |

### Spring Cloud技术选型

| 组件              | 作用                | 备注                    |
| ----------------- | ------------------- | ----------------------- |
| Consul            | 服务发现 & 配置中心 | 近期提供Nacos版         |
| Feign             | 端到端调用          |                         |
| Hystrix           | 断路器              |                         |
| Turbine           | Hystrix监控聚合     |                         |
| Hystrix Dashboard | Hystrix监控界面     |                         |
| Zuul              | 网关                | 近期提供Cloud Gateway版 |



## 端口规划

| 项目                             | 端口                                   |
| -------------------------------- | -------------------------------------- |
| config-server                    | 18888                                  |
| consul                           | 8500                                   |
| hystrix-dashboard                | 7979                                   |
| turbine-server                   | 8989                                   |
| zuul-server                      | 5566（服务端口）、5567（actuator端口） |
| ms-content-sample（服务提供者）  | 8081                                   |
| ms-consumer-sample（服务消费者） | 8010                                   |



## 项目初始化

详见：[初始化文档](doc/1.初始化文档.md) ，包含：如何初始化项目



## 组件之间的关系

详见：[组件之间的关系](doc/2.组件之间的关系.md) ，包含：各种架构图



## 开发者指南

详见：[开发者指南](doc/3.开发者指南.md) ，包含项目规范、最佳实践



## 组件搭建及管理指南

详见：[组件搭建及管理指南](doc/4.组件搭建管理指南.md) ，包含Keycloak、Jenkins、SonarQube安装、部署、管理、运维等。



## Kubernetes部署

详见：[Kubernetes部署指南](doc/Kubernetes部署.md) ，包含从0部署的手把手步骤。



## TODO

1. Keycloak 动态授权例子：
   1. 有scope的
   2. 没有scope的
2. Docker Compose快速部署手把手教程 & 编排
3. Consul安装、部署管理说明
4. Kubernetes快速部署手把手教程 & 编排






