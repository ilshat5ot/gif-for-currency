package ru.alfa.exchangetask.gifforcurrency.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class GifClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("api_key","p247iQfyW7b9WG1kymhXqbnxX43SRer4");

        };
    }
}
