package com.pheonix.core.utils.config;

import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.interceptor.AuthEnforcerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MVCConfig implements WebMvcConfigurer {

    private final AuthEnforcerInterceptor authEnforcerInterceptor;

    List<String> pathsForAuthFilterCheck  = Arrays.asList(RestConstants.PURCHASE + "/**", RestConstants.FILES + "/**");

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authEnforcerInterceptor).addPathPatterns(pathsForAuthFilterCheck);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
