package com.hollycrm.mi.core.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    @Value("${jdbc.driver-class-name}")
    private String driverclassname;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.max-idle}")
    private Integer maxIdle;

    @Value("${jdbc.max-total}")
    private Integer maxTotal;

    @Bean(name = "dataSource")
    public BasicDataSource getBasicDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverclassname);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setMaxIdle(maxIdle);
        basicDataSource.setMaxTotal(maxTotal);
        basicDataSource.setValidationQuery("select 1 from dual");
        basicDataSource.setTestOnBorrow(true);
        basicDataSource.setTestOnReturn(true);
        basicDataSource.setTestWhileIdle(true);
        basicDataSource.setTimeBetweenEvictionRunsMillis(600000);
        basicDataSource.setMinEvictableIdleTimeMillis(6000);
        return basicDataSource;
    }

}
