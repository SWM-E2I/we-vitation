package com.e2i.wemeet.web.config;

import com.e2i.wemeet.web.global.interceptor.TeamCheckInterceptor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@ConfigurationPropertiesScan(basePackages = "com.e2i.wemeet.web")
@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TeamCheckInterceptor())
            .order(1)
            .addPathPatterns("/v1/web/**")
            .excludePathPatterns("/v1/web/main", "/v1/web/error");
    }
}
