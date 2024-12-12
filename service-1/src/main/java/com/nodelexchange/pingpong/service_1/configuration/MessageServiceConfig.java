package com.nodelexchange.pingpong.service_1.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageServiceConfig {

    public static final String PUBLISH_QUEUE = "service-2-message-queue";
    public static final String SUBSCRIBE_QUEUE = "service-1-message-queue";

    @Bean
    public Queue queue() {
        return new Queue(SUBSCRIBE_QUEUE);
    }
}
