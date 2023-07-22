package com.e2i.wemeet.web.config;

import com.e2i.wemeet.web.global.interceptor.IdentifierCheckInterceptor;
import com.e2i.wemeet.web.global.interceptor.TeamCheckInterceptor;
import com.e2i.wemeet.web.global.resolver.invitation.InvitationArgumentResolver;
import com.e2i.wemeet.web.util.secure.Cryptography;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@ConfigurationPropertiesScan(basePackages = "com.e2i.wemeet.web")
@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    private final Cryptography cryptography;

    @Autowired
    public GlobalConfig(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TeamCheckInterceptor())
            .order(1)
            .addPathPatterns("/v1/web/phone/**", "/v1/web/register/**");

        registry.addInterceptor(new IdentifierCheckInterceptor())
            .order(2)
            .addPathPatterns("/v1/web/college/**", "/v1/web/profile/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new InvitationArgumentResolver(cryptography));
    }
}
