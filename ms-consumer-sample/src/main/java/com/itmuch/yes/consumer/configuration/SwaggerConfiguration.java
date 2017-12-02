package com.itmuch.yes.consumer.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * swagger 信息
     *
     * @return 页面信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("消费者API")
                .description("消费者API")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("", "", "")).build();
    }

    @Bean
    public Docket customImplementation() {
        ParameterBuilder builder = new ParameterBuilder();
        Parameter parameter = builder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("Token") //参数名
                .description("请输入您的JWT Token")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false)
                .build();
        List<Parameter> parameters = Lists.newArrayList(parameter);

        return new Docket(DocumentationType.SWAGGER_2)
                // TODO: 2017/11/2 等等待配置域名
                //.host()
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itmuch"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo())
                .globalOperationParameters(parameters);
    }
}