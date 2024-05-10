package com.sia.gentest.config;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.actuate.console.CamelDevConsoleEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Value("${camel.on-exception-log-exhausted}")
    private boolean logExhausted;

    @Value("${camel.on-exception-log-exhausted-message-body}")
    private boolean logExhaustedMessageBody;

    @Value("${camel.on-exception-log-exhausted-message-history}")
    private boolean logExhaustedMessageHistory;

}
