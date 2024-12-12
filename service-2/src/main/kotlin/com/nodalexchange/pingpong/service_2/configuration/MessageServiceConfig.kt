package com.nodalexchange.pingpong.service_2.configuration

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessageServiceConfig {

    companion object {
        const val PUBLISH_QUEUE = "service-1-message-queue"
        const val SUBSCRIBE_QUEUE = "service-2-message-queue"
    }

    @Bean
    fun queue(): Queue {
        return Queue(SUBSCRIBE_QUEUE)
    }
}