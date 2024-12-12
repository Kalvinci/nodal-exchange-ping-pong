package com.nodelexchange.pingpong.service_1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nodelexchange.pingpong.service_1.configuration.MessageServiceConfig.PUBLISH_QUEUE;
import static com.nodelexchange.pingpong.service_1.configuration.MessageServiceConfig.SUBSCRIBE_QUEUE;

@Service
public class PingPongService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingPongService.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PingPongService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void serve(String message) {
        rabbitTemplate.convertAndSend(PUBLISH_QUEUE, message);
        LOGGER.info("Service 1 sent message: {}", message);
    }

    @RabbitListener(queues = SUBSCRIBE_QUEUE)
    public void receive(String message) {
        LOGGER.info("Service 1 received message: {}", message);
        serve("pong");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        serve("ping");
    }
}
