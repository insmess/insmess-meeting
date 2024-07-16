package com.insmess.meeting.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 分组名称
                .groupName("模块化开发的系统接口测试文档")
                .select()
                //这里标注控制器的位置
                .apis(RequestHandlerSelectors.basePackage("com.insmess.meeting.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api信息
     * @return api对象信息
     */
    private ApiInfo apiInfo()   {
        return new ApiInfoBuilder()
                .title("模块化开发的系统接口测试文档")  // 标题
                .description("用户中心接口文档")  // 简介
//                .contact(new Contact("ssy","http://localhost:8090/","431975683@qq.com"))
                .version("1.0")
                .build();
    }
}