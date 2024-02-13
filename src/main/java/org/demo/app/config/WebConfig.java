package org.demo.app.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Value("${employee.service.base.url}")
    private String empServiceUrl;

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(empServiceUrl)
                .build();
    }

}
