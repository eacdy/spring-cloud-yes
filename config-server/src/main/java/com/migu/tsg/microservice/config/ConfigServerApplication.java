package com.migu.tsg.microservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
