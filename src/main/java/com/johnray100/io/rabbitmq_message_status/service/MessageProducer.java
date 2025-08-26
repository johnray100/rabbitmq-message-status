package com.johnray100.io.rabbitmq_message_status.service;

import com.johnray100.io.rabbitmq_message_status.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPending(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.STATUS_EXCHANGE, "status.pending", message);
    }
}
