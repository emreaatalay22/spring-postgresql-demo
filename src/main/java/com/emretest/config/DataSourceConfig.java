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
import java.util.UUID;

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

    @Value("${spring.datasource.hikari.connectionTimeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private long idleTimeout;


    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {
       System.out.println("Initializing Write DataSource: {}");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(writeDbUrl); // URL'yi externalize edin
        hikariConfig.setUsername(writeDbUsername); // Externalized username
        hikariConfig.setPassword(writeDbPassword); // Externalized password
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(maximumPoolSize); // Max pool size
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setMinimumIdle(minimumIdle);
        hikariConfig.setIdleTimeout(idleTimeout); // Idle timeout

        // Kubernetes ortam değişkeninden hostname bilgisini al
        String hostName = System.getenv("HOSTNAME");
        if (hostName == null || hostName.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            hostName = uuid.toString();
            // Eğer HOSTNAME tanımlı değilse, varsayılan değer kullan
        }
        hikariConfig.setPoolName(hostName + "-hikari");

        return new HikariDataSource(hikariConfig);

        /*
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); // ✅ PostgreSQL için doğru sürücü
        dataSource.setUrl(writeDbUrl);
        dataSource.setUsername(writeDbUsername);
        dataSource.setPassword(writeDbPassword);
        return dataSource;
        */

    }

    @Bean(name = "readDataSource")

    public DataSource readDataSource() {
        System.out.println("Initializing Read DataSource: {}");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(readDbUrl); // URL'yi externalize edin
        hikariConfig.setUsername(readDbUsername); // Externalized username
        hikariConfig.setPassword(readDbPassword); // Externalized password
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(maximumPoolSize); // Max pool size
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setMinimumIdle(minimumIdle);
        hikariConfig.setIdleTimeout(idleTimeout); // Idle timeout
        // Kubernetes ortam değişkeninden hostname bilgisini al
        String hostName = System.getenv("HOSTNAME");
        if (hostName == null || hostName.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            hostName = uuid.toString();
            // Eğer HOSTNAME tanımlı değilse, varsayılan değer kullan
        }
        hikariConfig.setPoolName(hostName + "-hikari");
        return new HikariDataSource(hikariConfig);

/*
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); // ✅ PostgreSQL için doğru sürücü
        dataSource.setUrl(readDbUrl);
        dataSource.setUsername(readDbUsername);
        dataSource.setPassword(readDbPassword);
        return dataSource;
        */
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
