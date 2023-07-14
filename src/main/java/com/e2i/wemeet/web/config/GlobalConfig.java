package com.e2i.wemeet.web.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ConfigurationPropertiesScan(basePackages = "com.e2i.wemeet.web")
@Configuration
public class GlobalConfig {

}
