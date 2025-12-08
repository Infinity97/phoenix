package com.pheonix.user.management.utils.config;

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
                .apis(basePackage("com.pheonix.user.management.controller"))
                .paths(PathSelectors.any()).build().apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo("Phoenix User Management Service", "This is the user management service application", "1.0", "Terms of Service", new Contact("Carscan dev", "info@carscan.co.za", "info@carscan.co.za"),
                "Car scan", "https://www.carscan.ai/", Collections.emptyList());
    }
}
