package com.golf.two_for_tom_open.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@Profile("prod")
public class prodDbConfig {

    @Value("${DB_HOST:localhost}")
    private String host;

    @Value("${DB_PORT:5432}")
    private String port;

    @Value("${DB_NAME:minigolf}")
    private String dbName;

    @Value("${DB_USERNAME}")
    private String dbUserName;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @Bean
    public BasicDataSource dataSource() {
        log.info("port is: " + port);
        log.info("username is: " + dbUserName);

        String dbUrl = "jdbc:postgresql://" + host + ':' + port + "/" + dbName + "?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(dbUserName);
        basicDataSource.setPassword(dbPassword);

        return basicDataSource;
    }
}
