# Kubernetes部署

## 备注

* 本文中的 `Keycloak` 使用H2数据库，不适用于生产环境，生产中需额外搭建数据库。本文用H2主要是为了节省内存。
* 本文中的MySQL也用Kubernetes Deployment启动，实际项目中建议将数据库运行在物理机，或使用StatefulSet。
* 本文中的挂载目录都是本地路径，实际项目中可考虑使用分布式存储。

## TODO：Zipkin整合





## 端口规划

| 服务              | 端口 | NodePort |
| ----------------- | ---- | -------- |
| MySQL             | 3306 | 30006    |
| Consul            | 8500 | 32001    |
| Keycloak          | 8080 | 32080    |
| Turbine Server    | 8989 | 32089    |
| Hystrix Dashboard | 7979 | 32079    |
| Zuul Server       | 5566 | 32006    |



## 准备工作

### 修改微服务的配置

* Fork `https://gitee.com/itmuch/spring-cloud-yes-config-repo` 仓库

* 修改名称带有 `k8s.yml` 的文件，找到类似如下内容，并将其修改为您的Keycloak的地址。

  ```yaml
  keycloak:
    auth-server-url: http://30.1.227.244:32080/auth
  ```

* 将目录切换至 `kubernetes` 目录
* 修改 `config/git2consul-config/git2consul.json` 文件，将其中的Git仓库地址修改为您想要的配置仓库的地址。



### 修改挂载目录

* 修改 `git2consul.yaml` ，修改

  ```yaml
  volumes:
  - name: git2consul-config
    hostPath:
      path: 改为config/git2consul-config目录在你机器上的绝对地址
  ```

* 修改 `mysql.yaml` ，修改

  ```yaml
  volumes:
  - name: mysql-init
    hostPath:
      path: 改为config/init-sql目录在你机器上的绝对地址
  - name: mysql-data
    hostPath:
      path: MySQL数据存储的路径，按需填写，绝对路径
  ```

* 修改 `keycloak.yaml` ，修改

  ```yaml
  volumes:
  - name: keycloak-init
    hostPath:
      path: 改为config/keycloak目录在你机器上的绝对地址
  ```



### 新建Keycloak测试用户

在名为 `Realm` 的Realm下，新建两个用户，用于测试，角色分别如下：

* user-role
* test-role

操作方法详见：《Keycloak初始化》



## 启动应用

依次执行如下命令即可。

```shell
# 启动Consul
kubectl create -f consul.yaml

# 启动git2consul，用于将配置仓库的信息同步到Consul中
kubectl create -f git2consul.yaml

# 启动keycloak，单点认证/授权服务器
kubectl create -f keycloak.yaml

# 启动MySQL
kubectl create -f mysql.yaml

# 启动服务提供者
kubectl create -f ms-content-sample.yaml

# 启动服务消费者
kubectl create -f ms-consumer-sample.yaml

# 启动Turbine
kubectl create -f turbine-server.yaml

# 启动Hystrix Dashboard
kubectl create -f hystrix-dashboard.yaml

# 启动Zuul
kubectl create -f zuul-server.yaml
```



## 应用停止

依次执行如下命令即可。

```shell
kubectl delete -f zuul-server.yaml
kubectl delete -f hystrix-dashboard.yaml
kubectl delete -f turbine-server.yaml
kubectl delete -f ms-consumer-sample.yaml
kubectl delete -f ms-content-sample.yaml
kubectl delete -f mysql.yaml
kubectl delete -f keycloak.yaml
kubectl delete -f git2consul.yaml
kubectl delete -f consul.yaml
```



## 测试基本功能

* 访问 `http://localhost:32001/ui/dc1/services` 可看到Consul上的服务；
* 访问 `http://localhost:32080/auth` 可看到Keycloak首页，输入账号/密码：`admin/admin` 可登陆管理；
* 访问 `http://localhost:32089/turbine.stream?cluster=zuul-server` 可看到Turbine监控页
* 访问 `http://localhost:32079/` 可看到Hystrix Dashboard首页



## 测试业务

* 测试1：访问 `http://localhost:32006/ms-consumer-sample/articles` 弹出登录框，使用 `user-role` 角色的用户登录，发现可访问；使用 `test-role` 角色的用户登录，无法访问。
* 测试2：访问 `http://localhost:32006/logout` 退出
* 测试3：访问 `http://localhost:32006/ms-content-sample/articles/1` 可正常访问，无需登录，可匿名访问。
* 测试4：将 `http://turbine-server:8989/turbine.stream?cluster=zuul-server` 贴到Hystrix Dashboard上，可看到Zuul的Hystrix监控图表。