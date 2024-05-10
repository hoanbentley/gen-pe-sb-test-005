package com.sia.gentest.config;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OpenApiConfiguration {

    public static final String[] PACKAGES_TO_SCAN = {
        // Add more lines to scanned for Swagger
        "com.sia.gentest.web.controller",
        "com.sia.gentest.integration.controller",
    };

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
            .group("api")
            .packagesToScan(PACKAGES_TO_SCAN)
            .build();
    }
}
