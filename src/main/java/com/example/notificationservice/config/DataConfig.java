package com.example.notificationservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataConfig {
    @Configuration
    public class DatabaseConfig {

        @Value("${DB_HOST}")
        private String host;

        @Value("${DB_PORT}")
        private String port;

        @Value("${DB_NAME}")
        private String dbName;

        @Value("${DB_USER}")
        private String username;

        @Value("${DB_PASSWORD}")
        private String password;

        @Bean
        public DataSource dataSource() {
            return DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url("jdbc:postgresql://" + host + ":" + port + "/" + dbName)
                    .username(username)
                    .password(password)
                    .build();
        }
    }
}
