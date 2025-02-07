package com.emretest.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.write.url}")
    private String writeDbUrl;

    @Value("${spring.datasource.write.username}")
    private String writeDbUsername;

    @Value("${spring.datasource.write.password}")
    private String writeDbPassword;

    @Value("${spring.datasource.read.url}")
    private String readDbUrl;

    @Value("${spring.datasource.read.username}")
    private String readDbUsername;

    @Value("${spring.datasource.read.password}")
    private String readDbPassword;

    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(writeDbUrl); // URL'yi externalize edin
        hikariConfig.setUsername(writeDbUsername); // Externalized username
        hikariConfig.setPassword(writeDbPassword); // Externalized password
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(10); // Max pool size
        hikariConfig.setMinimumIdle(5); // Min idle connections
        hikariConfig.setIdleTimeout(30000); // Idle timeout
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "readDataSource")

    public DataSource readDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(readDbUrl); // URL'yi externalize edin
        hikariConfig.setUsername(readDbUsername); // Externalized username
        hikariConfig.setPassword(readDbPassword); // Externalized password
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(10); // Max pool size
        hikariConfig.setMinimumIdle(5); // Min idle connections
        hikariConfig.setIdleTimeout(30000); // Idle timeout
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public DataSource dataSource() {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("writeDataSource", writeDataSource());
        targetDataSources.put("readDataSource", readDataSource());
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(writeDataSource()); // Varsayılan olarak yazma veritabanı
        return routingDataSource;
    }
}
