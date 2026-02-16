package com.meswright.ja.taskmanager.ui.config;

import com.meswright.dto.TaskDtoV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public RestClient getRestClient() {
        return RestClient.create();
    }

}
