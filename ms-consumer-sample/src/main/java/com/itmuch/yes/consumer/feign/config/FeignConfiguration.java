package com.itmuch.yes.consumer.feign.config;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    /**
     * 支持上传
     *
     * @return SpringFormEncoder
     */
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }

    /**
     * 整合Keycloak的拦截器
     * 参考：https://github.com/thomasdarimont/jcon2017-keycloak
     *
     * @return 拦截器
     */
    @Bean
    public RequestInterceptor keycloakSecurityContextRequestInterceptor() {
        return new KeycloakRequestInterceptor();
    }
}