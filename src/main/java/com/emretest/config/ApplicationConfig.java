package com.emretest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

//@Bean (Global variable  oluşturmak için kullanıldı. Bean olarak oluşturulduğunda yeni bir referans yapılmıyor.
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {
    private String environment;
    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}