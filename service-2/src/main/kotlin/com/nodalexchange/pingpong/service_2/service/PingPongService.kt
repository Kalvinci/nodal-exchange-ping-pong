package com.nodalexchange.pingpong.service_2.service

import com.nodalexchange.pingpong.service_2.configuration.MessageServiceConfig.Companion.PUBLISH_QUEUE
import com.nodalexchange.pingpong.service_2.configuration.MessageServiceConfig.Companion.SUBSCRIBE_QUEUE
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class PingPongService(private val rabbitTemplate: RabbitTemplate) {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(PingPongService::class.java)
    }

    fun serve(message: String) {
        rabbitTemplate.convertAndSend(PUBLISH_QUEUE, message)
        LOGGER.info("Service 2 sent message: {}", message)
    }

    @RabbitListener(queues = [SUBSCRIBE_QUEUE])
    fun receive(message: String) {
        LOGGER.info("Service 2 received message: {}", message)
        serve("pong")
        try {
            Thread.sleep(10_000)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        serve("ping")
    }
}
