package com.johnray100.io.rabbitmq_message_status.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String STATUS_EXCHANGE = "status-exchange";
    public static final String QUEUE_PENDING = "queue.pending";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(STATUS_EXCHANGE);
    }

    @Bean
    public Queue pendingQueue() {
        return new Queue(QUEUE_PENDING, true);
    }

    @Bean
    public Binding bindingPending(Queue pendingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(pendingQueue).to(exchange).with("status.pending");
    }
}
