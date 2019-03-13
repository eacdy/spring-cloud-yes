# Kafka单机版安装说明

**一、下载Kafka**

前往<http://kafka.apache.org/downloads> ，根据自己的需要，下载合适版本的Kafka，笔者使用的版本是kafka_2.12-0.10.2.1。

**二、启动Kafka**

- 启动Zookeeper。Kafka依赖Zookeeper，因此，启动Kafka前得先启动Zookeeper。在Kafka的根路径执行如下命令即可启动Zookeeper

  ```
  bin/zookeeper-server-start.sh config/zookeeper.properties &
  ```

- 启动Kafka。启动完Zookeeper后，即可启动Kafka。执行如下命令即可。

  ```
  bin/kafka-server-start.sh config/server.properties &
  ```

  

