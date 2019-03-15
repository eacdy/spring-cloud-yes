package com.itmuch.yes.configuration;

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

/**
 * @author itmuch.com
 */
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
                .title("内容中心API")
                .description("内容中心API")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("", "", "")).build();
    }

    @Bean
    public Docket customImplementation() {
        ParameterBuilder builder = new ParameterBuilder();
        Parameter parameter = builder
                //参数类型支持header, cookie, body, query etc
                .parameterType("header")
                //参数名
                .name("Token")
                .description("请输入您的JWT Token")
                //指定参数值的类型
                .modelRef(new ModelRef("string"))
                .required(false)
                .build();
        List<Parameter> parameters = Lists.newArrayList(parameter);

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itmuch"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo())
                .globalOperationParameters(parameters);
    }
}