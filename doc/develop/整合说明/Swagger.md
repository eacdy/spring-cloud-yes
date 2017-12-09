# Swagger

## 加依赖

```xml
<!-- swagger -->
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
</dependency>
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
</dependency>
```

## 写配置

```java
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
                .modelRef(new ModelRef("string"))//指定参数值的类型
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
```



