package com.pheonix.core.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * Swagger config.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(basePackage("com.pheonix.core.controller"))
                .paths(PathSelectors.any()).build().apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo("Phoenix Core Service", "This is the core service application", "1.0", "Terms of Service", new Contact("Phoenix", "Phoenix-Rising from the Ashes", "ayushsurana2018@gmail.com"),
                "ServApp", "https://www.servapp.com", Collections.emptyList());
    }
}
