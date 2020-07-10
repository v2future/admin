package com.hollycrm.mi.core.config;

import com.hollycrm.mi.core.cache.redis.RedisCacheManager;
import com.hollycrm.mi.core.kit.CacheKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiConfig implements WebMvcConfigurer {

    @Autowired
    RedisCacheManager redisCacheManager;

    @Bean
    public Object init() {
        CacheKit.init( redisCacheManager);
        return null;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
