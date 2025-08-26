package com.johnray100.io.rabbitmq_message_status.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private final MessageTracker messageTracker;

    public MessageConsumer(MessageTracker messageTracker) {
        this.messageTracker = messageTracker;
    }

    @RabbitListener(queues = "queue.pending")
    public void receivePending(String message) {
        // remove from pending list
        messageTracker.removePending(message);

        // increment ongoing counter
        messageTracker.incrementOngoing();
        try {
            Thread.sleep(30000); // 30 seconds to simulate real processing

            // Mark as completed both in-memory and DB
            messageTracker.markCompleted(message);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            messageTracker.decrementOngoing();
        }
    }
}
