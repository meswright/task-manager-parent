package com.meswright.ja.taskmanager.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public RestClient getRestClient() {
        return RestClient.create();
    }

}
