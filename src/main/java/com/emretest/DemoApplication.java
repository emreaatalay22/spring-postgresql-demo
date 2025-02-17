package com.emretest;


import com.emretest.config.ApplicationConfig;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EntityScan(basePackages = "com.emretest.entities")  // 👈 Varlık paketini tanımlayın
@SpringBootApplication(scanBasePackages = {"com.emretest"}  )
@EnableJpaRepositories(basePackages = "com.emretest.repositories")
@EnableConfigurationProperties(value = ApplicationConfig.class)

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().
                commonTags("application", "MYAPPNAME")
                .commonTags("instance", UUID.randomUUID().toString());
    }
}
