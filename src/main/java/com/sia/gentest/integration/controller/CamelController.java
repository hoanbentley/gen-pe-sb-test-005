package com.sia.gentest.integration.controller;

import java.util.concurrent.Future;

import org.apache.camel.ProducerTemplate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * This Camel Controller serves as a REST API entry point to invoke camel routes.
 */
@RestController
@RequestMapping("/api/integrations")
@Slf4j
public class CamelController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping(value = "/job/{jobName}")
    public String trigger(@PathVariable("jobName") String jobName) throws Exception {
        producerTemplate.sendBody("direct:" + jobName, "");
        return "ok";
    }

    // https://stackoverflow.com/questions/49308074/apache-camel-timeout-synchronous-route
    // @PostMapping(value = "/job/{jobName}")
    // public String invoke(@PathVariable String jobName) throws Exception {

    //     Exchange exchange = new DefaultExchange(producerTemplate.getCamelContext());
    //     exchange.getIn().setBody("Invoke " + jobName);
    //     // exchange.setExchangeId("exchangeId");
    //     Future<Exchange> future = producerTemplate.asyncSend("direct:" + jobName, exchange);
    //     // producerTemplate.sendBody("direct:" + jobName, "test");

    //     Exchange reply = future.get();
    //     // ExceptionUtils.getStackTrace(reply.getException())
    //     return reply.toString();
    // }

    // TODO: Revisit
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {

        log.error("Exception {}", ExceptionUtils.getMessage(e));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred. Please contact support.");
    }
}