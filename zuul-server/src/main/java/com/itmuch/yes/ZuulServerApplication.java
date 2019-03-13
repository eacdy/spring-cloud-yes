package com.itmuch.yes;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@SpringCloudApplication
public class ZuulServerApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
