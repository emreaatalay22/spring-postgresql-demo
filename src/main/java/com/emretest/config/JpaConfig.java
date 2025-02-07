package com.emretest.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource); // Doğru DataSource enjekte edilmesi
        em.setPackagesToScan("com.emretest.entities"); // Entity paketinin doğru tanımlanması
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter()); // JpaVendorAdapter ayarları
        em.setJpaProperties(hibernateProperties()); // Hibernate özelliklerinin eklenmesi
        return em;
    }

    // Hibernate için Vendor Adapter ayarları
    private JpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true); // SQL sorgularını göster
        adapter.setGenerateDdl(true); // DDL oluşturma
        return adapter;
    }

    // Hibernate özellikleri
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // PostgreSQL Dialect
        properties.put("hibernate.hbm2ddl.auto", "update"); // Hibernate DDL ayarı
        properties.put("hibernate.show_sql", "false"); // SQL sorgularını konsolda göster
        properties.put("hibernate.format_sql", "true"); // SQL'i formatlı göster
        properties.put("hibernate.jdbc.lob.non_contextual_creation", "true"); // LOB ayarları
        return properties;
    }


}