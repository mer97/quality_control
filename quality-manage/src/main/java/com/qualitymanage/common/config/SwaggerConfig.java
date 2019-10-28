package com.qualitymanage.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置类
 *
 * @author lihai
 * Create Date: 2019-10-21
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact(
                "quality", "", ""
        );
        return new ApiInfoBuilder()
                .title("质量数据模块API接口")
                .description("API接口")
                .contact(contact)
                .version("1.0.0")
                .build();
    }

}
