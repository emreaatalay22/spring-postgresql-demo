package com.emretest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @EventListener(ApplicationReadyEvent.class)
    public void logEnvironmentDetails() {
        System.out.println("======================================");
        System.out.println("Uygulama '" + activeProfile + "' profili ile ayağa kalktı.");
        System.out.println("Datasource URL: " + datasourceUrl);
        System.out.println("======================================");
    }
}
